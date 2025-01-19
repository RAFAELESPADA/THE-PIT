package me.rafaelauler.minigame.thepit;



import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import us.ajg0702.leaderboards.LeaderboardPlugin;








public class BukkitMain extends JavaPlugin implements Listener {

    public static BukkitMain plugin;
    public static BukkitMain instance;

/*  93 */   public static File messagesf = new File("plugins/THE-PIT", "messages.yml");
public static File customizationf = new File("plugins/THE-PIT", "settings.yml");
public static FileConfiguration messages = YamlConfiguration.loadConfiguration(messagesf);

public static FileConfiguration customization = YamlConfiguration.loadConfiguration(customizationf);
    @Override
    public void onEnable() {
    	  plugin = this;
    	  instance = this;
    	  ConsoleCommandSender cmd = Bukkit.getConsoleSender();
    		if (!Coins.setupPermissions()) {
    			cmd.sendMessage("THEPIT - Disabled due to no Vault dependency found! THE-PIT VERSION" + getDescription().getVersion());
    	        cmd.sendMessage("Install vault to the-pit work!");
    	        getServer().getPluginManager().disablePlugin(this);
    	        return;
    	    }
    		if (!Coins.setupEconomy()) {
    			cmd.sendMessage("THEPIT - Disabled due to no Vault dependency found! THE-PIT VERSION" + getDescription().getVersion());
    	        cmd.sendMessage("Install vault to THE-PIT work!");
    	        getServer().getPluginManager().disablePlugin(this);
    	        return;
    	    }
    		if (!Bukkit.getPluginManager().isPluginEnabled("KPCore")) {
    			 Bukkit.getConsoleSender().sendMessage("KPCORE is not installed! It is required for MYSQL SUPPORT (MANDATORY)");
    			 Bukkit.getConsoleSender().sendMessage("Disabling THE-PIT");
    			 getServer().getPluginManager().disablePlugin(this);
    		        return;
    		 }
    		/* 146 */     if ((Bukkit.getVersion().contains("1.14") || (Bukkit.getVersion().contains("1.15") || (Bukkit.getVersion().contains("1.16") || (Bukkit.getVersion().contains("1.17") || (Bukkit.getVersion().contains("1.18") || (Bukkit.getVersion().contains("1.19") || (Bukkit.getVersion().contains("1.20") || (Bukkit.getVersion().contains("1.21") || (Bukkit.getVersion().contains("1.22") || (Bukkit.getVersion().contains("1.23") || (Bukkit.getVersion().contains("1.24")))))))))))))
    		/*     */     {
    		/* 148 */       getConfig().options().copyDefaults(true);
    		/* 149 */       getConfig().options().copyHeader(true);
    		/* 150 */       saveConfig();
    		/* 151 */       Bukkit.getConsoleSender().sendMessage("§e[THE-PIT] §aThe server is using 1.14/1.15 MINECRAFT VERSION");
    		/* 152 */       Bukkit.getConsoleSender().sendMessage("§e[THE-PIT] §aAltering config sounds to 1.14 Sounds...");
    		getConfig().options().copyDefaults(true);
    		getConfig().options().copyHeader(true);
    		saveConfig();
    		getConfig().set("Sound.Kit", "ENTITY_ITEM_PICKUP");
    		getConfig().set("Sound.ShopMenu", "BLOCK_CHEST_OPEN");
    		getConfig().set("Sound.KitUse", "ENTITY_ENDER_DRAGON_HURT");
    		getConfig().set("Sound.Soup", "ENTITY_GENERIC_EAT");
    		getConfig().set("Sound.Fisherman", "ENTITY_ENDERMAN_TELEPORT");
    		getConfig().set("Sound.Spiderman", "ENTITY_SLIME_JUMP");
    		getConfig().set("Sound.Respawn", "ENTITY_PLAYER_LEVELUP");
    		getConfig().set("Sound.Join", "ENTITY_PLAYER_LEVELUP");
    		getConfig().set("Sound.KitMenu", "BLOCK_WOODEN_DOOR_CLOSE");
    		getConfig().set("Sound.SpongeUse", "ENTITY_ENDERMAN_TELEPORT");
    		getConfig().set("Sound.BowlDrop", "ENTITY_ITEM_PICKUP");
    		getConfig().set("Sound.ErrorMessage", "ENTITY_ARROW_HIT_PLAYER");
    		getConfig().set("Sound.SucefullMessage", "ENTITY_EXPERIENCE_ORB_PICKUP");
    		getConfig().set("Sound.NoPermissionMessage", "ENTITY_WITHER_SHOOT");
    		getConfig().set("Sound.SwitcherShoot", "ENTITY_ENDERMAN_TELEPORT");
    		getConfig().set("Sound.Timelord", "ENTITY_WITHER_SPAWN");
    		getConfig().set("Sound.ItemDespawn", "BLOCK_LAVA_POP");
    		getConfig().set("Sound.Stomper", "ENTITY_FIREWORK_ROCKET_BLAST");
    		getConfig().set("Sound.NarutoAbility", "ENTITY_BLAZE_DEATH");
    		getConfig().set("Sound.CommandsSounds", "UI_BUTTON_CLICK");
    		getConfig().set("Sound.ClickTest", "UI_BUTTON_CLICK");
    		getConfig().set("Sound.1v1", "UI_BUTTON_CLICK");
    		getConfig().set("Sound.Respawning", "UI_BUTTON_CLICK");
    		getConfig().set("Sound.RespawnSucess", "ENTITY_EXPERIENCE_ORB_PICKUP");
    		getConfig().set("Sound.ShopMenu-Click", "ENTITY_PLAYER_LEVELUP");
    		getConfig().set("Sound.Airman-Fly", "ENTITY_ENDERMAN_SCREAM");
    		getConfig().set("Sound.DoubleJump-Ability", "ENTITY_FIREWORK_ROCKET_BLAST");
    		getConfig().set("Sound.StomperDamage", "BLOCK_ANVIL_LAND");
    		getConfig().set("Sound.AnchorHit", "BLOCK_ANVIL_LAND");
    		getConfig().set("Sound.RyuAbility", "ENTITY_GENERIC_EXPLODE");
    		/* 178 */       Bukkit.getConsoleSender().sendMessage("§e[THE-PIT] §aDone! All Sounds have been modified to 1.14 Sounds.");
    		/*     */     }
if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null){
	/* 151 */       Bukkit.getConsoleSender().sendMessage("§e[THEPIT] §aPlaceHolderAPI is found!");
	/* 151 */       Bukkit.getConsoleSender().sendMessage("§e[THEPIT] §aHooking into it!");
    new PlaceHolderAPIHook(this).register();
	/* 151 */       Bukkit.getConsoleSender().sendMessage("§e[THEPIT] §aPlaceHolderAPI has hooked sucefully!");
}
loadTopPlayersHologram();
  Bukkit.getServer().getPluginManager().registerEvents(this, this);
  getCommand("thepit").setExecutor(new MainCommand(this));
  getCommand("setthepittopkills").setExecutor(new ThePitTopKills());
  getCommand("updatethepittopkill").setExecutor(new UpdateTopKill());
  getCommand("thepiteditmode").setExecutor(new ThePitEditMode());
  getCommand("thepithelp").setExecutor(new THEPITHELP());
  getCommand("givegold").setExecutor(new GoldCommand());
  getCommand("thepitkickall").setExecutor(new PTKICK());
  File cf = new File(getDataFolder(), "config.yml");
  /* 127 */     if (!cf.exists()) {
  /* 128 */       saveResource("config.yml", false);
  /*     */     }
  if (!messagesf.exists()) {
	  /* 135 */       saveResource("messages.yml", false);
	  /*     */     }

  if (!customizationf.exists()) {
	  /* 135 */       saveResource("settings.yml", false);
	  /*     */     }
  /*     */     try
  /*     */     {
  messages.load(messagesf);

  customization.load(customizationf);

  /*     */     }
  catch (IOException|InvalidConfigurationException e1)
  /*     */     {
  /* 144 */       System.out.println(e1.getMessage());
  /*     */     }
  Bukkit.getConsoleSender().sendMessage("§e[THE-PIT] §aRegistering plugin listeners...");
	/*     */     
  registerEvents();

  Bukkit.getConsoleSender().sendMessage("§e[THE-PIT] §aDone! Plugin listeners loaded!");

  saveDefaultConfig();

  Bukkit.getConsoleSender().sendMessage("§e[THE-PIT] §aThe plugin has been enabled successfully!");
    }
    public static void handleTopPlayers(Location location) {
    	Plugin lb = Bukkit.getPluginManager().getPlugin("ajLeaderboards");
    	if (lb == null || !lb.isEnabled() || !(lb instanceof LeaderboardPlugin)) {
    		Bukkit.getLogger().severe("AjLeaderBoards not found! TopKills will not work.");
    		return;
    	}
    	Plugin dc = Bukkit.getPluginManager().getPlugin("DecentHolograms");
    	if (dc == null) {
    		Bukkit.getLogger().severe("DecentHolograms not found! TopKills will not work.");
    		return;
    	}
    	LeaderboardPlugin ajlb = (LeaderboardPlugin) lb;
    	if (!ajlb.getCache().createBoard("the-pit_player_kills")) {
    		Bukkit.getLogger().severe("[the-pit] TopKills Creation Failed. Aborting");
    		return;
    	}
    		
    		String header = "§e§lTop 15 players §a(THE PIT KILLS)";	
    			List<String> lines = Arrays.asList(header,
    				"§61"  + "º " + "§e" + "%ajlb_lb_the-pit_player_kills_1_alltime_name%" +
    				" §fKills: §6" + "%ajlb_lb_the-pit_player_kills_1_alltime_value%", "§62" + "º " + "§e" + "%ajlb_lb_the-pit_player_kills_2_alltime_name%" +
    						" §fKills: §6" + "%ajlb_lb_the-pit_player_kills_2_alltime_value%", "§63" + "º " + "§e" + "%ajlb_lb_the-pit_player_kills_3_alltime_name%" +
    								" §fKills: §6" + "%ajlb_lb_the-pit_player_kills_3_alltime_value%", "§64" + "º " + "§e" + "%ajlb_lb_the-pit_player_kills_4_alltime_name%" +
    										" §fKills: §6" + "%ajlb_lb_the-pit_player_kills_4_alltime_value%", "§65" + "º " + "§e" + "%ajlb_lb_the-pit_player_kills_5_alltime_name%" +
    												" §fKills: §6" + "%ajlb_lb_the-pit_player_kills_5_alltime_value%", "§66" + "º " + "§e" + "%ajlb_lb_the-pit_player_kills_6_alltime_name%" +
    														" §fKills: §6" + "%ajlb_lb_the-pit_player_kills_6_alltime_value%", "§67" + "º " + "§e" + "%ajlb_lb_the-pit_player_kills_7_alltime_name%" +
    																" §fKills: §6" + "%ajlb_lb_the-pit_player_kills_7_alltime_value%", "§68" + "º " + "§e" + "%ajlb_lb_the-pit_player_kills_8_alltime_name%" +
    																		" §fKills: §6" + "%ajlb_lb_the-pit_player_kills_8_alltime_value%", "§69" + "º " + "§e" + "%ajlb_lb_the-pit_player_kills_9_alltime_name%" +
    																				" §fKills: §6" + "%ajlb_lb_the-pit_player_kills_9_alltime_value%", "§610" + "º " + "§e" + "%ajlb_lb_the-pit_player_kills_10_alltime_name%" +
    																						" §fKills: §6" + "%ajlb_lb_the-pit_player_kills_10_alltime_value%", "§611" + "º " + "§e" + "%ajlb_lb_the-pit_player_kills_11_alltime_name%" +
    																								" §fKills: §6" + "%ajlb_lb_the-pit_player_kills_11_alltime_value%", "§612" + "º " + "§e" + "%ajlb_lb_the-pit_player_kills_12_alltime_name%" +
    																										" §fKills: §6" + "%ajlb_lb_the-pit_player_kills_12_alltime_value%", "§613" + "º " + "§e" + "%ajlb_lb_the-pit_player_kills_13_alltime_name%" +
    																												" §fKills: §6" + "%ajlb_lb_the-pit_player_kills_13_alltime_value%", "§614" + "º " + "§e" + "%ajlb_lb_the-pit_player_kills_14_alltime_name%" +
    																														" §fKills: §6" + "%ajlb_lb_the-pit_player_kills_14_alltime_value%", "§615" + "º " + "§e" + "%ajlb_lb_the-pit_player_kills_15_alltime_name%" +
    																																" §fKills: §6" + "%ajlb_lb_the-pit_player_kills_15_alltime_value%");
    		
    		
    			
    		Hologram hologram = DHAPI.getHologram("toppitkills");
    		if (hologram == null) {
    			 Bukkit.getConsoleSender().sendMessage("[the-pit] Creating hologram...");
    		 Hologram holo = DHAPI.createHologram("toppitkills", location, true, lines);
    	 holo.updateAll();
    		}
    		for (Player p : Bukkit.getOnlinePlayers()) {
    			if (p.hasPermission("ajleaderboards.dontupdate.the-pit_player_kills") && !BukkitMain.getInstance().getConfig().getBoolean("disable-negation-of-ajleaderboards-no-update-permission")) {
    		        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set ajleaderboards.dontupdate.the-pit_player_kills false");
    			}
    		}
    		
    			DHAPI.updateHologram("toppitkills");
    }
    public static void loadTopPlayersHologram() {
    	new BukkitRunnable() {
    		@Override
    		public void run() {
    			if (BukkitMain.plugin.getConfig().getString("TOP.World") == null) {
    				Bukkit.getLogger().severe("[THE-PIT] TopKills is not seted. Please set it with /setthepittopkills command");
    				return;
    			}
    		      World w = Bukkit.getServer().getWorld(BukkitMain.plugin.getConfig().getString("TOP.World"));
    		  	/* 201 */           double x = BukkitMain.plugin.getConfig().getDouble("TOP.X");
    		  	/* 202 */           double y = BukkitMain.plugin.getConfig().getDouble("TOP.Y");
    		  	/* 203 */           double z = BukkitMain.plugin.getConfig().getDouble("TOP.Z");
    		  	/* 204 */           Location lobby = new Location(w, x, y, z);
    			
    			Location location = lobby;
    			handleTopPlayers(location);
    			
    		}
    	}.runTaskTimer(BukkitMain.getInstance(), 10 * 20L, 2 * (60 * 20L));
    }
    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("[THEPIT] PLUGIN DISABLED");
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
    }
    public static BukkitMain getInstance()
    /*     */   {
    /*  82 */     return instance;
    /*     */   }
    public void registerEvents(){
    	PluginManager pm = Bukkit.getPluginManager();
    	Bukkit.getConsoleSender().sendMessage("[THEPIT] EVENTS STARTED");
    	pm.registerEvents(new Eventos(), this);
    	Metrics metrics = new Metrics(this, 23696);
    	metrics.addCustomChart(new Metrics.DrilldownPie("serverAddress", () -> {
    		Map<String, Map<String, Integer>> map = new HashMap<>();
    		Map<String, Integer> entry = new HashMap<>();
    	
    		if (getConfig().getBoolean("SendIPAddressData")) entry.put(Bukkit.getServer().getIp(), 1);
    		else entry.put("Hidden", 1);
    		
    		map.put("Port " + Bukkit.getServer().getPort(), entry);
    		
    		return map;
    	}));
    	pm.registerEvents(new StatusGUI(), this);
    	pm.registerEvents(new BlockCommands(this), this);
    	pm.registerEvents(new ThePitEditMode(), this);
    	pm.registerEvents(new AntiDeathDrop(this), this);
       	pm.registerEvents(new Streak(), this);
       	pm.registerEvents(new Shop(this), this);
    }

public static void darEfeito(Player p, PotionEffectType tipo, int duracao, int level)
/*     */   {
/* 349 */     p.addPotionEffect(new PotionEffect(tipo, 20 * duracao, level));
/*     */   }
    

public static void tirarEfeitos(final Player p) {
    p.removePotionEffect(PotionEffectType.ABSORPTION);
    p.removePotionEffect(PotionEffectType.BLINDNESS);
    p.removePotionEffect(PotionEffectType.NAUSEA);
    p.removePotionEffect(PotionEffectType.RESISTANCE);
    p.removePotionEffect(PotionEffectType.MINING_FATIGUE);
    p.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
    p.removePotionEffect(PotionEffectType.INSTANT_DAMAGE);
    p.removePotionEffect(PotionEffectType.INSTANT_HEALTH);
    p.removePotionEffect(PotionEffectType.HEALTH_BOOST);
    p.removePotionEffect(PotionEffectType.HUNGER);
    p.removePotionEffect(PotionEffectType.STRENGTH);
    p.removePotionEffect(PotionEffectType.INVISIBILITY);
    p.removePotionEffect(PotionEffectType.JUMP_BOOST);
    p.removePotionEffect(PotionEffectType.NIGHT_VISION);
    p.removePotionEffect(PotionEffectType.POISON);
    p.removePotionEffect(PotionEffectType.REGENERATION);
    p.removePotionEffect(PotionEffectType.SATURATION);
    p.removePotionEffect(PotionEffectType.SLOWNESS);
    p.removePotionEffect(PotionEffectType.SPEED);
    p.removePotionEffect(PotionEffectType.WATER_BREATHING);
    p.removePotionEffect(PotionEffectType.WEAKNESS);
    p.removePotionEffect(PotionEffectType.WITHER);
}
   
    }


