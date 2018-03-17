package me.NinetyNine.gcclv2.commands;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	// public static List<String> changesMade =
	// plugin.getConfig().getStringList("changesMade");
	// public static String clio = ChangelogIO.save(change.get(changesMade.size()),
	// plugin.getConfig());

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		Player player = (Player) sender;

		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta bookmeta = (BookMeta) book.getItemMeta();
		bookmeta.addPage(" ");

		String gcpf = ChatColor.GREEN + "✔ " + ChatColor.BLACK + "";
		String gcpr = ChatColor.RED + "✘ " + ChatColor.BLACK + "";
		String gcpc = ChatColor.GOLD + "▶ " + ChatColor.BLACK + "";

		Date now = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd MM yyyy");

		String message = "";

		for (int i = 2; i < args.length; i++) {
			message += args[i] + " ";
		}
		message = message.trim();

		List<String> pages = bookmeta.getPages();

		if (cmd.getName().equalsIgnoreCase("changelog")) {
			if (player.hasPermission("changelog.open")) {

				for (String changelog : change) {
					bookmeta.setPage(1, changelog);
					book.setItemMeta(bookmeta);
					player.getInventory().addItem(book);
					return true;
				}
			} else {
				return false;
			}
			return true;
		}

		if (cmd.getName().equalsIgnoreCase("gcchangelog")) {
			if (player.hasPermission("gcchangelog.admin")) {
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
						change.remove(1);
						System.out.println("undo");
						return true;
					}
				}

				if (args[0].equalsIgnoreCase("set")) {
					if (args.length == 1) {
						// for (String changelog : change) {

						if (change.contains("NEW_PAGE")) {
							bookmeta.addPage(" ");
						} else {
							bookmeta.setAuthor("aXed");
							bookmeta.setTitle("Changelog");
							// pages.addAll(change);
							// bookmeta.setPages(changelog);

							book.setItemMeta(bookmeta);

							player.sendMessage("Set!");
							return true;
						}
						// }
					}
				}
				// "addDate"
				if (args[1].equalsIgnoreCase("date")) {
					if (args.length == 2) {
						change.add(ChatColor.BOLD + format.format(now) + "\n");
						// changesMade.add("addDate");

						System.out.println("date");
					}
					return true;
				}
				// "addFixed"
				if (args[1].equalsIgnoreCase("fixed")) {
					if (args.length == 2) {
						player.sendMessage("Usage: /gcchangelog add fixed <change>");
					} else {
						change.add(gcpf + message + "\n");
						// changesMade.add("addFixed");
						System.out.println("fixed");
					}
					return true;
				}
				// "addRemoved"
				if (args[1].equalsIgnoreCase("removed")) {
					if (args.length == 2) {
						player.sendMessage("Usage: /gcchangelog add removed <change>");
					} else {
						change.add(gcpr + message + "\n");
						// changesMade.add("addRemoved");
						System.out.println("remove");
					}
					return true;
				}
				// "addChanged"
				if (args[1].equalsIgnoreCase("changed")) {
					if (args.length == 2) {
						player.sendMessage("Usage: /gcchangelog add changed <change>");
					} else {
						change.add(gcpc + message + "\n");
						// changesMade.add("addChanged");
						System.out.println("changed");
					}
					return true;
				}

				if (args[1].equalsIgnoreCase("page")) {
					if (args.length == 2) {
						// if (bookmeta.getPageCount() < plugin.getConfig().getInt("maxPages")) {
						change.add("NEW_PAGE");
						player.sendMessage("+1 page");
						// changesMade.add("addPage");
						return true;
					}

					// if (bookmeta.getPageCount() == plugin.getConfig().getInt("maxPages")) {
					// player.sendMessage("Reached the maximum amount of pages!");
					// }
					// return true;
				}
			} else {
				player.sendMessage("You do not have permissions.");
				return false;
			}
		}
		return true;
	}
}