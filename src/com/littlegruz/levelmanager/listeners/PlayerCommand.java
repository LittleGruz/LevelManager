package com.littlegruz.levelmanager.listeners;

import java.util.StringTokenizer;

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
      String message = event.getMessage();
      
      if(message.contains("cast")){
         if(message.contains("teach")){
            String name, spell;
            StringTokenizer st = new StringTokenizer(message, " ");
            
            st.nextToken();
            st.nextToken();
            name = st.nextToken();
            spell = st.nextToken();
            
            // TODO Get required spell level
            
            // TODO Get player level
            
            // TODO Compare
         }
         else if(message.contains("tome")){
            
            // TODO Get spell name
            
            // TODO Get book and quill held (find something unique)
            
            // TODO Store link between spell and book
            
            // TODO Book is read with right click
         }
         else if(message.contains("spellbook")){
            // TODO Get spell name
            
            // TODO Get bookcase block location (check for block destroy)
            
            // TODO Link block location with spell name
         }
      }
   }
}
