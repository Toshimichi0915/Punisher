package punisher.punisher;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

//import org.bukkit.Bukkit;
import java.util.Objects;

public final class Punisher extends JavaPlugin implements Listener{
    @Override
    public void onEnable() {
        saveDefaultConfig();
        FileConfiguration config = getConfig();
        config.set("Version", "1.0");
        getLogger().info(ChatColor.RED + "Punisherをロードしました。");
        Objects.requireNonNull(getCommand("punisher")).setExecutor(new CommandClass());
        getServer().getPluginManager().registerEvents(this, this);
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        String target = e.getPlayer().getName();
        e.setJoinMessage(ChatColor.BLUE + "プレイヤーがログインしました: " + target);
        FileConfiguration config = getConfig();
        String Info = config.getString("Banned" + target);
        saveConfig();
        if(Info == null) {
            config.set("Banned" + target, "False");
            saveConfig();
        }
        if(Objects.equals(Info, "True")) {
            e.setJoinMessage(ChatColor.BLUE + "Banned: " + target);
        }

    }
    @Override
    public void onDisable() {
        getLogger().info("Punisherを終了しました");
    }
}
