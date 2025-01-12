package de.emilb2109.casesystem.listener;

import de.emilb2109.casesystem.Main;
import de.emilb2109.casesystem.manager.PlayerStats;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityInteractEvent;

import java.util.Objects;

public class CaseOpeningSystem implements Listener {

    Player player;

    private int casenum = 1;

    @EventHandler
    public void onClickArmor(EntityInteractEvent event) {
        PlayerStats playerStats = Main.getInstance().getStatsManager().getPlayerStats(player.getUniqueId());
        if(event.getEntityType() == EntityType.ARMOR_STAND) {
            if(event.getEntity().getLocation() == Main.getInstance().getMethod().locationFromString(Objects.requireNonNull(Main.getInstance().getConfig().getString("case.open.case")))) {
                event.setCancelled(true);
                if (casenum == 1) {
                    if (playerStats.getVote() > 0) {
                        player.sendMessage("Open Case Vote");
                        playerStats.setVote(playerStats.getVote() - 1);
                        Main.getInstance().getStatsManager().savePlayerStats(playerStats, false);
                    }
                }
                if(casenum == 2) {
                    if (playerStats.getSupreme() > 0) {
                        player.sendMessage("Open Case Supreme");
                        playerStats.setSupreme(playerStats.getSupreme() - 1);
                        Main.getInstance().getStatsManager().savePlayerStats(playerStats, false);
                    }
                }
                if(casenum == 3){
                    if (playerStats.getMega() > 0) {
                        player.sendMessage("Open Case Mega");
                        playerStats.setMega(playerStats.getMega() - 1);
                        Main.getInstance().getStatsManager().savePlayerStats(playerStats, false);
                    }
                }
            }
            if(event.getEntity().getLocation() == Main.getInstance().getMethod().locationFromString(Objects.requireNonNull(Main.getInstance().getConfig().getString("Case.open.weiter")))) {
                event.setCancelled(true);
                if(casenum == 3) {
                    casenum = 1;
                }
                else {
                    casenum = casenum + 1;
                }
            }
            if(event.getEntity().getLocation() == Main.getInstance().getMethod().locationFromString(Objects.requireNonNull(Main.getInstance().getConfig().getString("Case.open.zurueck")))) {
                event.setCancelled(true);
                if(casenum == 1) {
                    casenum = 3;
                }
                else {
                    casenum = casenum - 1;
                }
            }
        }
    }
}
