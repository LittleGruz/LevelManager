package com.littlegruz.levelmanager.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.littlegruz.levelmanager.LevelMain;

public class BlockBreak implements Listener{
   private LevelMain plugin;
   
   public BlockBreak(LevelMain instance){
      plugin = instance;
   }

   @EventHandler
   public void onBlockBreak(BlockBreakEvent event){
      // Remove bookshelf from HashMap if it is destroyed
      if(plugin.getShelfMap().get(event.getBlock().getLocation()) != null){
         plugin.getShelfMap().remove(event.getBlock().getLocation());
         event.getPlayer().sendMessage("Spellbook destoryed");
      }
   }
}
