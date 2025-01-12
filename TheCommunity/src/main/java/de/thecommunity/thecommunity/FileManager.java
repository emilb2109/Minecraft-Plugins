package de.thecommunity.thecommunity;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private static File file = new File("plugins/TheCommunity/");
    private static  File cfg = new File("plugins/TheCommunity/config.yml");
    private static File cfgp = new File("plugins/TheCommunity/player.yml");

    private static YamlConfiguration cfg2;
    private static YamlConfiguration cfg2p;

    public  static void setup(){
        if (!file.exists()) {
            file.mkdir();
        }

        if (!cfg.exists()){
            try {
                cfg.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!cfgp.exists()){
            try {
                cfgp.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        cfg2 = YamlConfiguration.loadConfiguration(cfg);
        cfg2p = YamlConfiguration.loadConfiguration(cfgp);
    }

    public static YamlConfiguration getCfg2() {
        return cfg2;
    }
    public static YamlConfiguration getCfg2p() {
        return cfg2p;
    }

    public  static  void saveAllFiles(){
        try{
            cfg2.save(cfg);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public  static  void saveAllFilesP(){
        try{
            cfg2p.save(cfgp);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
