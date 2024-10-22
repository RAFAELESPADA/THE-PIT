package me.rafaelauler.minigame.thepit;



import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;



public class AntiDeathDrop
  implements Listener
{
  private BukkitMain main;
  
  public AntiDeathDrop(BukkitMain main)
  {
    this.main = main;
  }
  
  @EventHandler
  public void onDeath(PlayerRespawnEvent e)
  {
    if (((e.getPlayer() instanceof Player)) && ((e.getPlayer().getKiller() instanceof Player)))
    {
      Player p = e.getPlayer();
      Player k = p.getKiller();
      if (MainCommand.game.contains(p.getName()))
      {
     
      if ((this.main.getConfig().getString("RespawnSound").equalsIgnoreCase("true")) && (MainCommand.game.contains(p.getName()))) {
        p.playSound(p.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.Respawn")), 1.0F, 1.0F);
      }
      }}
    }
  
  @EventHandler
  public void onDeatht(PlayerDeathEvent e)
  {
	  Player p = e.getEntity();
	  if (MainCommand.game.contains(p.getName()))
      {
		  e.getDrops().clear(); 
      }
  }


	


	  public static int GetKills(Player p)
	  {
		  if (p == null) {
			  return 0;
		  }
		  WavePlayer killerHelixPlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
			
	      
	    return killerHelixPlayer.getPvp().getKills();
	  }
	 
	  
	  public static int GetDeaths(Player p)
	  {
		  if (p == null) {
			  return 0;
		  }
		  WavePlayer killerHelixPlayer = WaveBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
			
	      
		    return killerHelixPlayer.getPvp().getDeaths();
	  }
}

