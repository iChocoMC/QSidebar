package com.github.ichocomc.qsidebar.sidebar;

import com.github.ichocomc.qsidebar.interfaces.PlaceholderMethod;
import com.github.ichocomc.qsidebar.QSidebar;
import com.github.ichocomc.qsidebar.animatedtitle.DefaultTitle;
import com.github.ichocomc.qsidebar.animatedtitle.WithAnimation;
import com.github.ichocomc.qsidebar.interfaces.SidebarTitle;
import com.github.ichocomc.qsidebar.placeholders.methods.DefaultPlaceholder;
import com.github.ichocomc.qsidebar.placeholders.methods.WithPlaceholders;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.Scoreboard;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.List;

public class Sidebar {

    private final SidebarScore score;
    private final Scoreboard serverScoreboard;
    private final List<EntityPlayer> onlinePlayers;

    public Sidebar(FileConfiguration config, QSidebar plugin) {
        onlinePlayers = MinecraftServer.getServer().getPlayerList().players;
        serverScoreboard = MinecraftServer.getServer().getWorld().scoreboard;

        PlaceholderMethod method =
            plugin.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")
            || plugin.getConfig().getBoolean("placeholder-api-support")
                ? new WithPlaceholders(plugin.getConfig())
                : new DefaultPlaceholder();

        SidebarTitle title = config.getBoolean("animated-title.enable")
                ? new WithAnimation(config.getStringList("animated-title.lines"), plugin)
                : new DefaultTitle(config.getString("title"));

        SidebarObjective objective = new SidebarObjective(serverScoreboard, title);

        score = new SidebarScore(serverScoreboard, objective, config, method);
        serverScoreboard.setDisplaySlot(1, objective);
    }

    // Set sidebar using bukkit entity
    public void setSidebar(Player player) {
        score.setLines(((CraftPlayer)player).getHandle().playerConnection, player);
    }

    /*
     * Update the sidebar lines for all players online
     */
    public void updateSidebar() {
        serverScoreboard.setDisplaySlot(1, new SidebarObjective(serverScoreboard));
        for (EntityPlayer entityPlayer : onlinePlayers) {
            score.setLines(entityPlayer.playerConnection, entityPlayer.getBukkitEntity().getPlayer());
        }
    }
}