package me.rafaelauler.minigame.thepit;

/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;

import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.GameMode;
/*     */ import org.bukkit.Location;
import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
import org.bukkit.World;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scoreboard.Scoreboard;

import me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI;



 
/*     */ public class MainCommand
/*     */   implements CommandExecutor, Listener
/*     */ {
/*  43 */   public static HashMap<String, ItemStack[]> saveinv = new HashMap();
/*  44 */   public static HashMap<String, ItemStack[]> savearmor = new HashMap();
/*  45 */   public static HashMap<String, Location> saveworld = new HashMap();
/*  46 */   public static HashMap<String, GameMode> savegamemode = new HashMap();
public static HashMap<String, Scoreboard> savescore = new HashMap();
public static HashMap<String, Integer> savelevel = new HashMap();
public static HashMap<String, Integer> savehunger = new HashMap();
public static HashMap<String, PotionEffect> saveeffect = new HashMap();
public static HashMap<String, Integer> saveair = new HashMap();
public final static String NomeServer = me.rafaelauler.minigame.thepit.BukkitMain.getInstance().getConfig().getString("Prefix").replace("&", "§");

/*     */ 
/*     */   private BukkitMain BukkitMain;
private static String text = "";
/*     */   static BukkitMain plugin;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public MainCommand(BukkitMain BukkitMain)
/*     */   {
/*  61 */     this.BukkitMain = BukkitMain;
/*  62 */     plugin = BukkitMain;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  76 */   HashMap<String, Location> maps = new HashMap();
/*  77 */   public static ArrayList<String> game = new ArrayList();
public static ArrayList<Player> player = new ArrayList();
/*  78 */   List<String> commands = Arrays.asList(new String[] { "admin", "list", "create", "delete", "1v1", "score", "setspawn", "spawn", "join", "leave", "reset", "coins", "setchallenge", "kit", "kitunlocker", "resetkit", "stats", "reload", "update" });
/*     */   
/*     */   public MainCommand() {}
/*     */   

/*     */   
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
/*  91 */     if (commandLabel.equalsIgnoreCase("thepit") || (commandLabel.equalsIgnoreCase("thp")))
/*     */     {
/*  93 */       if (args.length == 0)
/*     */       {

/* 106 */         sender.sendMessage(ChatColor.DARK_AQUA + "§m-----------" + ChatColor.AQUA + " THEPIT COMMANDS " + ChatColor.DARK_AQUA + ChatColor.STRIKETHROUGH + "-------------");
/* 107 */         sender.sendMessage(ChatColor.DARK_AQUA + "§eCreated by Rafael Auler");
/* 108 */         sender.sendMessage("");
/* 109 */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/" + commandLabel + ChatColor.DARK_AQUA + " - " + ChatColor.GRAY + "Main command");
/* 110 */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/" + commandLabel + " " + ChatColor.GREEN + "join" + ChatColor.DARK_AQUA + " - " + ChatColor.GRAY + "Join the PIT!");
/* 111 */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/" + commandLabel + " " + ChatColor.GREEN + "leave" + ChatColor.DARK_AQUA + " - " + ChatColor.GRAY + "Leave the PIT!");
/* 112 */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/" + commandLabel + " " + ChatColor.GREEN + "setspawn" + ChatColor.DARK_AQUA + " - " + ChatColor.GRAY + "Set spawn!");
/* 114 */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/" + commandLabel + " " + ChatColor.GREEN + "stats" + ChatColor.DARK_AQUA + " - " + ChatColor.GRAY + "View stats!");
/*     */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/" + commandLabel + " " + ChatColor.GREEN + "spawn" + ChatColor.DARK_AQUA + " - " + ChatColor.GRAY + "Returns to spawn");                              
/* 116 */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/" + commandLabel + " " + ChatColor.GREEN + "info" + ChatColor.DARK_AQUA + " - " + ChatColor.GRAY + "Shows plugin info");
/* 117 */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/" + commandLabel + " " + ChatColor.GREEN + "update" + ChatColor.DARK_AQUA + " - " + ChatColor.RED + "Get a link to verify updates");
/* 118 */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/" + commandLabel + " " + ChatColor.GREEN + "reload" + ChatColor.DARK_AQUA + " - " + ChatColor.RED + "Reload config files");
/* 119 */         sender.sendMessage(ChatColor.DARK_AQUA + " - " + ChatColor.AQUA + "/thepithelp" + ChatColor.DARK_AQUA + " - " + ChatColor.GRAY + "View the full command list");
/* 120 */         sender.sendMessage(ChatColor.DARK_AQUA + "§m------------------------------------------");
/* 101 */         ((Player)sender).playSound(((Player)sender).getLocation(), Sound.valueOf(this.BukkitMain.getConfig().getString("Sound.SucefullMessage")), 4.0F, 2.0F);
/* 102 */         return true;
/*     */       }
/* 104 */       if (args[0].equalsIgnoreCase("info"))
/*     */       {
	/* 27 */       sender.sendMessage("§4§l\u274C §2§lCREDITS §f§lAND §e§lINFORMATION §4§l \u274C");
	/* 28 */       sender.sendMessage("§6\u279C §cPlugin Name: §eTHE-PIT");
	/* 29 */       sender.sendMessage("§6\u279C §cPlugin Version: §e " + BukkitMain.getInstance().getDescription().getVersion());
	/* 30 */       sender.sendMessage("§6\u279C §cAuthor: §ezEnderX5_ , Rafael Auler");
	/* 31 */       sender.sendMessage("§6\u279C §cAuthor Channel: http://bit.ly/2kC345B");
	/* 32 */       sender.sendMessage("§6\u279C §cSpigot Profile: http://bit.ly/2j5qvnM");
	/* 33 */       sender.sendMessage("§6\u279C §cPlugin Page: COMMING SOON");
	/* 34 */       sender.sendMessage("§cThanks for use this plugin i really appreaciate IT");
	/* 35 */       sender.sendMessage("§cIf you like it consider giving a §e§l\u2605\u2605\u2605\u2605\u2605 §cReview");
	/* 36 */       sender.sendMessage("§cPS: §eSubscribe to my channel and follow me on Spigot Thanks! §9§l=)");
/* 121 */         ((Player)sender).playSound(((Player)sender).getLocation(), Sound.valueOf(this.BukkitMain.getConfig().getString("Sound.SucefullMessage")), 10.0F, 2.0F);
/* 122 */         return true;
/*     */       }
if (args[0].equalsIgnoreCase("reload"))
/*     */       {
/* 128 */         if (!sender.hasPermission("thepit.reload")) {
	Player p = (Player)sender;
/* 129 */           p.sendMessage(String.valueOf(this.BukkitMain.getConfig().getString("Prefix").replace("&", "§")) + this.BukkitMain.getConfig().getString("Permission").replace("&", "§"));
/* 130 */           p.playSound(p.getLocation(), Sound.valueOf(this.BukkitMain.getConfig().getString("Sound.NoPermissionMessage")), 1.0F, 1.0F);
/* 131 */           return true;
/*     */         }
/*     */         Player p = (Player)sender;
/* 134 */         Plugin plugin = p.getServer().getPluginManager().getPlugin("THE-PIT");
/* 135 */         plugin.reloadConfig();
/* 136 */         p.getServer().getPluginManager().disablePlugin(plugin);
/* 137 */         p.getServer().getPluginManager().enablePlugin(plugin);
/* 138 */         p.sendMessage(String.valueOf(this.BukkitMain.getConfig().getString("Prefix").replace("&", "§")) + " §eThe plugin Config has been sucefully reloaded.");
/* 139 */         return true;
/*     */       }
/* 141 */       if (args[0].equalsIgnoreCase("update"))
/*     */       {
/* 143 */         if (!sender.hasPermission("thepit.admin")) {
/* 144 */           sender.sendMessage(String.valueOf(this.BukkitMain.getConfig().getString("Prefix").replace("&", "§")) + this.BukkitMain.getConfig().getString("Permission").replace("&", "§"));
/* 145 */           ((Player)sender).playSound(((Player)sender).getLocation(), Sound.valueOf(this.BukkitMain.getConfig().getString("Sound.NoPermissionMessage")), 1.0F, 1.0F);
/* 146 */           return true;
/*     */         }
/*     */         
/* 149 */         sender.sendMessage("§c[THEPIT] §eThe plugin may has a update.");
/* 150 */         sender.sendMessage("§c[THEPIT] §eVerify it here.");
/* 151 */         sender.sendMessage("§c[THEPIT] §eYour version: " + BukkitMain.getInstance().getDescription().getVersion());
/* 152 */         sender.sendMessage("§c[THEPIT] §eIf your version is lower than the last update");
/* 153 */         sender.sendMessage("§c[THEPIT] §eYou should update your plugin");
/* 154 */         sender.sendMessage("§c[THEPIT] §eLink: COMMING SOON");
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 159 */         ((Player)sender).playSound(((Player)sender).getLocation(), Sound.valueOf(this.BukkitMain.getConfig().getString("Sound.SucefullMessage")), 2.0F, 2.0F);
/* 160 */         return true;
/*     */       }
if (args[0].equalsIgnoreCase("spawn"))
/*     */       {
	Player p = (Player)sender;
	/*  58 */       if (!MainCommand.game.contains(p.getName()))
	/*     */       {
	/*  60 */         p.sendMessage(String.valueOf("§cYou are not in thepit to do this!"));
	/*  61 */         return true;
	/*     */       }
	/*     */       
	/*  64 */       if (!sender.hasPermission("thepit.spawn")) {
	/*  65 */         p.sendMessage(String.valueOf(this.BukkitMain.getConfig().getString("Prefix").replace("&", "§")) + this.BukkitMain.getConfig().getString("Permission").replace("&", "§").replaceAll("%permisson%", commandLabel));
	/*  66 */         p.playSound(p.getLocation(), Sound.valueOf(this.BukkitMain.getConfig().getString("Sound.NoPermissionMessage")), 1.0F, 1.0F);
	/*  67 */         return true;
	/*     */       }
	/*  69 */       for (PotionEffect effect : p.getActivePotionEffects()) {
	/*  70 */         p.removePotionEffect(effect.getType());
	/*     */       }
	/*  72 */       p.sendMessage(String.valueOf(this.BukkitMain.getConfig().getString("Prefix").replace("&", "§")) + this.BukkitMain.getConfig().getString("Message.Clear").replace("&", "§"));
	/*  73 */       p.getInventory().clear();
	/*  82 */       org.bukkit.World w = org.bukkit.Bukkit.getServer().getWorld(BukkitMain.plugin.getConfig().getString("Spawn.World"));
	/*  83 */       double x = BukkitMain.plugin.getConfig().getDouble("Spawn.X");
	/*  84 */       double y = BukkitMain.plugin.getConfig().getDouble("Spawn.Y");
	/*  85 */       double z = BukkitMain.plugin.getConfig().getDouble("Spawn.Z");
	/*  86 */       Location lobby = new Location(w, x, y, z);
	/*  87 */       lobby.setPitch((float)BukkitMain.plugin.getConfig().getDouble("Spawn.Pitch"));
	/*  88 */       lobby.setYaw((float)BukkitMain.plugin.getConfig().getDouble("Spawn.Yaw"));
	/*  89 */       /*     */ 
	/* 219 */           p.getInventory().clear();
	p.getInventory().setArmorContents(null);
	/*     */       
	/*     */ 
	/* 107 */       								ItemStack espada = new ItemStack(Material.IRON_SWORD);

	ItemStack arco = new ItemStack(Material.BOW);

	ItemStack flechas = new ItemStack(Material.ARROW, 32);

	ItemStack colete = new ItemStack(Material.IRON_CHESTPLATE);

	ItemStack calça = new ItemStack(Material.CHAINMAIL_LEGGINGS);

	ItemStack bota = new ItemStack(Material.CHAINMAIL_BOOTS);
	/*     */ p.getInventory().clear();
	/* 107 */       p.updateInventory();
	/*     */       p.getInventory().setItem(0, espada);

	/*     */       p.getInventory().setItem(1, arco);
	/*     */       p.getInventory().setItem(8, flechas);
	p.getInventory().setChestplate(colete);
	/*     */ 
	p.getInventory().setLeggings(calça);
	p.getInventory().setBoots(bota);
	/*     */ 
	/* 107 */       p.updateInventory();
	/*  90 */       p.teleport(lobby);
	p.getInventory().setArmorContents(null);

	/*     */ 
	/* 107 */       p.updateInventory();
	/*     */       
	/*     */ 
	/* 107 */       
	/*     */       p.setAllowFlight(false);
	/*     */ 
	/* 107 */    
	/*     */       
	/*     */ me.rafaelauler.minigame.thepit.BukkitMain.tirarEfeitos(p);
	/* 107 */     
/*     */         

/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 159 */         ((Player)sender).playSound(((Player)sender).getLocation(), Sound.valueOf(this.BukkitMain.getConfig().getString("Sound.SucefullMessage")), 2.0F, 2.0F);
/* 160 */         return true;
/*     */       }
/* 162 */       if (!this.commands.contains(args[0].toLowerCase()))
/*     */       {
/* 164 */         sender.sendMessage(ChatColor.DARK_AQUA + String.valueOf(this.BukkitMain.getConfig().getString("Prefix").replace("&", "§")) + " §eUnknown command");
/* 165 */         return true;
/*     */       }
if (args[0].equalsIgnoreCase("setspawn"))
/*     */       {
	Player p = (Player)sender;
	  if (!p.hasPermission("kitpvp.setspawn")){
          return true;
        }
        BukkitMain.getConfig().set("Spawn.World", p.getLocation().getWorld().getName());
        BukkitMain.getConfig().set("Spawn.X", Double.valueOf(p.getLocation().getX()));
        BukkitMain.getConfig().set("Spawn.Y", Double.valueOf(p.getLocation().getY()));
        BukkitMain.getConfig().set("Spawn.Z", Double.valueOf(p.getLocation().getZ()));
        BukkitMain.getConfig().set("Spawn.Pitch", Float.valueOf(p.getLocation().getPitch()));
        BukkitMain.getConfig().set("Spawn.Yaw", Float.valueOf(p.getLocation().getYaw()));
        BukkitMain.saveConfig();
        BukkitMain.getConfig().set("SpawnD.World", p.getLocation().getWorld().getName());
        BukkitMain.getConfig().set("SpawnD.X", Double.valueOf(p.getLocation().getX()));
        BukkitMain.getConfig().set("SpawnD.Y", Double.valueOf(p.getLocation().getY()));
        BukkitMain.getConfig().set("SpawnD.Z", Double.valueOf(p.getLocation().getZ()));
        BukkitMain.getConfig().set("SpawnD.Pitch", Float.valueOf(p.getLocation().getPitch()));
        BukkitMain.getConfig().set("SpawnD.Yaw", Float.valueOf(p.getLocation().getYaw()));
        BukkitMain.saveConfig();
     
        p.sendMessage(NomeServer + me.rafaelauler.minigame.thepit.BukkitMain.messages.getString("SpawnSeted").replace("&", "§"));
        return true;
      }
}
if (args[0].equalsIgnoreCase("stats"))
/*     */       {
	Player p = (Player)sender;
	  
        StatusGUI.openGUI(p, p);
        return true;
      }
/* 167 */       if (args[0].equalsIgnoreCase("join"))
/*     */       {
/* 169 */         if ((sender instanceof Player))
/*     */         {
/* 171 */           if (game.contains(sender.getName()))
/*     */           {
/* 173 */             sender.sendMessage(String.valueOf(this.BukkitMain.getConfig().getString("Prefix").replace("&", "§") + "§cYou are already on thepit!"));
/* 174 */             return true;
/*     */           }
/*     */           
/*     */ Player p = (Player)sender;
/*     */ 
/* 179 */           p.sendMessage(String.valueOf(this.BukkitMain.getConfig().getString("Prefix").replace("&", "§")) + String.valueOf(this.BukkitMain.getConfig().getString("Message.ThePitJoin-Message").replace("&", "§")));
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 185 */           game.add(p.getName());
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 

}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ Player p = (Player)sender;
/*     */ 
;
		/* 200 */           World w = Bukkit.getServer().getWorld(BukkitMain.plugin.getConfig().getString("Spawn.World"));
		/* 201 */           double x = BukkitMain.plugin.getConfig().getDouble("Spawn.X");
		/* 202 */           double y = BukkitMain.plugin.getConfig().getDouble("Spawn.Y");
		/* 203 */           double z = BukkitMain.plugin.getConfig().getDouble("Spawn.Z");
		/* 204 */           Location lobby = new Location(w, x, y, z);
		/* 205 */           saveworld.put(p.getName(), p.getLocation());
		                    savescore.put(p.getName(), p.getScoreboard());
		/* 206 */           saveinv.put(p.getName(), p.getInventory().getContents());
		/* 207 */           savearmor.put(p.getName(), p.getInventory().getArmorContents());
		/* 208 */           savegamemode.put(p.getName(), p.getGameMode());
		/*     */           savelevel.put(p.getName(), p.getLevel());
		savehunger.put(p.getName(), p.getFoodLevel());
		saveair.put(p.getName(), p.getRemainingAir());
		/*     */ 
		/* 211 */           lobby.setPitch((float)BukkitMain.plugin.getConfig().getDouble("Spawn.Pitch"));
		/* 212 */           lobby.setYaw((float)BukkitMain.plugin.getConfig().getDouble("Spawn.Yaw"));
		/* 213 */           p.getInventory().clear();
/* 216 */           p.teleport(lobby);
/*     */           
/*     */ 
/* 219 */           p.getInventory().clear();
p.getInventory().setArmorContents(null);
/*     */       
/*     */ 
/* 107 */       								ItemStack espada = new ItemStack(Material.IRON_SWORD);

ItemStack arco = new ItemStack(Material.BOW);

ItemStack flechas = new ItemStack(Material.ARROW, 32);

ItemStack colete = new ItemStack(Material.IRON_CHESTPLATE);

ItemStack calça = new ItemStack(Material.CHAINMAIL_LEGGINGS);

ItemStack bota = new ItemStack(Material.CHAINMAIL_BOOTS);
/*     */ p.getInventory().clear();
/* 107 */       p.updateInventory();
/*     */       p.getInventory().setItem(0, espada);

/*     */       p.getInventory().setItem(1, arco);
/*     */       p.getInventory().setItem(8, flechas);
p.getInventory().setChestplate(colete);
/*     */ 
p.getInventory().setLeggings(calça);
p.getInventory().setBoots(bota);
/*     */ 
/* 107 */       p.updateInventory();
/*     */           
/*     */ 

/*     */ 
/* 235 */           p.setExp(0.0F);
/* 236 */           p.setExhaustion(20.0F);
/* 237 */           p.setFireTicks(0);
/* 238 */           p.setFoodLevel(20000);
/* 239 */           TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), this.BukkitMain.getConfig().getString("Title.JoinTitle"), this.BukkitMain.getConfig().getString("Title.JoinSubTitle"));
me.rafaelauler.minigame.thepit.BukkitMain.tirarEfeitos(p);

}
if (args[0].equalsIgnoreCase("leave"))
/*     */       {
	Player p = (Player)sender;
	if ((!game.contains(p.getName()))) {
		p.sendMessage(NomeServer + 
				me.rafaelauler.minigame.thepit.BukkitMain.messages.getString("MustBeInGame").replace("&", "§"));
		return true;
	}
	if (p.getGameMode().equals(GameMode.SPECTATOR)) {
		p.sendMessage(NomeServer + 
				me.rafaelauler.minigame.thepit.BukkitMain.messages.getString("NeedToRespawn").replace("&", "§"));
		return true;
	}
	if (BukkitMain.getConfig().getBoolean("bungeemode")) {
		p.sendMessage(NomeServer + 
				me.rafaelauler.minigame.thepit.BukkitMain.messages.getString("BungeeModeEnabled").replace("&", "§"));
		return true;
	}
	/*     */       
	/*     */ 
	/*     */ 
	/*     */ 
	/* 283 */       game.remove(p.getName());
	/* 284 */       game.remove(p.getName());
	/* 285 */       game.remove(p.getName());
	/* 286 */       game.remove(p.getName());
	/* 287 */       game.remove(p.getName());
	/* 288 */       game.remove(p.getName());
	/* 289 */       game.remove(p.getName());
	/* 290 */       game.remove(p.getName());
	/* 291 */       game.remove(p.getName());
	/* 292 */       game.remove(p.getName());
	/* 293 */       game.remove(p.getName());
	/* 294 */       game.remove(p.getName());game.remove(p.getName());
	/* 295 */       game.remove(p.getName());
	/* 296 */       game.remove(p.getName());
	/* 297 */       game.remove(p.getName());

	/* 303 */       p.sendMessage(String.valueOf(this.BukkitMain.getConfig().getString("Prefix").replace("&", "§")) + String.valueOf(this.BukkitMain.getConfig().getString("Message.ThePitLeave-Message").replace("&", "§")));
	/* 304 */       p.getInventory().clear();
	/* 305 */       p.teleport((Location)saveworld.get(p.getName()));
	/* 306 */       p.getInventory().setContents((ItemStack[])saveinv.get(p.getName()));
	/* 307 */       p.setGameMode((GameMode)savegamemode.get(p.getName()));
	p.setScoreboard(savescore.get(p.getName()));
	p.setLevel(savelevel.get(p.getName()));
	p.setFoodLevel(savehunger.get(p.getName()));
	p.setRemainingAir(saveair.get(p.getName()));
	/* 308 */       p.getInventory().setArmorContents((ItemStack[])savearmor.get(p.getName()));
	TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), this.BukkitMain.getConfig().getString("Title.LeaveTitle"), this.BukkitMain.getConfig().getString("Title.LeaveSubTitle"));

	/*     */   
	/* 311 */       p.updateInventory();
	me.rafaelauler.minigame.thepit.BukkitMain.tirarEfeitos(p);

	/*     */     }

/*     */       
return false;
}
}