package hirop.hologram;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.messaging.PluginMessageListener;

import java.io.*;

public class Hologram1 implements Listener, PluginMessageListener {

    private final Hologram plugin;

    public Hologram1(Hologram plugin) {
        this.plugin = plugin;
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(plugin, "BungeeCord");
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(plugin, "BungeeCord", this);
    }

    public void requestPlayerCount(Player player, String server) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("PlayerCount");
            out.writeUTF(server);
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }
        try (DataInputStream in = new DataInputStream(new ByteArrayInputStream(message))){
            String subchannel = in.readUTF();
            if (subchannel.equals("PlayerCount")) {
                String server = in.readUTF();
                int playerCount = in.readInt();
                player.sendMessage("Server " + server + " has " + playerCount + " players.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
