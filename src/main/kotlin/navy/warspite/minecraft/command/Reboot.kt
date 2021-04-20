package navy.warspite.minecraft.command

import navy.warspite.minecraft.bot.Server
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

object Reboot: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return when {
            args.isNotEmpty() -> {
                Server.powerOff(true)
                true
            }
            else -> false
        }
    }
}