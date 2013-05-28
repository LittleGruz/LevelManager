package com.littlegruz.levelmanager.listeners;

import java.util.StringTokenizer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;

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
            int levelReq;
            StringTokenizer st = new StringTokenizer(message, " ");
            
            st.nextToken(); // Contains "/cast"
            st.nextToken(); // Contains "spellbook"
            name = st.nextToken();
            spell = st.nextToken();
            
            if(plugin.getServer().getPlayer(name) != null){
               levelReq = plugin.getLevelConfig().getInt(spell);
               
               if(levelReq > plugin.getServer().getPlayer(name).getLevel()){
                  event.getPlayer().sendMessage("That players level is too low to learn this spell");
                  event.setCancelled(true);
               }
            }
         }
         else if(message.contains("tome")){
            
            // TODO Get spell name
            
            // TODO Get book and quill held (find something unique)
            
            // TODO Store link between spell and book
            
            // Note: Book and quill is read with right click, upon publishing it becomes a book
         }
         else if(message.contains("spellbook")){
            StringTokenizer st = new StringTokenizer(message, " ");
            
            st.nextToken(); // Contains "/cast"
            st.nextToken(); // Contains "spellbook"
            
            // TODO Check if null occurs and if MagicSpells checks through other materials
            if(st.hasMoreTokens())
               plugin.getShelfMap().put(event.getPlayer().getTargetBlock(null, 20).getLocation(), st.nextToken());
         }
      }
   }

   // TODO check if this fires when book and quill changes from the tome command
   @EventHandler
   public void onPlayerItemHeld(PlayerItemHeldEvent event){
      event.getPlayer().sendMessage("change");
   }
}
