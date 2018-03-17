package me.NinetyNine.gcclv2;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.BookMeta;

import me.NinetyNine.gcclv2.commands.GCCLV2Commands;

public class Methods implements Listener {

	public void undo(Player player) {
		if (!GCCLV2Commands.change.isEmpty())
			GCCLV2Commands.change.remove(GCCLV2Commands.change.size() - 1);
		else
			player.sendMessage("There are currently no changes made.");
	}

	public void addPage(BookMeta bookmeta) {
		if (GCCLV2Commands.change.contains("NEW_PAGE")) {
			GCCLV2Commands.bookmeta.addPage(" ");
		} else 
			return;
	}
}