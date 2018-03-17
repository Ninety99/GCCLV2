package me.NinetyNine.gcclv2;

import org.bukkit.plugin.java.JavaPlugin;

import me.NinetyNine.gcclv2.commands.GCCLV2Commands;

public class GCCLV2 extends JavaPlugin {
	
	//String changes = ChangelogIO.save((ArrayList<String>) getConfig().getStringList("changesMade"), getConfig());
	//ArrayList<String> loadChanges = ChangelogIO.load((List<String>) getConfig().getStringList("changesMade"), getConfig());
	
	@Override
	public void onEnable() {
		getCommand("changelog").setExecutor(new GCCLV2Commands());
		getCommand("gcchangelog").setExecutor(new GCCLV2Commands());
		
		//String clio = ChangelogIO.save(GCCLV2Commands.change.get(GCCLV2Commands.changesMade.size()), getConfig());
		
		getServer().getLogger().info("[GCCL] Getting config...");
		getConfig().options().copyDefaults(true);
		saveConfig();
		reloadConfig();
		getServer().getLogger().info("[GCCL] Enabled!");	
	}
	
	@Override
	public void onDisable() {
		//OgetConfig().getStringList(ChangelogIO.save(changes, config));
		getServer().getLogger().info("[GCCL] Saving config...");
		//getConfig().set("changesMade", changes);
		//getConfig().set("changesMade", loadChanges);
		saveConfig();
	}

}
