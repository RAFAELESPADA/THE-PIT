package me.rafaelauler.minigame.thepit;


import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;




@SuppressWarnings("unused")
public class GoldCommand implements CommandExecutor {
	public static boolean isNumeric(String str) {
		try {
			Integer.parseInt(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("givegold")) {
			if (!sender.hasPermission("thepit.givegold")) {
				sender.sendMessage("You dont have permission");
			} else {
				if (args.length == 0) {
					sender.sendMessage( "§c§l/givegold [player] [amount]");
					return true;
				}
				Player target = Bukkit.getPlayerExact(args[0]);
				if ((target == null) || (!(target instanceof Player))) {
					sender.sendMessage( "§c§lThe Player is offline");
					return true;
				}
				if (isNumeric(args[1])) {
					Integer coins = Integer.parseInt(args[1]);
				
					Coins.addCoins(target, coins);
					WavePlayer t1 = WaveBukkit.getInstance().getPlayerManager().getPlayer(target.getName());
				 WaveBukkit.getInstance().getPlayerManager().getController().save(t1);
					sender.sendMessage( "§eYou give the player " + target.getName() + "" + coins
							+ "§b Coins");
					target.sendMessage( "§eYou receive " + coins
							+ " §bGOLD");
					target.sendMessage( "§6Your balance is updated!");
					
				}
			}
		}
		return false;
	}
}

		


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\Coins\Commands.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */

