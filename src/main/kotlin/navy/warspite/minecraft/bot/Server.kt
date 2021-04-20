package navy.warspite.minecraft.bot

import navy.warspite.minecraft.Config
import navy.warspite.minecraft.Main
import navy.warspite.minecraft.McListener
import net.dv8tion.jda.api.JDA

/**
 * MasterやユーザーBotの共通クラス
 */
object Server {
    /**
     * Discordチャンネルにメッセージを送信
     * @param jda 送信元のJDA
     * @param message メッセージ
     */
    fun send(jda: JDA, message: String) {
        val channel = Config.config.server?.channel?.let {
            jda.getTextChannelById(it)
        } ?: return
        channel.sendMessage(message).queue()
    }

    /**
     * Master, ユーザーBotを再起動
     * @param reboot trueの場合、再起動
     */
    fun powerOff(reboot: Boolean = false) {
        Master.shutdown()
        for (user in User.users) {
            User.shutdown(user.key)
        }

        if (!reboot) return
        Config.loadConfig()
        if (Config.validity) {
            Master.run()
            User.runOnlinePlayer()
        }
    }
}