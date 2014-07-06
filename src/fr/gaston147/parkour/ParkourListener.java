package fr.gaston147.parkour;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class ParkourListener implements Listener {
	private Parkour plugin;
	
	public ParkourListener(Parkour plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		ParkourGame game = plugin.getParkourGameOf(event.getPlayer());
		if (game != null) {
			if (event.getPlayer().getLocation().getY() <= game.getOutY()) {
				event.getPlayer().teleport(game.getStartPoint());
			}
		}
	}
}
