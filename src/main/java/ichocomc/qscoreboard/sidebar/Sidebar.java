package ichocomc.qscoreboard.sidebar;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.Scoreboard;

public class Sidebar {

    private final SidebarScore score;
    private final String title;
    private final Scoreboard serverScoreboard;
    private final List<EntityPlayer> onlinePlayers;

    public Sidebar(FileConfiguration sidebarConfig) {
        onlinePlayers = MinecraftServer.getServer().getPlayerList().players;
        title = ChatColor.translateAlternateColorCodes('&', sidebarConfig.getString("title"));
        serverScoreboard = MinecraftServer.getServer().getWorld().scoreboard;

        SidebarObjective objective = new SidebarObjective(serverScoreboard, title);

        score = new SidebarScore(serverScoreboard, objective, sidebarConfig);
        serverScoreboard.setDisplaySlot(1, objective);
    }

    // Set sidebar using nms entity
    public void setSidebar(EntityPlayer entityPlayer, Player player) {
        score.setLines(entityPlayer.playerConnection, player);
    }

    // Set sidebar using bukkit entity
    public void setSidebar(Player player) {
        setSidebar(((CraftPlayer)player).getHandle(), player);
    }

    /*
     * Update the sidebar lines for all players online
     */
    public void updateSidebar() {
        serverScoreboard.setDisplaySlot(1, new SidebarObjective(serverScoreboard, title));

        for (EntityPlayer entityPlayer : onlinePlayers) {
            setSidebar(entityPlayer, entityPlayer.getBukkitEntity().getPlayer());
        }
    }
}