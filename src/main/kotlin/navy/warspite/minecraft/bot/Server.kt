package navy.warspite.minecraft.bot

import navy.warspite.minecraft.Config
import net.dv8tion.jda.api.JDA

/**
 * MasterやユーザーBotの共通クラス
 */
object Server {
    /**
     * Discordチャンネルにメッセージを送信する
     * @param jda 送信元のJDA
     * @param message メッセージ
     */
    fun send(jda: JDA, message: String) {
        val channel = Config.config.server?.channel?.let {
            jda.getTextChannelById(it)
        } ?: return
        channel.sendMessage(message).queue()
    }
}