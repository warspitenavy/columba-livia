package navy.warspite.minecraft.command

import navy.warspite.minecraft.Main
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

object TabComplete : TabCompleter {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        alias: String,
        args: Array<out String>
    ): MutableList<String> {
        return when {
            args.size > 1 -> {
                when (args[0]) {
                    "edit" -> Main.instance.server.onlinePlayers.map { it.name }.toMutableList()
                    else -> mutableListOf()
                }
            }
            args.isNotEmpty() -> {
                CommandRegister.commands.keys.toMutableList()
            }
            else -> mutableListOf()
        }
    }
}