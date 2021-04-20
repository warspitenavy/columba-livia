package navy.warspite.minecraft.bot

import navy.warspite.minecraft.Config
import navy.warspite.minecraft.Main
import navy.warspite.minecraft.Utils
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

/**
 * マスターBotクラス
 * @author warspite.navy
 */
object Master {
    lateinit var master: JDA

    /** マスターBotを起動する */
    fun run() {
        JDABuilder.createDefault(Config.config.master?.token)
            .addEventListeners(object : ListenerAdapter() {
                /** 起動したら、変数masterにJDAを代入 */
                override fun onReady(event: ReadyEvent) {
                    super.onReady(event)
                    master = event.jda
                }

                /** メッセージを受信したら、ゲーム内に送信 */
                override fun onMessageReceived(event: MessageReceivedEvent) {
                    super.onMessageReceived(event)
                    if (event.channel.id != Config.config.server?.channel) return
                    if (event.author.isBot) return
                    if (event.message.isWebhookMessage) return

                    val name = event.author.name
                    val message = event.message.contentDisplay

                    Main.instance.server.broadcastMessage(Utils.coloured("&r[&dDiscord&r] ${name}: $message"))
                }
            })
            .build()
    }

    /** マスターBotを停止する */
    fun shutdown() {
        master.shutdown()
    }

    /**
     * メッセージをDiscordチャンネルに送信
     * @param message Discordチャンネルに送信するメッセージ
     */
    fun send(message: String) {
        Server.send(master, message)
    }

    /** プレイヤー人数などを更新 */
    fun update() {
        val player = Main.instance.server.onlinePlayers.size
        master.presence.activity = Activity.playing("${player}人がMinecraft")
    }
}