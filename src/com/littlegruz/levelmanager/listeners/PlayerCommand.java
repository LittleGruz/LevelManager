package com.littlegruz.levelmanager.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import com.littlegruz.levelmanager.LevelMain;

public class PlayerCommand implements Listener{
   private LevelMain plugin;
   
   public PlayerCommand(LevelMain instance){
      plugin = instance;
   }

   @EventHandler
   public void onPlayerCommand(PlayerCommandPreprocessEvent event){
      plugin.getServer().broadcastMessage("Triggered");
   }
}
