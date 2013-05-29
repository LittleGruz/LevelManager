package com.littlegruz.levelmanager.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.littlegruz.levelmanager.LevelMain;

public class LevelRequirements implements CommandExecutor{
   private LevelMain plugin;
   
   public LevelRequirements(LevelMain instance){
      plugin = instance;
   }

   @Override
   public boolean onCommand(CommandSender sender, Command cmd,
         String commandLabel, String[] args){
      if(sender.hasPermission("manager.commands")){
         if(cmd.getName().compareToIgnoreCase("spelllevel") == 0){
            if(args.length == 2){
               try{
               plugin.getLevelConfig().set(args[0], Integer.parseInt(args[1]));
               
               sender.sendMessage("Spell \"" + args[0] + "\" level requirement is now " + args[1]);
               plugin.saveLevelConfig();
               
               return true;
               }catch(NumberFormatException e){
                  sender.sendMessage("Not a valid number");
               }
            }
            else
               sender.sendMessage("Wrong number of arguments");
         }
         else if(cmd.getName().compareToIgnoreCase("levelcap") == 0){
            if(args.length == 1){
               try{
               plugin.getLevelConfig().set("max_level", Integer.parseInt(args[0]));
               
               sender.sendMessage("Level cap is now " + args[0]);
               plugin.saveLevelConfig();
               
               return true;
               }catch(NumberFormatException e){
                  sender.sendMessage("Not a valid number");
               }
            }
            else
               sender.sendMessage("Wrong number of arguments");
         }
      }
      else{
         sender.sendMessage("Insufficient permissions");
         return true;
      }
      
      return false;
   }

}
