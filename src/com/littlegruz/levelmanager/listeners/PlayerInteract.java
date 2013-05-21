package com.littlegruz.levelmanager.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.littlegruz.levelmanager.LevelMain;

public class PlayerInteract implements Listener{
   private LevelMain plugin;
   
   public PlayerInteract(LevelMain instance){
      plugin = instance;
   }

   @EventHandler
   public void onPlayerInteract(PlayerInteractEvent event){
      if(event.getClickedBlock() != null){
         // Check if the right clicked block is a bookshelf
         if(event.getClickedBlock().getType().compareTo(Material.BOOKSHELF) == 0
               && event.getAction().compareTo(Action.RIGHT_CLICK_BLOCK) == 0){
            // TODO Can the action replace the null clickedblock check?
            /* If the bookshelf has a spell, then determine if the player has a
             * high enough level to learn it */
            if(plugin.getShelfMap().get(event.getClickedBlock().getLocation()) != null){
               String spell = plugin.getShelfMap().get(event.getClickedBlock().getLocation());
               int levelReq = plugin.getLevelConfig().getInt(spell);
               
               if(levelReq > event.getPlayer().getLevel()){
                  event.getPlayer().sendMessage("Your level is too low to learn this spell");
                  event.setCancelled(true);
               }
            }
         }
      }
      
      // TODO Check for right clicking (reading) a spell book
      
      // TODO Perhaps use item meta data to find the right book?
      event.getPlayer().getItemInHand().getItemMeta().hasLore();
   }
}
