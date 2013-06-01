package com.littlegruz.levelmanager.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickupItem implements Listener{
   
   public PlayerPickupItem(){
   }

   @EventHandler
   public void onPlayerPickupItem(PlayerPickupItemEvent event){
      if(event.getItem().getItemStack().getType().compareTo(Material.BOOK_AND_QUILL) == 0){
         if(event.getItem().getItemStack().getDurability() > event.getPlayer().getLevel()){
            event.setCancelled(true);
         }
      }
      else if(event.getItem().getItemStack().getType().compareTo(Material.WRITTEN_BOOK) == 0){
         if(event.getItem().getItemStack().getDurability() > event.getPlayer().getLevel()){
            event.setCancelled(true);
         }
      }
   }
}
