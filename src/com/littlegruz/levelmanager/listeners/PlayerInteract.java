package com.littlegruz.levelmanager.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.littlegruz.levelmanager.LevelMain;

public class PlayerInteract implements Listener{
   private LevelMain plugin;
   
   public PlayerInteract(LevelMain instance){
      plugin = instance;
   }

   @EventHandler
   public void onPlayerInteract(PlayerInteractEvent event){
      // TODO Check for right clicking stored bookshelf
      if(event.getClickedBlock() != null){
         if(event.getClickedBlock().getType().compareTo(Material.BOOKSHELF) == 0){
            plugin.getServer().broadcastMessage("Book'd");
         }
      }
      
      // TODO Check for right clicking (reading) a spell book
      //event.setCancelled(true);
   }
}
