package com.littlegruz.levelmanager;

import java.io.File;
import java.util.HashMap;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.littlegruz.levelmanager.listeners.PlayerCommand;

public class LevelMain extends JavaPlugin{
   private File levelFile;
   private HashMap<String, Integer> levelReqs;
   private int levelCap;
   
   public void onEnable(){
      // Create the directory if needed
      new File(getDataFolder().toString()).mkdir();
      levelFile = new File(getDataFolder().toString() + "/levels.yml");

      getServer().getPluginManager().registerEvents(new PlayerCommand(this), this);
      
      loadLevels();
   }
   
   public void onDisable(){
      
   }
   
   public void loadLevels(){
      
      if(levelFile.exists()){
         FileConfiguration levelConfig;
         
         levelConfig = YamlConfiguration.loadConfiguration(levelFile);
         
         levelReqs.put("firenova", Integer.valueOf(levelConfig.getInt("firenova")));
      }
      else{
         getLogger().info("No levels.yml file found");
      }
   }
   
   public HashMap<String, Integer> getLevelRequirementsMap(){
      return levelReqs;
   }
   
   public int getLevelCap(){
      return levelCap;
   }
}
