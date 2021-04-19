package navy.warspite.minecraft

import navy.warspite.minecraft.Config.config
import navy.warspite.minecraft.listener.MasterJdaListener
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.entities.TextChannel

/**
 * マスターBotクラス
 * @author warspite.navy
 */
object Master {
    lateinit var master: JDA
    lateinit var channel: TextChannel

    /** マスターBotを起動する */
    fun run() {
        JDABuilder.createDefault(config.master?.token)
            .addEventListeners(MasterJdaListener)
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
        channel.sendMessage(message).queue()
    }

    /** プレイヤー人数などを更新 */
    fun update() {
        val player = Main.instance.server.onlinePlayers.size
        master.presence.activity = Activity.playing("${player}人がMinecraft")
    }
}