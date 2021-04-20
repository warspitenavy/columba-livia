package navy.warspite.minecraft.command

import navy.warspite.minecraft.Utils.coloured
import navy.warspite.minecraft.data.Messages
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object CommandRegister : CommandExecutor {
    val command = linkedMapOf(
        "edit" to EditToken
    )
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return when {
            args.isNullOrEmpty() -> {
                sender.sendMessage(coloured(Messages().commandReference, true))
                true
            }
            else -> false
        }
    }
}