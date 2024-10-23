package me.rafaelauler.minigame.thepit;

/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Streak
/*     */   implements Listener
/*     */ {
/*     */   private BukkitMain main;
/*     */   static BukkitMain plugin;
/*     */   
/*     */   public Streak()
/*     */   {
/*  31 */     this.main = this.main;
/*  32 */     plugin = this.main;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  40 */   
/*     */   
/*     */   public void onEnable() {}
/*     */   
/*     */   @EventHandler
/*  45 */   public void playerdeath(PlayerDeathEvent ev) { 
	if (ev.getEntity().getKiller() == null) {
		return;
	}
Player p = ev.getEntity();
Player k = p.getKiller();
boolean isCitizensNPC = p.hasMetadata("NPC");

double killstreak = XP.getXP(k);
/*  46 */     if ((p.getKiller() instanceof Player))
/*     */     {
/*     */ 
/*  49 */     
if (!MainCommand.game.contains(k.getName())) {
	return;
}

WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(k.getName());
	
if (isCitizensNPC && BukkitMain.getInstance().getConfig().getBoolean("BotsKillsAllowed")) {
	Sun8oxData.getPvp().addthepitkills(1);
	if (killstreak % BukkitMain.customization.getInt("XP-Required-To-LevelUP") == 0) {
		Streak.sendToGame(String.valueOf(MainCommand.NomeServer + BukkitMain.messages.getString("LevelUP").replaceAll("%player%", k.getName()).replaceAll("%level%", Integer.toString(Level.getLevel(k)))).replaceAll("&", "§"));
	}
	k.sendMessage(String.valueOf(MainCommand.NomeServer + BukkitMain.getInstance().getConfig().getString("Kill.Tell").replaceAll("%player%", p.getName())));
	XP.addXP(k, BukkitMain.customization.getInt("XPEarned-OnKill"));
	Coins.addCoins(k, BukkitMain.customization.getInt("Earned-Coins-Per-Kill"));               
	k.sendMessage("§a+" + BukkitMain.customization.getInt("XPEarned-OnKill") + "XP");
	k.sendMessage("§a+" + BukkitMain.customization.getInt("Earned-Coins-Per-Kill")  + "COINS");
    WaveBukkit.getInstance().getPlayerManager().getController().save(Sun8oxData);
}

  else if (!isCitizensNPC) {

	  
		  WavePlayer Sun8oxData2 = WaveBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
			
/*  52 */       p.sendMessage(MainCommand.NomeServer + "" + (BukkitMain.messages.getString("StreakDestroyed").replace("&", "§").replace("%player%", k.getName()))); 


/*  53 */       addtokillstreak(k);
BukkitMain.tirarEfeitos(p);        
Sun8oxData.getPvp().addthepitkills(1);
Sun8oxData2.getPvp().addthepitdeaths(1);
Sun8oxData.getPvp().addthepitstreak(1);
  if (BukkitMain.getInstance().getConfig().getBoolean("Commands-ON-KILL-Enabled")) {
	  for (String commands : BukkitMain.getInstance().getConfig().getStringList("Commands-Executed-On-Kill")) {
	  Bukkit.dispatchCommand(Bukkit.getConsoleSender(), commands.replace("%killer%", k.getName()).replace("%killed%", p.getName()));
  }
  }
if (killstreak % BukkitMain.customization.getInt("XP-Required-To-LevelUP") == 0) {
	Streak.sendToGame(String.valueOf(MainCommand.NomeServer + BukkitMain.messages.getString("LevelUP").replaceAll("%player%", k.getName()).replaceAll("%level%", Integer.toString(Level.getLevel(k)))).replaceAll("&", "§"));
}
/*  64 */       int kills2 = Sun8oxData2.getPvp().getKillstreak();
if (kills2 >= 3) {
	broadcast(MainCommand.NomeServer + "" + BukkitMain.messages.getString("KillStreakLostBroadcast").replace("&", "§").replace("%killstreak%", String.valueOf(kills2)).replace("%player%", p.getName()).replace("%killer%", k.getName()) , p.getWorld());
	Streak.sendToGame(String.valueOf(MainCommand.NomeServer + BukkitMain.messages.getString("LevelUP").replaceAll("%player%", k.getName()).replaceAll("%level%", Integer.toString(Level.getLevel(k)))).replaceAll("&", "§"));
}
p.sendMessage(String.valueOf(MainCommand.NomeServer + BukkitMain.getInstance().getConfig().getString("Death.Tell").replaceAll("%player%", k.getName())));
k.sendMessage(String.valueOf(MainCommand.NomeServer + BukkitMain.getInstance().getConfig().getString("Kill.Tell").replaceAll("%player%", p.getName())));
XP.addXP(k, BukkitMain.customization.getInt("XPEarned-OnKill"));
XP.removeXP(p, BukkitMain.customization.getInt("XPLost-OnDeath"));
Coins.addCoins(k, BukkitMain.customization.getInt("Earned-Coins-Per-Kill"));               
Coins.removeCoins(p, BukkitMain.customization.getInt("Lost-Coins-Per-Death"));
p.sendMessage("§cYou died to " + k.getName());
k.sendMessage("§a+" + BukkitMain.customization.getInt("XPEarned-OnKill") + "XP");
k.sendMessage("§a+" + BukkitMain.customization.getInt("Earned-Coins-Per-Kill")  + "COINS");
WaveBukkit.getInstance().getPlayerManager().getController().save(Sun8oxData);
WaveBukkit.getInstance().getPlayerManager().getController().save(Sun8oxData2);
Bukkit.getConsoleSender().sendMessage("§e" + p.getName() + " (" +  ev.getEntityType() + ")" + " has been killed by " + k.getName() + " (" +  ev.getEntity().getKiller().getType() + ")" + " on thepit");
ItemStack maça = new ItemStack(Material.GOLDEN_APPLE);
  k.getInventory().addItem(maça); 
  k.playSound(k.getLocation(), Sound.valueOf(this.main.getConfig().getString("Sound.Respawn")), 1.0F, 1.0F);
  
  }
}
}
/*     */   
/*     
*/  

/*     */ 
/*     */   public static void addtokillstreak(Player killer)
/*     */   {
/*  61 */     String name = killer.getName();
/*  62 */     if (MainCommand.game.contains(name))
/*     */     {


	  WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(killer.getName());
		       int kills = Sun8oxData.getPvp().getKillstreak();
/*     */      Sun8oxData.getPvp().addthepitstreak(1);
if (kills != 0) {
killer.sendMessage(MainCommand.NomeServer + "" + ChatColor.GREEN + "You are on " + ChatColor.RED + Integer.toString(kills) + ChatColor.GREEN + " Killstreak.");
}
/*  69 */       if (kills % 3 == 0 && kills != 0) {

	broadcast(MainCommand.NomeServer + "" + BukkitMain.messages.getString("KillStreakBroadcast").replace("&", "§").replace("%killstreak%", String.valueOf(kills)).replace("%player%", name) , killer.getWorld());
        killer.sendMessage(MainCommand.NomeServer + "" + ChatColor.GOLD + "You have been awarded " + BukkitMain.customization.getDouble("KS-3") + " Coins!");
/*  74 */         Coins.addCoins(killer, BukkitMain.customization.getInt("KS-3"));
/*     */       }
/*  76 */      
/*     */     
}
/*     */   }
/*     */ 

  public static void broadcast(String text, World w){
    for(Player p: w.getPlayers()){
        p.sendMessage(text);
    }
  }
public static void sendToGame(String message) {
    for(String player : MainCommand.game) {
        if(player != null) {
            Player p = Bukkit.getPlayer(player);
            p.sendMessage(message);
        }
    }
}
}