package com.littlegruz.levelmanager.listeners;

import java.util.StringTokenizer;

import org.bukkit.Material;
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
            int levelReq;
            StringTokenizer st = new StringTokenizer(message, " ");
            
            st.nextToken(); // Contains "/cast"
            st.nextToken(); // Contains "spellbook"
            name = st.nextToken();
            spell = st.nextToken();
            
            if(plugin.getServer().getPlayer(name) != null){
               if(plugin.getLevelConfig().get(spell) != null)
                  levelReq = plugin.getLevelConfig().getInt(spell);
               else
                  levelReq = plugin.getLevelCap();
               
               if(levelReq > plugin.getServer().getPlayer(name).getLevel()){
                  event.getPlayer().sendMessage("That players level is too low to learn this spell");
                  event.setCancelled(true);
               }
            }
         }
         else if(message.contains("tome")){
            StringTokenizer st = new StringTokenizer(message, " ");
            String spell = "";
            int levelReq;
            
            while(st.hasMoreTokens()){
               spell = st.nextToken();
               if(!spell.contains("cast") && !spell.contains("tome"))
                  break;
            }
            
            if(spell.compareTo("") != 0){
               if(plugin.getLevelConfig().get(spell) != null)
                  levelReq = plugin.getLevelConfig().getInt(spell);
               else
                  levelReq = plugin.getLevelCap();
               
               if(event.getPlayer().getItemInHand().getType().compareTo(Material.BOOK_AND_QUILL) == 0){
                  /* Setting the durability of the book to be the level requirement of the spell */
                  event.getPlayer().getItemInHand().setDurability((short)levelReq);
               }
            }
         }
         else if(message.contains("spellbook")){
            StringTokenizer st = new StringTokenizer(message, " ");
            
            st.nextToken(); // Contains "/cast"
            st.nextToken(); // Contains "spellbook"
            
            if(st.hasMoreTokens()){
               if(event.getPlayer().getTargetBlock(null, 20) != null){
                  plugin.getShelfMap().put(event.getPlayer().getTargetBlock(null, 20).getLocation(), st.nextToken());
               }
            }
         }
      }
   }
}
