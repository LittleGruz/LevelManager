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
      event.getPlayer().sendMessage(message);
      
      if(message.contains("cast")){
         if(message.contains("teach")){
            String name, spell;
            int levelReq;
            StringTokenizer st = new StringTokenizer(message, " ");
            
            event.getPlayer().sendMessage(st.nextToken()); // Contains "/cast"
            event.getPlayer().sendMessage(st.nextToken()); // Contains "spellbook"
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
            StringTokenizer st = new StringTokenizer(message, " ");
            String spell = "";
            int level;
            
            while(st.hasMoreTokens()){
               spell = st.nextToken();
               if(!spell.contains("cast") && !spell.contains("tome"))
                  break;
            }
            
            event.getPlayer().sendMessage(spell);
            
            if(spell.compareTo("") != 0){
               level = plugin.getLevelConfig().getInt(spell);
               
               if(event.getPlayer().getItemInHand().getType().compareTo(Material.BOOK_AND_QUILL) == 0){
                  /* Setting the durability of the book to be the level requirement of the spell */
                  event.getPlayer().getItemInHand().setDurability((short)level);
               }
            }
         }
         else if(message.contains("spellbook")){
            StringTokenizer st = new StringTokenizer(message, " ");
            
            event.getPlayer().sendMessage(st.nextToken()); // Contains "/cast"
            event.getPlayer().sendMessage(st.nextToken()); // Contains "spellbook"
            
            // TODO Check if null occurs and if MagicSpells checks through other materials
            if(st.hasMoreTokens()){
               if(event.getPlayer().getTargetBlock(null, 20) != null){
                  plugin.getShelfMap().put(event.getPlayer().getTargetBlock(null, 20).getLocation(), st.nextToken());
               }
            }
         }
      }
   }
}
