/*     */ package me.RafaelAulerDeMeloAraujo.TitleAPI;
/*     */ 
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Field;
/*     */ 
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Listener;
/*     */ 
/*     */ 
/*     */ public class TitleAPI extends org.bukkit.plugin.java.JavaPlugin implements Listener
/*     */ {
/*     */   @Deprecated
/*     */   public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message)
/*     */   {
/*  17 */     sendTitle(player, fadeIn, stay, fadeOut, message, null);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void sendSubtitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String message) {
/*  22 */     sendTitle(player, fadeIn, stay, fadeOut, null, message);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public static void sendFullTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle) {
/*  27 */     sendTitle(player, fadeIn, stay, fadeOut, title, subtitle);
/*     */   }
/*     */   
/*     */   public static void sendPacket(Player player, Object packet) {
/*     */     try {
/*  32 */       Object handle = player.getClass().getMethod("getHandle", new Class[0]).invoke(player, new Object[0]);
/*  33 */       Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
/*  34 */       playerConnection.getClass().getMethod("sendPacket", new Class[] { getNMSClass("Packet") }).invoke(playerConnection, new Object[] { packet });
/*     */     } catch (Exception e) {
/*  36 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   public static Class<?> getNMSClass(String name) {
/*  41 */     String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
/*     */     try {
/*  43 */       return Class.forName("net.minecraft.server." + version + "." + name);
/*     */     } catch (ClassNotFoundException e) {
/*  45 */       e.printStackTrace(); }
/*  46 */     return null;
/*     */   }
/*     */   
/*     */   public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle)
/*     */   {
/*  51 */     player.sendTitle(title.replace("&", "§"), subtitle.replace("&", "§"), fadeIn, stay, fadeOut);

/*     */     }
/*     */   
/*     */   
/*     */   public static void clearTitle(Player player) {
/* 103 */     sendTitle(player, Integer.valueOf(0), Integer.valueOf(0), Integer.valueOf(0), "", "");
/*     */   }
/*     */   
/*     */   public static void sendTabTitle(Player player, String header, String footer) {
/* 107 */     if (header == null) header = "";
/* 108 */     header = ChatColor.translateAlternateColorCodes('&', header);
/*     */     
/* 110 */     if (footer == null) footer = "";
/* 111 */     footer = ChatColor.translateAlternateColorCodes('&', footer);
/*     */     
/* 113 */     TabTitleSendEvent tabTitleSendEvent = new TabTitleSendEvent(player, header, footer);
/* 114 */     Bukkit.getPluginManager().callEvent(tabTitleSendEvent);
/* 115 */     if (tabTitleSendEvent.isCancelled()) {
/* 116 */       return;
/*     */     }
/* 118 */     header = header.replaceAll("%player%", player.getDisplayName());
/* 119 */     footer = footer.replaceAll("%player%", player.getDisplayName());
/*     */     try
/*     */     {
/* 122 */       Object tabHeader = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + header + "\"}" });
/* 123 */       Object tabFooter = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke(null, new Object[] { "{\"text\":\"" + footer + "\"}" });
/* 124 */       Constructor<?> titleConstructor = getNMSClass("PacketPlayOutPlayerListHeaderFooter").getConstructor(new Class[] { getNMSClass("IChatBaseComponent") });
/* 125 */       Object packet = titleConstructor.newInstance(new Object[] { tabHeader });
/* 126 */       Field field = packet.getClass().getDeclaredField("b");
/* 127 */       field.setAccessible(true);
/* 128 */       field.set(packet, tabFooter);
/* 129 */       sendPacket(player, packet);
/*     */     } catch (Exception ex) {
/* 131 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\TitleAPI\TitleAPI.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
