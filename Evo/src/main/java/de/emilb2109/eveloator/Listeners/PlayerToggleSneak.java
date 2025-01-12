package de.emilb2109.eveloator.Listeners;
import org.bukkit.Location;

import org.bukkit.Material;

import org.bukkit.block.Block;

import org.bukkit.block.BlockFace;

import org.bukkit.block.DaylightDetector;
import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;

import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerToggleSneakEvent;


public final class PlayerToggleSneak implements Listener {

    @EventHandler

    private void onSneak(PlayerToggleSneakEvent event) {

        final Player player = event.getPlayer();

        final Location location = player.getLocation();

        final Block block = location.getBlock();


        if (event.isSneaking() && block.getType() == Material.DAYLIGHT_DETECTOR) {
            DaylightDetector day = (DaylightDetector) block.getBlockData();


                for (int i = 1; i < location.getBlockY(); ++i) {

                    if (block.getRelative(BlockFace.DOWN, i).getType() == Material.DAYLIGHT_DETECTOR

                            && block.getRelative(BlockFace.DOWN, i - 1).getType() == Material.AIR

                            && block.getRelative(BlockFace.DOWN, i - 2).getType() == Material.AIR) {

                        final Location target = player.getLocation();

                        target.setY(location.getBlockY() - i + .375);


                        player.teleport(target);

                        break;

                    }

                }

        }

    }

}


