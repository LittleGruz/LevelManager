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

      // Instant spells
      /*levelReqsMap.put("confusion", Integer.valueOf(levelConfig.getInt("confusion")));
      levelReqsMap.put("conjure", Integer.valueOf(levelConfig.getInt("conjure")));
      levelReqsMap.put("dowse", Integer.valueOf(levelConfig.getInt("dowse")));
      levelReqsMap.put("enderchest", Integer.valueOf(levelConfig.getInt("enderchest")));
      levelReqsMap.put("firenova", Integer.valueOf(levelConfig.getInt("firenova")));
      levelReqsMap.put("food", Integer.valueOf(levelConfig.getInt("food")));
      levelReqsMap.put("forcepush", Integer.valueOf(levelConfig.getInt("forcepush")));
      levelReqsMap.put("freeze", Integer.valueOf(levelConfig.getInt("freeze")));
      levelReqsMap.put("gate", Integer.valueOf(levelConfig.getInt("gate")));
      levelReqsMap.put("leap", Integer.valueOf(levelConfig.getInt("leap")));
      levelReqsMap.put("mana", Integer.valueOf(levelConfig.getInt("mana")));
      levelReqsMap.put("mark", Integer.valueOf(levelConfig.getInt("mark")));
      levelReqsMap.put("phase", Integer.valueOf(levelConfig.getInt("phase")));
      levelReqsMap.put("prayer", Integer.valueOf(levelConfig.getInt("prayer")));
      levelReqsMap.put("explosivearrow", Integer.valueOf(levelConfig.getInt("explosivearrow")));
      levelReqsMap.put("purge", Integer.valueOf(levelConfig.getInt("purge")));
      levelReqsMap.put("recall", Integer.valueOf(levelConfig.getInt("recall")));
      levelReqsMap.put("repair", Integer.valueOf(levelConfig.getInt("repair")));
      levelReqsMap.put("summonhelper", Integer.valueOf(levelConfig.getInt("summonhelper")));
      levelReqsMap.put("summon", Integer.valueOf(levelConfig.getInt("summon")));
      levelReqsMap.put("roar", Integer.valueOf(levelConfig.getInt("roar")));
      levelReqsMap.put("anvil", Integer.valueOf(levelConfig.getInt("anvil")));
      levelReqsMap.put("sun", Integer.valueOf(levelConfig.getInt("sun")));
      levelReqsMap.put("wall", Integer.valueOf(levelConfig.getInt("wall")));

      // Targeted spells
      levelReqsMap.put("storm", Integer.valueOf(levelConfig.getInt("storm")));
      levelReqsMap.put("blink", Integer.valueOf(levelConfig.getInt("blink")));
      levelReqsMap.put("build", Integer.valueOf(levelConfig.getInt("build")));
      levelReqsMap.put("combust", Integer.valueOf(levelConfig.getInt("combust")));
      levelReqsMap.put("cripple", Integer.valueOf(levelConfig.getInt("cripple")));
      levelReqsMap.put("disarm", Integer.valueOf(levelConfig.getInt("disarm")));
      levelReqsMap.put("drainlife", Integer.valueOf(levelConfig.getInt("drainlife")));
      levelReqsMap.put("explode", Integer.valueOf(levelConfig.getInt("explode")));
      levelReqsMap.put("farm", Integer.valueOf(levelConfig.getInt("farm")));
      levelReqsMap.put("fireball", Integer.valueOf(levelConfig.getInt("fireball")));
      levelReqsMap.put("forcebomb", Integer.valueOf(levelConfig.getInt("forcebomb")));
      levelReqsMap.put("forcetoss", Integer.valueOf(levelConfig.getInt("forcetoss")));
      levelReqsMap.put("geyser", Integer.valueOf(levelConfig.getInt("geyser")));
      levelReqsMap.put("heal", Integer.valueOf(levelConfig.getInt("heal")));
      levelReqsMap.put("levitate", Integer.valueOf(levelConfig.getInt("levitate")));
      levelReqsMap.put("lightning", Integer.valueOf(levelConfig.getInt("lightning")));
      levelReqsMap.put("water", Integer.valueOf(levelConfig.getInt("water")));
      levelReqsMap.put("pain", Integer.valueOf(levelConfig.getInt("pain")));
      levelReqsMap.put("poison", Integer.valueOf(levelConfig.getInt("poison")));
      levelReqsMap.put("stormblock", Integer.valueOf(levelConfig.getInt("stormblock")));
      levelReqsMap.put("shadowstep", Integer.valueOf(levelConfig.getInt("shadowstep")));
      levelReqsMap.put("silence", Integer.valueOf(levelConfig.getInt("silence")));
      levelReqsMap.put("wolf", Integer.valueOf(levelConfig.getInt("wolf")));
      levelReqsMap.put("summon", Integer.valueOf(levelConfig.getInt("summon")));
      levelReqsMap.put("switch", Integer.valueOf(levelConfig.getInt("switch")));
      levelReqsMap.put("telekinesis", Integer.valueOf(levelConfig.getInt("telekinesis")));
      levelReqsMap.put("tree", Integer.valueOf(levelConfig.getInt("tree")));
      levelReqsMap.put("volley", Integer.valueOf(levelConfig.getInt("volley")));
      levelReqsMap.put("zap", Integer.valueOf(levelConfig.getInt("zap")));
      
      // Buff spells
      levelReqsMap.put("armor", Integer.valueOf(levelConfig.getInt("armor")));
      levelReqsMap.put("empower", Integer.valueOf(levelConfig.getInt("empower")));
      levelReqsMap.put("flamewalk", Integer.valueOf(levelConfig.getInt("flamewalk")));
      levelReqsMap.put("gills", Integer.valueOf(levelConfig.getInt("gills")));
      levelReqsMap.put("haste", Integer.valueOf(levelConfig.getInt("haste")));
      levelReqsMap.put("invisibility", Integer.valueOf(levelConfig.getInt("invisibility")));
      levelReqsMap.put("invulnerability", Integer.valueOf(levelConfig.getInt("invulnerability")));
      levelReqsMap.put("lifewalk", Integer.valueOf(levelConfig.getInt("lifewalk")));
      levelReqsMap.put("lightwalk", Integer.valueOf(levelConfig.getInt("lightwalk")));
      levelReqsMap.put("lilywalk", Integer.valueOf(levelConfig.getInt("lilywalk")));
      levelReqsMap.put("clarity", Integer.valueOf(levelConfig.getInt("clarity")));
      levelReqsMap.put("reach", Integer.valueOf(levelConfig.getInt("reach")));
      levelReqsMap.put("reflect", Integer.valueOf(levelConfig.getInt("reflect")));
      levelReqsMap.put("spellhaste", Integer.valueOf(levelConfig.getInt("spellhaste")));
      levelReqsMap.put("stealth", Integer.valueOf(levelConfig.getInt("stealth")));
      levelReqsMap.put("stonevision", Integer.valueOf(levelConfig.getInt("stonevision")));
      levelReqsMap.put("waterwalk", Integer.valueOf(levelConfig.getInt("waterwalk")));
      levelReqsMap.put("windwalk", Integer.valueOf(levelConfig.getInt("windwalk")));
      
      // Multi spells
      levelReqsMap.put("zeus", Integer.valueOf(levelConfig.getInt("zeus")));
      levelReqsMap.put("smite", Integer.valueOf(levelConfig.getInt("smite")));*/
      
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
