package org.oddlama.vane.portals.menu;

import static org.oddlama.vane.util.Util.namespaced_key;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import org.oddlama.vane.annotation.lang.LangMessage;
import org.oddlama.vane.core.config.TranslatedItemStack;
import org.oddlama.vane.core.lang.TranslatedMessage;
import org.oddlama.vane.core.menu.Menu.ClickResult;
import org.oddlama.vane.core.menu.Menu;
import org.oddlama.vane.core.menu.MenuItem;
import org.oddlama.vane.core.menu.MenuWidget;
import org.oddlama.vane.core.module.Context;
import org.oddlama.vane.core.module.ModuleComponent;
import org.oddlama.vane.portals.Portals;
import org.oddlama.vane.portals.portal.Portal;

public class ConsoleMenu extends ModuleComponent<Portals> {
	@LangMessage public TranslatedMessage lang_title;

	public TranslatedItemStack<?> item_settings;
	public TranslatedItemStack<?> item_select_target;
	public TranslatedItemStack<?> item_select_target_locked;
	public TranslatedItemStack<?> item_unlink_console;
	public TranslatedItemStack<?> item_destroy_portal;

	public ConsoleMenu(Context<Portals> context) {
		super(context.namespace("console"));

		final var ctx = get_context();
        item_settings             = new TranslatedItemStack<>(ctx, "settings",             Material.ENDER_PEARL, 1, "Used to enter portal settings.");
        item_select_target        = new TranslatedItemStack<>(ctx, "select_target",        Material.COMPASS, 1, "Used to enter portal target selection.");
        item_select_target_locked = new TranslatedItemStack<>(ctx, "select_target_locked", Material.FIREWORK_STAR, 1, "Used to show portal target selection when the target is locked.");
        item_unlink_console       = new TranslatedItemStack<>(ctx, "unlink_console",       Material.CHAIN, 1, "Used to unlink the current console.");
        item_destroy_portal       = new TranslatedItemStack<>(ctx, "destroy_portal",       namespaced_key("vane", "decoration_tnt_1"), 1, "Used to destroy the portal.");
	}

	public Menu create(final Portal portal, final Player player, final Block console) {
		final var columns = 9;
		final var title = lang_title.str("§5§l" + portal.name());
		final var console_menu = new Menu(get_context(), Bukkit.createInventory(null, columns, title));

		console_menu.add(menu_item_select_target(portal));

		// TODO style
		console_menu.add(menu_item_settings());
		console_menu.add(menu_item_unlink_console());
		console_menu.add(menu_item_destroy_portal());

		return console_menu;
	}

	private MenuWidget menu_item_settings() {
		return new MenuItem(0, item_settings.item(), (player, menu, self) -> {
			menu.close(player);
			return ClickResult.SUCCESS;
		});
	}

	private MenuWidget menu_item_select_target(final Portal portal) {
		if (portal.target_locked()) {
			return new MenuItem(4, item_select_target_locked.item(), (player, menu, self) -> {
				return ClickResult.ERROR;
			});
		} else {
			return new MenuItem(4, item_select_target.item(), (player, menu, self) -> {
				menu.close(player);
				return ClickResult.SUCCESS;
			});
		}
	}

	private MenuWidget menu_item_unlink_console() {
		return new MenuItem(7, item_unlink_console.item(), (player, menu, self) -> {
			menu.close(player);
			return ClickResult.SUCCESS;
		});
	}

	private MenuWidget menu_item_destroy_portal() {
		return new MenuItem(8, item_destroy_portal.item(), (player, menu, self) -> {
			menu.close(player);
			return ClickResult.SUCCESS;
		});
	}

	@Override public void on_enable() {}
	@Override public void on_disable() {}
}
