package navy.warspite.minecraft.command

import navy.warspite.minecraft.Utils.coloured
import navy.warspite.minecraft.data.Messages
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object CommandRegister : CommandExecutor {
    val commands = linkedMapOf(
        "edit" to EditToken
    )
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return when {
            args.isNullOrEmpty() -> {
                sender.sendMessage(coloured(Messages().commandReference, true))
                true
            }
            commands.containsKey(args[0]) -> {
                commands[args[0]]?.onCommand(sender, command, label, args)
                true
            }
            else -> false
        }
    }
}