package hirop.hologram;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HologramCommand implements CommandExecutor {

    private final Hologram plugin;

    public HologramCommand(Hologram plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("このコマンドはプレイヤーのみが使用できます。");
            return true;
        }

        Player player = (Player) sender;
        if (args.length != 1) {
            player.sendMessage("使用方法: /holocreate <server>");
            return false;
        }

        String server = args[0];
        player.sendMessage("サーバーのホログラムを作成しています:" + server);
        return true;
    }

}
