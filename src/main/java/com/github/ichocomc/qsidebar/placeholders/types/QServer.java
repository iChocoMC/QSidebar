package com.github.ichocomc.qsidebar.placeholders.types;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class QServer extends PlaceholderExpansion {

	@Override
	public @NotNull String getAuthor() {
		return "iChocoMC";
	}

	@Override
	public @NotNull String getIdentifier() {
		return "qserver";
	}

	@Override
	public @NotNull String getVersion() {
		return "1.0";
	}

	@Override
	public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
		return params.equals("online") 
			? "" + Bukkit.getOnlinePlayers().size()
			: null;
	}
}
