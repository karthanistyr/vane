package org.oddlama.vane.portals.event;

import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;

public class PortalLinkConsoleEvent extends PortalEvent {
	private static final HandlerList handlers = new HandlerList();
	private Player player;
	private List<Block> portal_blocks;
	private boolean check_only;

	public PortalLinkConsoleEvent(final Player player, final List<Block> portal_blocks, boolean check_only) {
		this.player = player;
		this.portal_blocks = portal_blocks;
		this.check_only = check_only;
	}

	public Player getPlayer() {
		return player;
	}

	public List<Block> getPortalBlocks() {
		return portal_blocks;
	}

	public boolean checkOnly() {
		return check_only;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
