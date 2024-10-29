package me.rafaelauler.minigame.thepit;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class PTKICK implements CommandExecutor {


/*     */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*     */   {
/*  49 */     if (!(sender instanceof Player)) {
/*  50 */       sender.sendMessage("§bYou need to be a player");
/*  51 */       return true;
/*     */     }
/*  53 */     
/*  54 */     if (!sender.hasPermission("thepit.kickall")) {
/*  55 */       sender.sendMessage("§cYou dont have the permission thepit.kickall");
/*  57 */       return true;
/*     */     }
for(Player all: Bukkit.getOnlinePlayers()){
	sender.sendMessage("§cYou forced " + MainCommand.game.size() + " players to leave the THE-PIT");
	all.chat("/thepit leave");
	
}
return true;
}
}
