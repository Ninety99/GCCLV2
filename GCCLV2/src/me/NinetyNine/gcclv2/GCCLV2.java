package me.NinetyNine.gcclv2;

import java.util.ArrayList;

import org.bukkit.plugin.java.JavaPlugin;

import me.NinetyNine.gcclv2.commands.GCCLV2Commands;

public class GCCLV2 extends JavaPlugin {
	
	public ArrayList<String> changes;
	public ArrayList<String> loadChanges;
	
	@Override
	public void onEnable() {
		getCommand("changelog").setExecutor(new GCCLV2Commands());
		getCommand("gcchangelog").setExecutor(new GCCLV2Commands());
		
		//getConfig().getStringList(ChangelogIO.load(loadChanges, config));
		//Config.getConfig(this, "config.yml");
		//Config.getConfigFile(this, "changelog.yml");
		
		getConfig().options().copyDefaults(true);
		reloadConfig();
		saveConfig();
		getServer().getLogger().info("[GCCL] Getting config...");
		getServer().getLogger().info("[GCCL] Enabled!");	
	}
	
	@Override
	public void onDisable() {
		//OgetConfig().getStringList(ChangelogIO.save(changes, config));
		getServer().getLogger().info("[GCCL] Saving config...");
		reloadConfig();
		saveConfig();
	}

}
