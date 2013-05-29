package com.littlegruz.levelmanager.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLevelChangeEvent;

import com.littlegruz.levelmanager.LevelMain;

public class PlayerLevel implements Listener{
   private LevelMain plugin;
   
   public PlayerLevel(LevelMain instance){
      plugin = instance;
   }

   @EventHandler
   public void onPlayerLevel(PlayerLevelChangeEvent event){
      if(event.getNewLevel() > plugin.getLevelCap())
         event.getPlayer().setLevel(plugin.getLevelCap());
   }
}
