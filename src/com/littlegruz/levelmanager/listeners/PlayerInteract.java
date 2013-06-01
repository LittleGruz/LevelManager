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
         /* Check if the right clicked block is a bookshelf */
         if(event.getClickedBlock().getType().compareTo(Material.BOOKSHELF) == 0
               && event.getAction().compareTo(Action.RIGHT_CLICK_BLOCK) == 0){
            // TODO Should the action replace the null clickedblock check?
            /* If the bookshelf has a spell, then determine if the player has a
             * high enough level to learn it */
            if(plugin.getShelfMap().get(event.getClickedBlock().getLocation()) != null){
               int levelReq;
               String spell = plugin.getShelfMap().get(event.getClickedBlock().getLocation());
               
               if(plugin.getLevelConfig().get(spell) != null)
                  levelReq = plugin.getLevelConfig().getInt(spell);
               else
                  levelReq = plugin.getLevelCap();
               
               if(levelReq > event.getPlayer().getLevel()){
                  event.getPlayer().sendMessage("Your level is too low to learn this spell");
                  event.setCancelled(true);
               }
            }
         }
      }
      
      if(event.getPlayer().getItemInHand().getType().compareTo(Material.DIAMOND) == 0)
         event.getPlayer().setLevel(event.getPlayer().getLevel() + 1);
      else if(event.getPlayer().getItemInHand().getType().compareTo(Material.SAND) == 0)
         event.getPlayer().setLevel(8);
      
      /* Check book durability (level cap) if the player tries to use it */
      if(event.getPlayer().getItemInHand().getType().compareTo(Material.BOOK_AND_QUILL) == 0){
         if(event.getPlayer().getItemInHand().getDurability() > event.getPlayer().getLevel()){
            event.getPlayer().sendMessage("Your level is too low to use this book");
            event.setCancelled(true);
         }
      }
   }
}
