package de.litemc.litemccbsystem.Manager.Menu.Menus;

import de.litemc.litemccbsystem.Main;
import de.litemc.litemccbsystem.Manager.Menu.System.Menu;
import de.litemc.litemccbsystem.Manager.Menu.System.PlayerMenuUtility;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class WarpCommandMenu extends Menu {
    public WarpCommandMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Warp Menu";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        switch (e.getCurrentItem().getType()) {
            case GRASS_BLOCK:
                e.getWhoClicked().closeInventory();
                if (Main.getInstance().getPlayerManager().getPlayerLevel(playerMenuUtility.getOwner()) >= 0) {
                    Location loc = new Location(Main.getInstance().getServer().getWorld(Objects.requireNonNull(Main.getInstance().getCustomConfig().getString("Commands.WarpCommand.FarmWorld.World"))), Main.getInstance().getCustomConfig().getDouble("Commands.WarpCommand.FarmWorld.X"), Main.getInstance().getCustomConfig().getDouble("Commands.WarpCommand.FarmWorld.Y"), Main.getInstance().getCustomConfig().getDouble("Commands.WarpCommand.FarmWorld.Z"), Main.getInstance().getCustomConfig().getInt("Commands.WarpCommand.FarmWorld.Yaw"), Main.getInstance().getCustomConfig().getInt("Commands.WarpCommand.FarmWorld.Pitch"));
                    e.getWhoClicked().teleport(loc);
                } else {
                    e.getWhoClicked().sendMessage("§cDu benötigst Level §e0 §cum in die Farmwelt zu kommen!");
                }
                break;
            case NETHERRACK:
                e.getWhoClicked().closeInventory();
                if (Main.getInstance().getPlayerManager().getPlayerLevel(playerMenuUtility.getOwner()) >= 10) {
                    Location loc = new Location(Main.getInstance().getServer().getWorld(Objects.requireNonNull(Main.getInstance().getCustomConfig().getString("Commands.WarpCommand.Nether.World"))), Main.getInstance().getCustomConfig().getDouble("Commands.WarpCommand.Nether.X"), Main.getInstance().getCustomConfig().getDouble("Commands.WarpCommand.Nether.Y"), Main.getInstance().getCustomConfig().getDouble("Commands.WarpCommand.Nether.Z"), Main.getInstance().getCustomConfig().getInt("Commands.WarpCommand.Nether.Yaw"), Main.getInstance().getCustomConfig().getInt("Commands.WarpCommand.Nether.Pitch"));
                    e.getWhoClicked().teleport(loc);
                } else {
                    e.getWhoClicked().sendMessage("§cDu benötigst Level §e10 §cum in den Nether zu kommen!");
                }
                break;
            case END_STONE:
                e.getWhoClicked().closeInventory();
                if (Main.getInstance().getPlayerManager().getPlayerLevel(playerMenuUtility.getOwner()) >= 20) {
                    Location loc = new Location(Main.getInstance().getServer().getWorld(Objects.requireNonNull(Main.getInstance().getCustomConfig().getString("Commands.WarpCommand.End.World"))), Main.getInstance().getCustomConfig().getDouble("Commands.WarpCommand.End.X"), Main.getInstance().getCustomConfig().getDouble("Commands.WarpCommand.End.Y"), Main.getInstance().getCustomConfig().getDouble("Commands.WarpCommand.End.Z"), Main.getInstance().getCustomConfig().getInt("Commands.WarpCommand.End.Yaw"), Main.getInstance().getCustomConfig().getInt("Commands.WarpCommand.End.Pitch"));
                    e.getWhoClicked().teleport(loc);
                } else {
                    e.getWhoClicked().sendMessage("§cDu benötigst Level §e20 §cum in das End zu kommen!");
                }
                break;
        }
    }

    @Override
    public void setMenuItems() {
        ItemStack world = new ItemStack(Material.GRASS_BLOCK, 1);
        ItemMeta worldMeta = world.getItemMeta();
        if (worldMeta != null) {
            worldMeta.setDisplayName("§2Farmwelt");
            if (Main.getInstance().getPlayerManager().getPlayerLevel(playerMenuUtility.getOwner()) >= 0) {
                List<String> lore = Arrays.asList("§aBenötigtes Level: §e0");
                worldMeta.setLore(lore);
            } else {
                List<String> lore = Arrays.asList("§cBenötigtes Level: §e0");
                worldMeta.setLore(lore);
            }
            world.setItemMeta(worldMeta);
        }
        inventory.setItem(11, world);

        ItemStack nether = new ItemStack(Material.NETHERRACK, 1);
        ItemMeta netherMeta = nether.getItemMeta();
        if (netherMeta != null) {
            netherMeta.setDisplayName("§4Nether");
            if (Main.getInstance().getPlayerManager().getPlayerLevel(playerMenuUtility.getOwner()) >= 10) {
                List<String> lore = Arrays.asList("§aBenötigtes Level: §e10");
                netherMeta.setLore(lore);
            } else {
                List<String> lore = Arrays.asList("§cBenötigtes Level: §e10");
                netherMeta.setLore(lore);
            }
            nether.setItemMeta(netherMeta);
        }
        inventory.setItem(13, nether);

        ItemStack end = new ItemStack(Material.END_STONE, 1);
        ItemMeta endMeta = end.getItemMeta();
        if (endMeta != null) {
            endMeta.setDisplayName("§1End");
            if (Main.getInstance().getPlayerManager().getPlayerLevel(playerMenuUtility.getOwner()) >= 20) {
                List<String> lore = Arrays.asList("§aBenötigtes Level: §e20");
                endMeta.setLore(lore);
            } else {
                List<String> lore = Arrays.asList("§cBenötigtes Level: §e20");
                endMeta.setLore(lore);
            }
            end.setItemMeta(endMeta);
        }
        inventory.setItem(15, end);

        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
        ItemMeta playerHeadMeta = playerHead.getItemMeta();
        if (playerHeadMeta != null) {
            playerHeadMeta.setDisplayName("§6Account");
            ArrayList<String> lore = new ArrayList<String>();
            lore.add("§2Aktuelles Level: §e" + Main.getInstance().getPlayerManager().getPlayerLevel(playerMenuUtility.getOwner()));
            playerHeadMeta.setLore(lore);
            playerHead.setItemMeta(playerHeadMeta);
        }
        SkullMeta skullMeta = (SkullMeta) playerHead.getItemMeta();
        if (skullMeta != null) {
            String playerName = playerMenuUtility.getOwner().getName();
            if (skullMeta.setOwner(playerName)) {
                playerHead.setItemMeta(skullMeta);
            }
        }
        inventory.setItem(26, playerHead);

        setFillerGlass();
    }
}
