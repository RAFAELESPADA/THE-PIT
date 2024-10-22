package me.rafaelauler.minigame.thepit;





import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;





public class ThePitTopKills
implements  CommandExecutor{
	

	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(!(sender instanceof Player)){
			return true;
		}
		final Player p = (Player)sender;
	      if (cmd.getName().equalsIgnoreCase("setthepittopkills")){
	        if (!p.hasPermission("thepit.settopkills")){
	          return true;
	        }
	        BukkitMain.plugin.getConfig().set("TOP.World", p.getLocation().getWorld().getName());
	        BukkitMain.plugin.getConfig().set("TOP.X", Double.valueOf(p.getLocation().getX()));
	        BukkitMain.plugin.getConfig().set("TOP.Y", Double.valueOf(p.getLocation().getY()));
	        BukkitMain.plugin.getConfig().set("TOP.Z", Double.valueOf(p.getLocation().getZ()));
	        BukkitMain.plugin.saveConfig();
	        BukkitMain.loadTopPlayersHologram();
	        p.sendMessage(MainCommand.NomeServer + "§cYou seted the top kills to your current location.");
	        return true;
	      }
		return false;
	}
	}

