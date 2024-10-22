/*    */ package me.RafaelAulerDeMeloAraujo.TitleAPI;
/*    */ 
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.HandlerList;
/*    */ 
/*    */ public class TitleSendEvent extends org.bukkit.event.Event
/*    */ {
/*  8 */   private static final HandlerList handlers = new HandlerList();
/*    */   private final Player player;
/*    */   private String title;
/*    */   private String subtitle;
/* 12 */   private boolean cancelled = false;
/*    */   
/*    */   public TitleSendEvent(Player player, String title, String subtitle) {
/* 15 */     this.player = player;
/* 16 */     this.title = title;
/* 17 */     this.subtitle = subtitle;
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
/*    */   public String getTitle() {
/* 34 */     return this.title;
/*    */   }
/*    */   
/*    */   public void setTitle(String title) {
/* 38 */     this.title = title;
/*    */   }
/*    */   
/*    */   public String getSubtitle() {
/* 42 */     return this.subtitle;
/*    */   }
/*    */   
/*    */   public void setSubtitle(String subtitle) {
/* 46 */     this.subtitle = subtitle;
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


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\TitleAPI\TitleSendEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */