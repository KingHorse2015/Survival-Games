package org.mcsg.survivalgames.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import net.milkbowl.vault.economy.Economy;

public class EconomyManager {

	private static EconomyManager instance = new EconomyManager();
	private Economy economy;
	private boolean enabled = false;

	private EconomyManager() {

	}

	public static EconomyManager getInstance() {
		return instance;
	}

	public void setup() {
		enabled = setupEconomy();

	}

	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			economy = economyProvider.getProvider();
		}

		return (economy != null);
	}

	public Economy getEcon() {
		return economy;
	}

	public boolean econPresent() {
		return enabled;
	}

}
