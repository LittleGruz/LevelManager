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
      if(cmd.getName().compareToIgnoreCase("spelllevel") == 0){
         if(args.length == 2){
            plugin.getLevelConfig().set(args[0], args[1]);
            
            if(plugin.getLevelConfig().get(args[0]) != null)
               sender.sendMessage("Spell level requirement changed");
            else
               sender.sendMessage("Spell level requirement created");
            
            plugin.saveLevelConfig();
         }
         else
            sender.sendMessage("Wrong number of arguments");
      }
      return false;
   }

}
