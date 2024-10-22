package me.rafaelauler.minigame.thepit;

/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.entity.Player;
/*    */ 
/*    */ public class THEPITHELP implements org.bukkit.command.CommandExecutor
/*    */ {
/*    */   private BukkitMain main;
/*    */   
/*    */   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
/*    */   {
/* 13 */     Player p = (Player)sender;
/* 14 */     if (label.equalsIgnoreCase("thepithelp")) {
/* 15 */       p.sendMessage("§b§l\u2605 §e§lTHEPIT Command Help Menu §b§l\u2605");
/* 16 */       p.sendMessage("§a<> = Opcional §b/ §a[] = Required");
/* 17 */       p.sendMessage("§e/thepit §7(View the main command list)");
/* 17 */       p.sendMessage("§e/thepiteditmode §7(Edit the pit arena)");

/*    */     }
/*    */     
/*    */ 
/* 34 */     return false;
/*    */   }
/*    */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\main\KITPVP.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
