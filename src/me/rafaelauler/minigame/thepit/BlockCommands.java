package me.rafaelauler.minigame.thepit;

import org.bukkit.event.EventHandler;
/*    */ import org.bukkit.event.player.PlayerCommandPreprocessEvent;
/*    */ 
/*    */ public class BlockCommands implements org.bukkit.event.Listener
/*    */ {
/*    */   private BukkitMain main;
/*    */   static BukkitMain plugin;
/*    */   
/*    */   public BlockCommands(BukkitMain main)
/*    */   {
/* 15 */     this.main = main;
/* 16 */     plugin = main;
/*    */   }
/*    */   

/*    */   
@EventHandler
public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e) {
  if (!e.getPlayer().hasPermission("thepit.unblockedcmds") && !e.getPlayer().hasPermission("thepit.*") && !e.getPlayer().isOp() && (MainCommand.game.contains(e.getPlayer().getName()) && (BukkitMain.getInstance().getConfig().getString("EnableCommandBlockingInThePit").equalsIgnoreCase("true")))) {
	  java.util.List<String> list = BukkitMain.plugin.getConfig().getStringList("BLOCKED_COMMANDS");
    list.stream().filter(cmd -> e.getMessage().toLowerCase().contains(cmd.toLowerCase())).forEach(msg -> {
          e.setCancelled(true);
          e.getPlayer().closeInventory();
          e.getPlayer().sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + String.valueOf(this.main.getConfig().getString("Message.BlockedCMD-OnThePit").replace("&", "§")));
        });
  } 
}
@EventHandler
public void onPlayerCommandPreproctess(PlayerCommandPreprocessEvent e) {
  if (!e.getPlayer().hasPermission("kitpvp.unblockedcmds") && !e.getPlayer().hasPermission("kitpvp.*") && !e.getPlayer().isOp() && (MainCommand.game.contains(e.getPlayer().getName()) && (BukkitMain.getInstance().getConfig().getString("EnableOnlyCommandsAllowedInKitPvP").equalsIgnoreCase("true")))) {
	  java.util.List<String> list = BukkitMain.plugin.getConfig().getStringList("ALLOWED_COMMANDS");
  if (!list.contains(e.getMessage())) {
          e.setCancelled(true);
          e.getPlayer().closeInventory();
          e.getPlayer().sendMessage(String.valueOf(this.main.getConfig().getString("Prefix").replace("&", "§")) + String.valueOf(this.main.getConfig().getString("Message.BlockedCMD-OnKitPvP").replace("&", "�")));
  }
  } 
}
}
/*    */ 


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\main\BlockCommands.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */

