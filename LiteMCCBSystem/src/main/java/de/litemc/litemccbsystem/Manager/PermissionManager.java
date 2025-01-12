package de.litemc.litemccbsystem.Manager;

import org.bukkit.Bukkit;

public class PermissionManager {

    public static void setPermission(String permission) {
        try {
            Bukkit.getPluginManager().addPermission(new org.bukkit.permissions.Permission(permission));
        } catch (IllegalArgumentException e) {
            //e
        }

    }

}
