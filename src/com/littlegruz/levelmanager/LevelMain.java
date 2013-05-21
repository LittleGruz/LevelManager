package com.littlegruz.levelmanager;

import java.io.File;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.littlegruz.levelmanager.listeners.BlockBreak;
import com.littlegruz.levelmanager.listeners.PlayerCommand;
import com.littlegruz.levelmanager.listeners.PlayerInteract;

public class LevelMain extends JavaPlugin{
   private File levelFile;
   private HashMap<String, Integer> levelReqsMap;
   private HashMap<Location, String> shelfMap;
   private FileConfiguration levelConfig;
   private int levelCap;
   
   public void onEnable(){
      // Create the directory if needed
      new File(getDataFolder().toString()).mkdir();
      levelFile = new File(getDataFolder().toString() + "/levels.yml");

      getServer().getPluginManager().registerEvents(new PlayerCommand(this), this);
      getServer().getPluginManager().registerEvents(new PlayerInteract(this), this);
      getServer().getPluginManager().registerEvents(new BlockBreak(this), this);
      
      loadLevels();
   }
   
   public void onDisable(){
      
   }
   
   public void loadLevels(){
      
      if(levelFile.exists()){
         levelConfig = YamlConfiguration.loadConfiguration(levelFile);
         
         levelReqsMap.put("firenova", Integer.valueOf(levelConfig.getInt("firenova")));
         
         if((levelCap = levelConfig.getInt("max_level")) == 0)
            levelCap = 20;
      }
      else{
         getLogger().info("No levels.yml file found");
         levelCap = 20;
      }
   }
   
   public HashMap<String, Integer> getLevelRequirementsMap(){
      return levelReqsMap;
   }
   
   public HashMap<Location, String> getShelfMap(){
      return shelfMap;
   }
   
   public FileConfiguration getLevelConfig(){
      return levelConfig;
   }
   
   public int getLevelCap(){
      return levelCap;
   }
}
