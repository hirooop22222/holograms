package hirop.hologram;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import me.decentholograms.api.DHAPI;
import me.decentholograms.api.holograms.Hologram;

import java.util.Arrays;

public final class Hologram extends JavaPlugin {

    @Override
    public void onEnable() {
        if (getCommand("holocreate") != null) {
            getCommand("holocreate").setExecutor(new HolocreateCommand(this));
        } else {
            getLogger().severe("コマンド 'holocreate' が plugin.yml に見つかりません");
        }
    }

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

        createHologram(server, x, y, z, text);
        sender.sendMessage("ホログラムを作成しました: " + text);
        return true;
    }

    public void createHologram(String server, double x, double y, double z, String text) {
        String worldName = "world"; // ここでワールド名を指定します
        Location location = new Location(Bukkit.getWorld(worldName), x, y, z);
        Hologram hologram = DHAPI.createHologram(location, text);
        hologram.show();
    }
}