/*    */ package me.RafaelAulerDeMeloAraujo.TitleAPI;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ public class TabTitleSendEvent extends org.bukkit.event.Event
/*    */ {
/*  8 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final Player player;
/*    */   private String header;
/*    */   private String footer;
/* 12 */   private boolean cancelled = false;
/*    */   
/*    */   public TabTitleSendEvent(Player player, String header, String footer) {
/* 15 */     this.player = player;
/* 16 */     this.header = header;
/* 17 */     this.footer = footer;
/*    */   }
/*    */   
/*    */   public HandlerList getHandlers()
/*    */   {
/* 22 */     return handlers;
/*    */   }
/*    */   
/*    */   public static HandlerList getHandlerList() {
/* 26 */     return handlers;
/*    */   }
/*    */   
/*    */   public Player getPlayer() {
/* 30 */     return this.player;
/*    */   }
/*    */   
/*    */   public String getHeader() {
/* 34 */     return this.header;
/*    */   }
/*    */   
/*    */   public void setHeader(String header) {
/* 38 */     this.header = header;
/*    */   }
/*    */   
/*    */   public String getFooter() {
/* 42 */     return this.footer;
/*    */   }
/*    */   
/*    */   public void setFooter(String footer) {
/* 46 */     this.footer = footer;
/*    */   }
/*    */   
/*    */   public boolean isCancelled() {
/* 50 */     return this.cancelled;
/*    */   }
/*    */   
/*    */   public void setCancelled(boolean cancelled) {
/* 54 */     this.cancelled = cancelled;
/*    */   }
/*    */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\TitleAPI\TabTitleSendEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */