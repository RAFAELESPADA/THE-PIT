package me.rafaelauler.minigame.thepit;


import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;


public final class ThePitEditMode
  implements Listener, CommandExecutor
{
  public static ArrayList<Player> embuild = new ArrayList();
  
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    Player p = (Player)sender;
    if (cmd.getName().equalsIgnoreCase("thepiteditmode")) {
      if (p.hasPermission("thepit.editmode"))
      {
        if (args.length == 0)
        {
          if (!embuild.contains(p))
          {
            embuild.add(p);
            p.sendMessage(String.valueOf(MainCommand.NomeServer) + "§aYou can now edit the thepit arena");
          }
          else
          {
            embuild.remove(p);
            p.sendMessage(String.valueOf(MainCommand.NomeServer) + "§cYou can no longer edit the thepit arena");
          }
        }
        else
        {
          Player t = Bukkit.getPlayer(args[0]);
          if (t == null)
          {
            p.sendMessage(MainCommand.NomeServer + "§cThis player is offline");
            return true;
          }
          if (!embuild.contains(t))
          {
            embuild.add(t);
            p.sendMessage(String.valueOf(MainCommand.NomeServer) + "§aNow: §7" + t.getName() + " §acan edit kitpvp arena");
          }
          else
          {
            embuild.remove(t);
            p.sendMessage(String.valueOf(MainCommand.NomeServer) + "§aPlayer: §7" + t.getName() + " §ano longer can edit kitpvp arena");
          }
        }
      }
      else {
        p.sendMessage(String.valueOf(MainCommand.NomeServer) + "§cYou don't have permission to do this!");
      }
    }
    return false;
  }
  
  @EventHandler
  public void aoconstruir(BlockPlaceEvent e)
  {
    Player p = e.getPlayer();
    if (!embuild.contains(p) && MainCommand.game.contains(e.getPlayer().getName()) && !BukkitMain.getInstance().getConfig().getBoolean("EnableBuildingOnThePit")) {
      e.setCancelled(true);
    }
  }
  
  @EventHandler
  public void aoconstruir(BlockBreakEvent e)
  {
    Player p = e.getPlayer();
    if (!embuild.contains(p) && MainCommand.game.contains(e.getPlayer().getName())  && !BukkitMain.getInstance().getConfig().getBoolean("EnableBuildingOnThePit")) {
      e.setCancelled(true);
    }
  }
}

