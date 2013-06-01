package com.littlegruz.levelmanager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.littlegruz.levelmanager.commands.LevelRequirements;
import com.littlegruz.levelmanager.listeners.BlockBreak;
import com.littlegruz.levelmanager.listeners.PlayerCommand;
import com.littlegruz.levelmanager.listeners.PlayerInteract;
import com.littlegruz.levelmanager.listeners.PlayerPickupItem;

public class LevelMain extends JavaPlugin{
   private File levelFile;
   private File shelfFile;
   private HashMap<Location, String> shelfMap;
   private FileConfiguration levelConfig;
   private int levelCap;
   
   public void onEnable(){
      // Create the directory if needed
      new File(getDataFolder().toString()).mkdir();
      levelFile = new File(getDataFolder().toString() + "/levels.yml");
      shelfFile = new File(getDataFolder().toString() + "/spellbooks.dat");

      getServer().getPluginManager().registerEvents(new PlayerCommand(this), this);
      getServer().getPluginManager().registerEvents(new PlayerInteract(this), this);
      getServer().getPluginManager().registerEvents(new BlockBreak(this), this);
      getServer().getPluginManager().registerEvents(new PlayerPickupItem(), this);

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
   
   public void saveBookshelfLocations(){
      BufferedWriter bw;

      try{
         bw = new BufferedWriter(new FileWriter(shelfFile));
         Iterator<Map.Entry<Location, String>> it = shelfMap.entrySet().iterator();
         
         while(it.hasNext()){
            Entry<Location, String> shelf = it.next();
            bw.write(shelf.getKey().getWorld().getName() + " "
                  + Integer.toString(shelf.getKey().getBlockX()) + " "
                  + Integer.toString(shelf.getKey().getBlockY()) + " "
                  + Integer.toString(shelf.getKey().getBlockZ()) + " "
                  + shelf.getValue() + "\n");
         }
         bw.close();
      }catch(IOException e){
         getLogger().info("Error saving spellbook block locations");
      }
   }
   
   public int getLevelCap(){
      return levelCap;
   }
}
