package me.NinetyNine.gcclv2;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;

public class ChangelogIO implements Listener {
	
	public static String save(ArrayList<String> changes, FileConfiguration config) {
		config.set("changesMade", changes);
		return save(changes, config);
	}
	
	public static String load(List<String> loadChanges, FileConfiguration config) {
		loadChanges = config.getStringList("changesMade");
		config.get("changesMade", loadChanges);
		config.set("changesMade", loadChanges);
		return load(loadChanges, config);
	}
}