package de.relulu.MobAge.util;

import org.bukkit.configuration.file.FileConfiguration;

import de.relulu.MobAge.MobAgeInit;

/**
 * Diese Klasse kümmert sich um das Auslesen und Bereitstellen von Konfigurationsparametern
 * 
 * @author ReLuLu
 *
 */
public class ConfigManager {

    private MobAgeInit mai;
	private FileConfiguration cfg;

	/**
	 * Konstruktor für für den Konfigurationsmanager
	 * 
	 * @param fcfg die Konfigurationsdatei
	 */
	public ConfigManager(MobAgeInit mai, FileConfiguration fcfg) {
		this.mai = mai;
	    this.cfg = fcfg;
	}

	/**
	 * Setzt die nodamage-Variable
	 * @param b neuer Wert
	 */
	public void setNoDamage(boolean b) {
		cfg.set("player-nodamage", b);
		mai.saveConfig();
	}

	/**
	 * Holt den Wert der nodamage-Variable
	 * @return Wert der Variable
	 */
	public boolean getNoDamage() {
		return cfg.getBoolean("player-nodamage");
	}

	/**
	 * Setzt die nohunger-Variable
	 * @param b neuer Wert
	 */
	public void setNoHunger(boolean b) {
		cfg.set("player-nohunger", b);
        mai.saveConfig();
	}

	/**
	 * Holt den Wert der nohunger-Variable
	 * @return Wert der Variable
	 */
	public boolean getNoHunger() {
		return cfg.getBoolean("player-nohunger");
	}

	/**
	 * Setzt die antigrief-Variable
	 * @param b neuer Wert
	 */
	public void setAntiGrief(boolean b) {
		cfg.set("decoration-antigrief", b);
        mai.saveConfig();
	}

}
