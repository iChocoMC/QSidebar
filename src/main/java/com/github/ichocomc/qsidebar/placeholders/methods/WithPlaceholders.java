package com.github.ichocomc.qsidebar.placeholders.methods;

import com.github.ichocomc.qsidebar.interfaces.PlaceholderMethod;
import com.github.ichocomc.qsidebar.placeholders.types.QPlayer;
import com.github.ichocomc.qsidebar.placeholders.types.QServer;

import me.clip.placeholderapi.PlaceholderAPI;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class WithPlaceholders implements PlaceholderMethod {
    
    public WithPlaceholders(FileConfiguration config) {
        if (config.getBoolean("enable-custom-placeholders.qplayer")) {
            new QPlayer().register();
        }
        if (config.getBoolean("enable-custom-placeholders.qserver")) {
            new QServer().register();
        }
    }

    @Override
    public String transform(String text, Player player) {
        return PlaceholderAPI.setPlaceholders(player, text);
    }
}