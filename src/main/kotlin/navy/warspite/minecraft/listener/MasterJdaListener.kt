package navy.warspite.minecraft.listener

import navy.warspite.minecraft.Config.config
import navy.warspite.minecraft.Main
import navy.warspite.minecraft.Master
import navy.warspite.minecraft.Utils.coloured
import net.dv8tion.jda.api.events.ReadyEvent
import net.dv8tion.jda.api.events.message.MessageReceivedEvent
import net.dv8tion.jda.api.hooks.ListenerAdapter

/**
 * マスターのリスナークラス
 * @author warspite.navy
 */
object MasterJdaListener : ListenerAdapter() {
    /** マスター起動イベント */
    override fun onReady(event: ReadyEvent) {
        super.onReady(event)
        Master.master = event.jda
        Master.channel = config.server?.channel?.let { event.jda.getTextChannelById(it) } ?: return
    }

    /**
     * Discordのメッセージを受信
     * 受信したメッセージをゲーム内に送信
     */
    override fun onMessageReceived(event: MessageReceivedEvent) {
        super.onMessageReceived(event)
        if(event.author.isBot) return

        val name = event.author.name
        val message = event.message.contentDisplay

        Main.instance.server.broadcastMessage(
            coloured("&r[&dDiscord&r] ${name}: $message")
        )
    }
}