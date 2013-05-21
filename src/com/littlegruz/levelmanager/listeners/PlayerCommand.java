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
      
      if(message.contains("cast") && message.contains("teach")){
         String name, spell;
         StringTokenizer st = new StringTokenizer(message, " ");
         
         st.nextToken();
         st.nextToken();
         name = st.nextToken();
         spell = st.nextToken();
         
         
      }
   }
}
