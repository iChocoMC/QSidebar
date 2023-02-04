package ichocomc.qscoreboard.placeholders.methods;

import org.bukkit.entity.Player;

import ichocomc.qscoreboard.placeholders.PlaceholderMethod;
import me.clip.placeholderapi.PlaceholderAPI;

public class WithPlaceholdersMethod extends PlaceholderMethod {

    @Override
    public String transform(Player player, String text) {
        return PlaceholderAPI.setPlaceholders(player, text);
    }
}