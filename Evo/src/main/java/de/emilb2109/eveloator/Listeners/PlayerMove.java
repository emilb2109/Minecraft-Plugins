package de.emilb2109.eveloator.Listeners;
import org.bukkit.Location;

import org.bukkit.Material;

import org.bukkit.block.Block;

import org.bukkit.block.BlockFace;

import org.bukkit.entity.Player;

import org.bukkit.event.EventHandler;

import org.bukkit.event.Listener;

import org.bukkit.event.player.PlayerMoveEvent;


public final class PlayerMove implements Listener {

    @EventHandler

    private void onJump(PlayerMoveEvent event) {

        final Player player = event.getPlayer();

        final Location location = player.getLocation();

        final Block block = location.getBlock();


        if(event.getFrom().getY() < event.getTo().getY() && block.getType() == Material.DAYLIGHT_DETECTOR) {

            for (int i = 1; i + location.getY() <= 255; ++i) {

                if (block.getRelative(BlockFace.UP, i).getType() == Material.DAYLIGHT_DETECTOR

                        && block.getRelative(BlockFace.UP, i + 1).getType() == Material.AIR

                        && block.getRelative(BlockFace.UP, i + 2).getType() == Material.AIR) {

                    final Location target = player.getLocation();

                    target.setY(location.getBlockY() + i + .375);


                    player.teleport(target);

                    break;

                }

            }

        }

    }

}
