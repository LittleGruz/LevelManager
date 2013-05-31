package com.littlegruz.levelmanager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.littlegruz.levelmanager.commands.LevelRequirements;
import com.littlegruz.levelmanager.listeners.BlockBreak;
import com.littlegruz.levelmanager.listeners.PlayerCommand;
import com.littlegruz.levelmanager.listeners.PlayerInteract;

public class LevelMain extends JavaPlugin{
   private File levelFile;
   //private HashMap<String, Integer> levelReqsMap;
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

      getCommand("spelllevel").setExecutor(new LevelRequirements(this));
      getCommand("levelcap").setExecutor(new LevelRequirements(this));
      
      //levelReqsMap = new HashMap<String, Integer>();
      shelfMap = new HashMap<Location, String>();
      
      loadLevels();
   }
   
   public void onDisable(){
      saveLevelConfig();
   }
   
   public void loadLevels(){
      
      if(!levelFile.exists()){
         getLogger().warning("No levels.yml file found");
         try{
            levelFile.createNewFile();
         } catch(IOException e){
            getLogger().warning("Error creating levels.yml file");
         }
      }
      
      levelConfig = YamlConfiguration.loadConfiguration(levelFile);
      
      /* Set level cap to 20 if there is no cap set */
      if((levelCap = levelConfig.getInt("max_level")) == 0){
         getLogger().info("No level cap set. Default cap is 20");
         levelCap = 20;
         levelConfig.set("max_level", levelCap);
         saveLevelConfig();
      }
   }
   
   public HashMap<Location, String> getShelfMap(){
      return shelfMap;
   }
   
   public FileConfiguration getLevelConfig(){
      return levelConfig;
   }
   
   public void saveLevelConfig(){
      try{
         levelConfig.save(levelFile);
      } catch(IOException e){
         getLogger().warning("Error saving level.yml file");
      }
   }
   
   public int getLevelCap(){
      return levelCap;
   }
}
