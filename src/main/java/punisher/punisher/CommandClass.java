package punisher.punisher;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import java.util.Objects;


public final class CommandClass extends JavaPlugin implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("punisher")) {
            if (args.length == 0) {
                sender.sendMessage("§6/punisher help");
            } else {
                if (args[0].equalsIgnoreCase("help")) {
                    sender.sendMessage("§6[Help]");
                    sender.sendMessage("&6/punisher ban ID Reason");
                }
                if (args[0].equalsIgnoreCase("ban")) {
                    if (args.length == 3) {
                        String method = args[0];
                        String playerName = args[1];
                        String reason = args[2];
                        Player bancheck = Bukkit.getPlayer(playerName);
                        if (Objects.requireNonNull(bancheck).isBanned()) {
                            sender.sendMessage(ChatColor.RED + "Banned user");
                        } else{
                        Player target = Bukkit.getServer().getPlayer(playerName);
                        for(Player ops : Bukkit.getOnlinePlayers()) {
                            if (ops.isOp()) {
                                ops.sendMessage(ChatColor.GOLD + "[Logs]");
                                ops.sendMessage(ChatColor.GOLD + "Method Type: " + method);
                                ops.sendMessage(ChatColor.GOLD + "PlayerName: " + playerName);
                                ops.sendMessage(ChatColor.GOLD + "Reason: " + reason);
                                Objects.requireNonNull(target).kickPlayer(ChatColor.DARK_RED + "» " + ChatColor.RED + "You have been banned because " + playerName + "!");
                                saveDefaultConfig();
                                FileConfiguration config = getConfig();
                                config.set("Banned" + args[1], "True");
                                saveConfig();
                            }
                            }
                            return true;
                        }
                        sender.sendMessage("§6/punisher help");
                    }
                }
            }
        }
        return true;
    }
}
