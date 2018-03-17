package me.NinetyNine.gcclv2.commands;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import me.NinetyNine.gcclv2.GCCLV2;
 
public class GCCLV2Commands implements Listener, CommandExecutor {

	public static ArrayList<String> change = new ArrayList<String>();
	public static GCCLV2 plugin;

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		Player player = (Player) sender;
		
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta bookmeta = (BookMeta) book.getItemMeta();
		
		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");
		
		String gcpf = ChatColor.GREEN + "✔ " + ChatColor.BLACK + "";
		String gcpr = ChatColor.RED + "✘ " + ChatColor.BLACK + "";
		String gcpc = ChatColor.GOLD + "▶ " + ChatColor.BLACK + "";

		String message = "";

		for (int i = 2; i < args.length; i++) {
			message += args[i] + " ";
		}
		message = message.trim();

		if (cmd.getName().equalsIgnoreCase("changelog")) {
			if (player.hasPermission("changelog.open")) {
				for (String changelog : change) {
					bookmeta.addPage(" ");
					bookmeta.setPage(1, changelog);
					book.setItemMeta(bookmeta);
					player.getInventory().addItem(book);
					return true;
				}
			} else
				return false;
		}

		if (cmd.getName().equalsIgnoreCase("gcchangelog")) {
			if (player.hasPermission("gcchangelog.admin")) {

				if (args.length == 0) {
					player.sendMessage("Are you sure you typed it correctly?");
					return true;
				}

				if (args.length == 0) {
					player.sendMessage("Usage: /gcchangelog add <type> <change> or /gcchangelog undo");
					System.out.println("gcchangelog add/remove");
					return true;
				}

				if (args[0].equalsIgnoreCase("add")) {
					if (args.length == 1) {
						player.sendMessage("Usage: /gcchangelog add <type> <change>");
						System.out.println("gcchangelog add type message");
						return true;
					}
				}

				if (args[0].equalsIgnoreCase("reload")) {
					if (args.length == 1) {
						plugin.reloadConfig();
						System.out.println("reload");
						return true;
					}
				}

				if (args[0].equalsIgnoreCase("undo")) {
					if (args.length == 1) {
						if (!GCCLV2Commands.change.isEmpty()) {
							GCCLV2Commands.change.remove(GCCLV2Commands.change.size() - 1);
							System.out.println("undo");
						} else {
							player.sendMessage("There are currently no changes made.");
						}
						return true;
					}
				}

				if (args[0].equalsIgnoreCase("set")) {
					if (args.length == 1) {
						if (bookmeta.getPageCount() > plugin.getConfig().getInt("maxPages")) {
							if (change.contains("NEW_PAGE")) {
								bookmeta.addPage(" ");
								return true;
							} 
						} else {
							player.sendMessage("Reached the maximum amount of pages!");
							return false;
						}

						bookmeta.setAuthor("aXed");
						bookmeta.setTitle("Changelog");

						book.setItemMeta(bookmeta);

						player.sendMessage("Set!");
						return true;
					}
				}

				if (args[1].equalsIgnoreCase("date")) {
					if (args.length == 2) {
						change.add(format.format(now) + "\n");
						System.out.println("date");
					}
					return true;
				}

				if (args[1].equalsIgnoreCase("fixed")) {
					if (args.length == 2) {
						player.sendMessage("Usage: /gcchangelog add fixed <change>");
					} else {
						change.add(gcpf + message);
						System.out.println("fixed");
						return true;
					}
				}

				if (args[1].equalsIgnoreCase("removed")) {
					if (args.length == 2) {
						player.sendMessage("Usage: /gcchangelog add removed <change>");
					} else {
						change.add(gcpr + message);
						System.out.println("remove");
						return true;
					}
				}

				if (args[1].equalsIgnoreCase("changed")) {
					if (args.length == 2) {
						player.sendMessage("Usage: /gcchangelog add changed <change>");
					} else {
						change.add(gcpc + message);

						System.out.println("changed");
						return true;
					}
				}

				if (args[1].equalsIgnoreCase("page")) {
					if (args.length == 2) {
						change.add("NEW_PAGE");
						player.sendMessage("+1 page");
						return true;
					}
				}
			} else {
				player.sendMessage("You do not have permissions.");
				return false;
			}
		}
		return true;
	}
}