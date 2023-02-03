package ichocomc.qscoreboard.placeholders.types;

import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

public class QPlayer extends PlaceholderExpansion {

	@Override
	public @NotNull String getAuthor() {
		return "iChocoMC";
	}

	@Override
	public @NotNull String getIdentifier() {
		return "qplayer";
	}

	@Override
	public @NotNull String getVersion() {
		return "1.0";
	}

	@Override
	public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
		switch (params) {
			case "name":
				return player.getName();
			case "displayname":
				return player.getPlayer().getCustomName();
			case "ping":
				return "" + ((CraftPlayer)player).getHandle().ping;
			default:
				return null;
		}
	}
}