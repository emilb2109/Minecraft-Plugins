package de.emilb2109.casesystem.util;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

public class Method {

    public String saveLocationToString(Location location) {
        return location.getWorld().getName() + ";" + location.getX() + ";" + location.getY() + ";" + location.getZ() + ";" + location.getYaw() + ";" + location.getPitch();
    }

    public Location locationFromString(String locationString) {
        if (!locationString.contains(";")) {
            return null;
        }
        String[] arrayString = locationString.split(";");
        if(arrayString.length != 6) {
            return null;
        }
        World world = Bukkit.getWorld(arrayString[0]);

        double x = Double.parseDouble(arrayString[1]);
        double y = Double.parseDouble(arrayString[2]);
        double z = Double.parseDouble(arrayString[3]);

        float yaw = Float.parseFloat(arrayString[4]);
        float pitch = Float.parseFloat(arrayString[5]);

        return new Location(world, x, y, z, yaw, pitch);
    }
}
