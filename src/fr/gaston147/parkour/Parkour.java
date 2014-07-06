package fr.gaston147.parkour;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Parkour extends JavaPlugin {
	private List<ParkourGame> games;
	
	public void onEnable() {
		games = new ArrayList<ParkourGame>();
		Bukkit.getPluginManager().registerEvents(new ParkourListener(this), this);
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command can only be executed by a player");
			return true;
		}
		Player player = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("beginparkour")) {
			for (ParkourGame game : games) {
				if (game.containsPlayer(player)) {
					player.sendMessage(ChatColor.RED + "You are already in a game!");
					return true;
				}
			}
			ParkourGame game = new ParkourGame(this, new Location(player.getWorld(), (double) player.getLocation().getBlockX() + 0.5, player.getLocation().getY(), (double) player.getLocation().getBlockZ() + 0.5, player.getLocation().getYaw(), player.getLocation().getPitch()));
			game.addPlayer(player);
			games.add(game);
			game.start();
			player.sendMessage(ChatColor.YELLOW + "Game started!");
			return true;
		} else if (cmd.getName().equalsIgnoreCase("endparkour")) {
			for (ParkourGame game : games) {
				if (game.containsPlayer(player)) {
					game.stop();
					player.sendMessage(ChatColor.YELLOW + "Game stopped!");
					return true;
				}
			}
			player.sendMessage(ChatColor.RED + "You aren't in a game!");
			return true;
		}
		return false;
	}

	public void removeGame(ParkourGame game) {
		games.remove(game);
	}

	public ParkourGame getParkourGameOf(Player player) {
		for (ParkourGame game : games) {
			if (game.containsPlayer(player))
				return game;
		}
		return null;
	}
}
