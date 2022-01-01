package de.relulu.MobAge;

import org.bukkit.event.Listener;

import de.relulu.MobAge.util.ConfigManager;
import de.relulu.MobAge.util.MessageHandler;

/**
 * Diese Klasse stellt den Listener für aufkommende Events, die von diesem
 * Plugin verarbeitet werden sollen.
 * 
 * @author ReLuLu
 *
 */
public class MobAgeListener implements Listener {
	
	private MobAgeManager maman;
	private MessageHandler mh;
	private ConfigManager confman;

    /**
     * Konstruktor für den Listener
     * @param maman DailyManager Objekt
     */
    public MobAgeListener(MobAgeManager maman) {
        this.maman = maman;
        this.mh = maman.getMessageHandler();
        this.confman = maman.getConfigManager();
	}

	/**
	 * Das Foodlevel vom Spieler ändert sich
	 *
	 * @param flce das FoodLevelChangeEvent
	 *//*
	@EventHandler
	public void onHunger(FoodLevelChangeEvent flce) {}
	*/
}
