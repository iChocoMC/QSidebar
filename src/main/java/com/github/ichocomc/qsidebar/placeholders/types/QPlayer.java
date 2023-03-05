package com.github.ichocomc.qsidebar.placeholders.types;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
			case "ping":
				return "" + ((CraftPlayer)player).getHandle().ping;
			case "name":
				return player.getName();
			case "displayname":
				return player.getPlayer().getCustomName();
			default:
				return null;
		}
	}
}