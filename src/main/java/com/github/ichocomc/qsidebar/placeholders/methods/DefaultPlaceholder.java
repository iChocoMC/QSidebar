package com.github.ichocomc.qsidebar.placeholders.methods;

import com.github.ichocomc.qsidebar.interfaces.PlaceholderMethod;
import org.bukkit.entity.Player;

public class DefaultPlaceholder implements PlaceholderMethod {

    @Override
    public String transform(String text, Player player) {
        return text;
    }
}