package fr.gaston147.parkour;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class ParkourGame {
	private Parkour plugin;
	private Location startPoint;
	private List<Player> players;
	private boolean started;
	
	public ParkourGame(Parkour plugin, Location startPoint) {
		this.plugin = plugin;
		this.startPoint = startPoint;
		players = new ArrayList<Player>();
		started = false;
	}
	
	public void addPlayer(Player player) {
		if (!started)
			players.add(player);
	}
	
	public void start() {
		started = true;
	}
	
	public void stop() {
		plugin.removeGame(this);
	}
	
	public Location getStartPoint() {
		return startPoint;
	}
	
	public boolean containsPlayer(Player player) {
		return players.contains(player);
	}

	public double getOutY() {
		return startPoint.getY() - 1;
	}
}
