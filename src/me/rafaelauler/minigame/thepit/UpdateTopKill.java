package me.rafaelauler.minigame.thepit;





import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;





public class UpdateTopKill
implements  CommandExecutor{
	

	
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if(!(sender instanceof Player)){
			return true;
		}
		final Player p = (Player)sender;
	      if (cmd.getName().equalsIgnoreCase("updatethepittopkill")){
	        if (!p.hasPermission("thepit.settopkills")){
	          return true;
	        }
	        BukkitMain.loadTopPlayersHologram();
	        p.sendMessage(MainCommand.NomeServer + "§cTop Reloaded.");
	        return true;
	      }
		return false;
	}
	}
