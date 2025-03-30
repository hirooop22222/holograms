package hirop.hologram;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class HologramCommand implements CommandExecutor {

    private final Hologram plugin;

    public HologramCommand(Hologram plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 4) {
            sender.sendMessage("使用方法: /holocreate <server> <x> <y> <z>");
            return false;
        }

        String server = args[0];
        double x = Double.parseDouble(args[1]);
        double y = Double.parseDouble(args[2]);
        double z = Double.parseDouble(args[3]);
        String text = String.join(" ", Arrays.copyOfRange(args, 4, args.length));

        plugin.createHologram(server, x, y, z, text);
        sender.sendMessage("ホログラムを作成しました: " + text);
        return true;
    }
}