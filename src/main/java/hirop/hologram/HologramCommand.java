package hirop.hologram;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HologramCommand implements CommandExecutor {

    private final Hologram plugin;

    public HologramCommand(Hologram plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return plugin.onCommand(sender, command, label, args);
    }
}
