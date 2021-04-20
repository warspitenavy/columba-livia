package navy.warspite.minecraft.command

import navy.warspite.minecraft.Config
import navy.warspite.minecraft.Main
import navy.warspite.minecraft.Utils.coloured
import navy.warspite.minecraft.data.Messages
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

/**
 * トークン編集コマンドクラス
 */
object EditToken : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        /** プレイヤーを取得 */
        val player = if (args.size > 1) {
            Main.instance.server.getPlayerExact(args[1])
        } else null

        /** 取得した変数playerがnullの場合、終了 */
        if (player == null){
            sender.sendMessage(coloured(Messages().missingPlayer, true))
            return true
        }

        return when {
            /** トークン編集 */
            args.size > 2 -> {
                Config.edit(player.uniqueId, args[2])
                sender.sendMessage(coloured(Messages().editToken, true))
                true
            }
            /** トークン削除 */
            args.size > 1 -> {
                Config.edit(player.uniqueId)
                sender.sendMessage(coloured(Messages().removeToken, true))
                true
            }
            /** (念のため)コマンドが空の場合リファレンス表示 */
            args.isNullOrEmpty() -> {
                sender.sendMessage(coloured(Messages().commandReference, true))
                true
            }
            else -> false
        }
    }
}