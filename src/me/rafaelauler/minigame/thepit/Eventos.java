package me.rafaelauler.minigame.thepit;



	import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import 
org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import me.RafaelAulerDeMeloAraujo.TitleAPI.TitleAPI;










	public class Eventos implements Listener {


		/*    */   @org.bukkit.event.EventHandler
		/*    */   public void onPlayerDropItem(PlayerDropItemEvent paramPlayerPickupItemEvent)
		/*    */   {
		/* 26 */     if (!MainCommand.game.contains(paramPlayerPickupItemEvent.getPlayer().getName()) || (BukkitMain.getInstance().getConfig().getString("DisableDropsOnThePit").equalsIgnoreCase("false"))) {
		/* 27 */       return;
		/*    */     }
		/* 29 */     if (MainCommand.game.contains(paramPlayerPickupItemEvent.getPlayer().getName()) && (BukkitMain.getInstance().getConfig().getString("DisableDropsOnThePit").equalsIgnoreCase("true")))
		/*    */     {
		/* 31 */       if (paramPlayerPickupItemEvent.getItemDrop().getItemStack().getType() == Material.GOLDEN_APPLE)
		/*    */       {
		/* 33 */         paramPlayerPickupItemEvent.setCancelled(false);
		/* 34 */         paramPlayerPickupItemEvent.getPlayer().playSound(paramPlayerPickupItemEvent.getPlayer().getLocation(), org.bukkit.Sound.valueOf(BukkitMain.getInstance().getConfig().getString("Sound.BowlDrop")), 1.0F, 1.0F);
		/*    */       }
		/*    */       else {
		/* 37 */         paramPlayerPickupItemEvent.setCancelled(true);
		/*    */       }
		/*    */     }
		/*    */   }
		/*    */   		/*    */ 
		/*    */ 
		/* 37 */   ArrayList<String> fall = new ArrayList<>();
		/*    */   ArrayList<String> fall2 = new ArrayList<>();
		public void Atirar(Player p) {
			int y = 8;
			Block block = p.getLocation().getBlock().getRelative(0, -1, 0);
			if (block.getType() == Material.SPONGE && MainCommand.game.contains(p.getName())) {
				Vector vector = new Vector(0, y, 0);
				p.setVelocity(vector);
				this.fall.remove(p.getName());
				p.getPlayer().getWorld().playEffect(p.getPlayer().getLocation(), Effect.MOBSPAWNER_FLAMES, 10);
				this.fall.add(p.getName());
			}
		}
		public void Atirar2(Player p) {
			final Location loc = p.getEyeLocation();

		    final Vector sponge = p.getLocation().getDirection().multiply(3.8).setY(0.45);
			Block block = p.getLocation().getBlock().getRelative(0, -1, 0);
			if (block.getType() == Material.SLIME_BLOCK && MainCommand.game.contains(p.getName())) {
				p.setVelocity(sponge);
			    p.playEffect(loc, Effect.MOBSPAWNER_FLAMES, (Object)null);
				this.fall2.remove(p.getName());
				this.fall2.add(p.getName());
			}
		}
		@EventHandler
		public void RemoverDan2o(EntityDamageEvent e) 
		{
		   if (!(e.getEntity() instanceof Player)) {
		       return;
		   }
		   Player p = (Player) e.getEntity();
		   if (e.getCause() == EntityDamageEvent.DamageCause.FALL && this.fall2.contains(p.getName()) && e.getEntity().getLocation().getY() < BukkitMain.plugin.getConfig().getInt("Spawn.Y") && MainCommand.game.contains(p.getName()) && p.getWorld().equals(Bukkit.getWorld(BukkitMain.plugin.getConfig().getString("Spawn.World"))))  {
			   e.setCancelled(true);
		
			      /*    */     }
			      /*    */     
			   /*    */     
		   this.fall2.remove(p.getName());
		   }

		@EventHandler
		private void Jumps(PlayerMoveEvent e) {
			Player p = e.getPlayer();
			Atirar(p);
			Atirar2(p);
		}

		 
		   @EventHandler
			public void RemoverDano(EntityDamageEvent e) 
			{
			   if (!(e.getEntity() instanceof Player)) {
		           return;
		       }
				Player p = (Player) e.getEntity();
				if (e.getCause() == EntityDamageEvent.DamageCause.FALL && this.fall.contains(p.getName())) 
				{
					this.fall.remove(p.getName());
					e.setCancelled(true);

				}
				else if(e.getCause() == EntityDamageEvent.DamageCause.ENTITY_ATTACK)
				{
					this.fall.remove(p.getName());
				}
			}
			
		

		/*    */   @org.bukkit.event.EventHandler
		/*    */   public void onPlayerPickupItem(PlayerPickupItemEvent paramPlayerPickupItemEvent)
		/*    */   {
		/* 46 */     if (!MainCommand.game.contains(paramPlayerPickupItemEvent.getPlayer().getName()) || (BukkitMain.getInstance().getConfig().getString("DisablePickupItemsOnThePit").equalsIgnoreCase("false"))) {
		/* 47 */       return;
		/*    */     }
		/* 49 */     if (MainCommand.game.contains(paramPlayerPickupItemEvent.getPlayer().getName()) && (BukkitMain.getInstance().getConfig().getString("DisablePickupItemsOnThePit").equalsIgnoreCase("true")))
		/*    */     {
		/* 51 */       if ((paramPlayerPickupItemEvent.getItem().getItemStack().getType() == Material.GOLDEN_APPLE))
		/*    */       {
		/* 53 */         paramPlayerPickupItemEvent.setCancelled(false);
		/*    */       } else {
		/* 55 */         paramPlayerPickupItemEvent.setCancelled(true);
		/*    */       }
		/*    */     }
		/*    */   }
		/*    */ 
		/*    */   @EventHandler
		/*    */   public void onFall(FoodLevelChangeEvent e) {
		/*    */   
			if (!(e.getEntity() instanceof Player)) {
		        return;
		    }
			Player p = (Player) e.getEntity();
			if (!(MainCommand.game.contains(p.getName()) || !BukkitMain.getInstance().getConfig().getString("DisableHungerOnThePit").equalsIgnoreCase("true"))) {
				return;
			}
			else if (!e.isCancelled()) {
		      e.setCancelled(true);
			}
			}
		@EventHandler
		public void onDeath(PlayerDeathEvent e) {
			
			if (MainCommand.game.contains((e.getEntity().getName()))) {
				
				Player victim = e.getEntity();
				e.setDeathMessage("");
				
				victim.getWorld().playEffect(victim.getLocation().add(0.0D, 1.0D, 0.0D), Effect.STEP_SOUND, 152);
				respawnPlayer(victim);
				
			}
		
		}
		@EventHandler
		/*     */   public void quickcommand3f(PlayerChangedWorldEvent e) {
			if (e.getFrom().equals(Bukkit.getWorld(BukkitMain.plugin.getConfig().getString("Spawn.World"))) && MainCommand.game.contains(e.getPlayer().getName())) {
		Player p = e.getPlayer();
		if (BukkitMain.getInstance().getConfig().getBoolean("DisableWorldLeaveThePitEvent")) {
			return;
		}
		if (BukkitMain.plugin.getConfig().getBoolean("PlayersRemainOnThePitOnLeave")) {
			return;
		}
		    	if (p.getGameMode().equals(GameMode.SPECTATOR)) {
		    		return;
		    	}
		    	if (BukkitMain.plugin.getConfig().getBoolean("bungeemode")) {
		    		return;
		    	}

		    	/* 283 */       MainCommand.game.remove(p.getName());
		    	/* 284 */       MainCommand.game.remove(p.getName());


		    	/* 303 */       p.sendMessage(String.valueOf(BukkitMain.getInstance().getConfig().getString("Prefix").replace("&", "§")) + String.valueOf(BukkitMain.getInstance().getConfig().getString("Message.ThePitLeave-Message").replace("&", "§")));
		    	/* 304 */       p.getInventory().clear();
		    	/* 305 */       p.teleport((Location)MainCommand.saveworld.get(p.getName()));
		    	/* 306 */       p.getInventory().setContents((ItemStack[])MainCommand.saveinv.get(p.getName()));
		    	/* 307 */       p.setGameMode((GameMode)MainCommand.savegamemode.get(p.getName()));
		    	p.setScoreboard(MainCommand.savescore.get(p.getName()));
		    	p.setLevel(MainCommand.savelevel.get(p.getName()));
		    	p.setFoodLevel(MainCommand.savehunger.get(p.getName()));
		    	p.setRemainingAir(MainCommand.saveair.get(p.getName()));
		    	/* 308 */       p.getInventory().setArmorContents((ItemStack[])MainCommand.savearmor.get(p.getName()));
		    	TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), BukkitMain.getInstance().getConfig().getString("Title.LeaveTitle"), BukkitMain.getInstance().getConfig().getString("Title.LeaveSubTitle"));

		    	/*     */   
		    	/* 311 */       p.updateInventory();
		    	BukkitMain.tirarEfeitos(p);
		    }
			
		}
		  @EventHandler
		  public void onSignChange2(SignChangeEvent e)
		  {
		    if (e.getLine(0).equalsIgnoreCase("[thepit]") && (e.getLine(1).equalsIgnoreCase("join")) && e.getPlayer().hasPermission("thepit.createsigns"))
		    {
		      e.setLine(0, BukkitMain.messages.getString("JoinSignLine1").replace("&", "§"));
		      e.setLine(1, BukkitMain.messages.getString("JoinSignLine2").replace("&", "§"));
		      e.setLine(2, BukkitMain.messages.getString("JoinSignLine3").replace("&", "§"));
		      e.setLine(3, BukkitMain.messages.getString("JoinSignLine4").replace("&", "§"));
		    }
		  }
		  

		  
		  @EventHandler
		  public void inv(PlayerInteractEvent e)
		  {
			  Player p = e.getPlayer();
			  if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock() != null) && (
				      (e.getClickedBlock().getType() == Material.OAK_SIGN) || (e.getClickedBlock().getType() == Material.OAK_WALL_SIGN)))
				    {
				      Sign s = (Sign)e.getClickedBlock().getState();
				      String[] lines = s.getLines();
				      if ((lines.length > 0) && (lines[0].equals(BukkitMain.messages.getString("JoinSignLine1").replace("&", "§")) && 
				        (lines.length > 1) && (lines[1].equals(BukkitMain.messages.getString("JoinSignLine2").replace("&", "§")) && 
				        (lines.length > 2) && (lines[2].equals(BukkitMain.messages.getString("JoinSignLine3").replace("&", "§")) && 
				        (lines.length > 3) && (lines[3].equals(BukkitMain.messages.getString("JoinSignLine4").replace("&", "§"))))))) {
				    	  p.performCommand("thepit join");
				      }
		  }
		  }
		  @EventHandler
		  public void onSignChangte(SignChangeEvent e)
		  {
		    if (e.getLine(0).equalsIgnoreCase("[thepit]") && (e.getLine(1).equalsIgnoreCase("leave")) && e.getPlayer().hasPermission("thepit.createsigns"))
		    {
		      e.setLine(0, BukkitMain.messages.getString("LeaveSignLine1").replace("&", "§"));
		      e.setLine(1, BukkitMain.messages.getString("LeaveSignLine2").replace("&", "§"));
		      e.setLine(2, BukkitMain.messages.getString("LeaveSignLine3").replace("&", "§"));
		      e.setLine(3, BukkitMain.messages.getString("LeaveSignLine4").replace("&", "§"));
		    }
		  }
		  

		  
		  @EventHandler
		  public void invt(PlayerInteractEvent e)
		  {
			  Player p = e.getPlayer();
			  if ((e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getClickedBlock() != null) && (
					  (e.getClickedBlock().getType() == Material.OAK_SIGN) || (e.getClickedBlock().getType() == Material.OAK_WALL_SIGN)))
				    {
				      Sign s = (Sign)e.getClickedBlock().getState();
				      String[] lines = s.getLines();
				      if ((lines.length > 0) && (lines[0].equals(BukkitMain.messages.getString("LeaveSignLine1").replace("&", "§")) && 
				        (lines.length > 1) && (lines[1].equals(BukkitMain.messages.getString("LeaveSignLine2").replace("&", "§")) && 
				        (lines.length > 2) && (lines[2].equals(BukkitMain.messages.getString("LeaveSignLine3").replace("&", "§")) && 
				        (lines.length > 3) && (lines[3].equals(BukkitMain.messages.getString("LeaveSignLine4").replace("&", "§"))))))) {
				    	  p.chat("/thepit leave");
				      }
		  }
		  }
		  @EventHandler
		  public void NoExplodeMyMapPlease(EntityExplodeEvent e)
		  {
			  World w = Bukkit.getServer().getWorld(BukkitMain.plugin.getConfig().getString("Spawn.World"));
			  if (e.getEntity().getWorld().equals(w)) {
		    e.setCancelled(true);
			  }
		  }
		  @EventHandler(priority = EventPriority.HIGHEST)
		  public void onEntityDamageByEntity(final EntityDamageByEntityEvent event) {
			  boolean isCitizensNPC = event.getEntity().hasMetadata("NPC");
			  boolean isCitizensNPC2 = event.getDamager().hasMetadata("NPC");
if (isCitizensNPC || isCitizensNPC2) {
	return;
}
		  	if (event.getEntity() instanceof Player && (event.getDamager() instanceof Player)) {
		  	if (MainCommand.game.contains(event.getEntity().getName()) && !MainCommand.game.contains(event.getDamager().getName())) {
		  		event.getDamager().sendMessage(ChatColor.BLUE + "You cannot attack " + event.getEntity().getName() + " because he is on THEPIT and you are not");
		  		event.setCancelled(true);
		  	}
		  	if (!MainCommand.game.contains(event.getEntity().getName()) && MainCommand.game.contains(event.getDamager().getName())) {
		  		event.getDamager().sendMessage(ChatColor.BLUE + "You cannot attack " + event.getEntity().getName() + " because you are on THEPIT and he is not");
		  		event.setCancelled(true);
		  	}
		  	}
		  	}
		private void respawnPlayer(Player p) {
				
				Location deathLocation = p.getLocation();
				Bukkit.getScheduler().scheduleSyncDelayedTask(BukkitMain.getInstance(), () -> p.spigot().respawn(), 1);
				
				p.setGameMode(GameMode.SPECTATOR);
				p.teleport(deathLocation);
				

									new BukkitRunnable() {
						
						
						
						@Override
						public void run() {
							
							
							
								

							/* 191 */       p.getInventory().setArmorContents(null);
								
								ItemStack espada = new ItemStack(Material.IRON_SWORD);

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
								/*     */      World w = Bukkit.getServer().getWorld(BukkitMain.plugin.getConfig().getString("SpawnD.World"));
								/* 153 */      double x = BukkitMain.plugin.getConfig().getDouble("SpawnD.X");
								/* 154 */      double y = BukkitMain.plugin.getConfig().getDouble("SpawnD.Y");
								/* 155 */      double z = BukkitMain.plugin.getConfig().getDouble("SpawnD.Z");
								/* 156 */      Location lobby = new Location(w, x, y, z);
								/* 157 */      lobby.setPitch((float)BukkitMain.plugin.getConfig().getDouble("SpawnD.Pitch"));
								/* 158 */      lobby.setYaw((float)BukkitMain.plugin.getConfig().getDouble("SpawnD.Yaw"));
	

								/*     */ 
								/*     */ 
								/* 107 */       
								/*     */       
								/*     */ 
								/*     */ 
								/*     */ 
								/* 187 */       p.setExp(0.0F);
								/* 188 */       p.setExhaustion(20.0F);
								/* 189 */       p.setFireTicks(0);
								/* 190 */       p.setFoodLevel(20000);
								/*     */       
								/* 193 */       p.updateInventory();
								/*     */       p.setGameMode(GameMode.SURVIVAL);
								/* 199 */       
								p.teleport(lobby);
								p.sendMessage(ChatColor.YELLOW + "You respawned");
								p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 0));
								p.playSound(p.getLocation(), org.bukkit.Sound.valueOf(BukkitMain.getInstance().getConfig().getString("Sound.RespawnSucess")), 3.0F, 3.0F);
								
								
								cancel();
								
		
						}
						
					}.runTask(BukkitMain.getInstance());
new BukkitRunnable() {
						
						
						
						@Override
						public void run() {
							/*     */      World w = Bukkit.getServer().getWorld(BukkitMain.plugin.getConfig().getString("SpawnD.World"));
							/* 153 */      double x = BukkitMain.plugin.getConfig().getDouble("SpawnD.X");
							/* 154 */      double y = BukkitMain.plugin.getConfig().getDouble("SpawnD.Y");
							/* 155 */      double z = BukkitMain.plugin.getConfig().getDouble("SpawnD.Z");
							/* 156 */      Location lobby = new Location(w, x, y, z);
							/* 157 */      lobby.setPitch((float)BukkitMain.plugin.getConfig().getDouble("SpawnD.Pitch"));
							/* 158 */      lobby.setYaw((float)BukkitMain.plugin.getConfig().getDouble("SpawnD.Yaw"));
p.teleport(lobby);
						}}.runTaskLater(BukkitMain.getInstance(), 10L);
				}
		

		 @EventHandler
		    public void Quebrar(final PlayerMoveEvent e) {
		        final Player p = e.getPlayer();
		        final ItemStack item1 = p.getItemInHand();
		        if (!MainCommand.game.contains(p.getName())) {
		        	return;
		        }
		        final Material material1 = Material.getMaterial(item1.getType().toString());
		        if (!material1.isBlock() && (!(material1 == Material.LEATHER_LEGGINGS)) && (!(material1 == Material.LEATHER_HELMET)) && (!(material1 == Material.IRON_HELMET)) && (!(material1 == Material.IRON_CHESTPLATE)) && (!(material1 == Material.IRON_BOOTS)) && (!(material1 == Material.IRON_LEGGINGS)) && (!(material1 == Material.LEATHER_BOOTS)) && (!(material1 == Material.GOLDEN_CHESTPLATE	        	)) && (!(material1 == Material.GOLDEN_HELMET)) && (!(material1 == Material.CHAINMAIL_CHESTPLATE)) && (!(material1 == Material.CHAINMAIL_HELMET)) && (!(material1 == Material.CHAINMAIL_BOOTS)) && (!(material1 == Material.CHAINMAIL_LEGGINGS)) && material1.getMaxDurability() >= 1 && item1.getDurability() != 0) {
		            p.getItemInHand().setDurability((short)0);
		            p.updateInventory();
		        }
		 }
		
		
		/*     */   @EventHandler
		/*     */   public void onEvent(PlayerJoinEvent e)
		/*     */   {
		     Player p = e.getPlayer(); 
		     if (!BukkitMain.plugin.getConfig().getBoolean("bungeemode")) {
			 return;
		}
		if (!MainCommand.game.contains(p.getName())) {
		/*  74 */     MainCommand.game.add(p.getName());
		}
		new BukkitRunnable() {	
			@Override
				public void run() {
		/*     */           if (!MainCommand.game.contains(p.getName())) {
			return;
		}
		/*     */ 	/*     */       ;

				}}.runTaskTimer(BukkitMain.getInstance(), 10 * 20L, 20L * BukkitMain.getInstance().getConfig().getInt("ScoreBoard-Interval-Update"));




			/*     */ 
			/* 200 */           World w = Bukkit.getServer().getWorld(BukkitMain.plugin.getConfig().getString("Spawn.World"));
			/* 201 */           double x = BukkitMain.plugin.getConfig().getDouble("Spawn.X");
			/* 202 */           double y = BukkitMain.plugin.getConfig().getDouble("Spawn.Y");
			/* 203 */           double z = BukkitMain.plugin.getConfig().getDouble("Spawn.Z");
			/* 204 */           Location lobby = new Location(w, x, y, z);


			/*     */ 
			/* 211 */           lobby.setPitch((float)BukkitMain.plugin.getConfig().getDouble("Spawn.Pitch"));
			/* 212 */           lobby.setYaw((float)BukkitMain.plugin.getConfig().getDouble("Spawn.Yaw"));
			/* 213 */           p.getInventory().clear();
			/*     */           
			/*     */ 
			/* 216 */           p.teleport(lobby);
			/*     */           
			/*     */ 
			/* 219 */           p.getInventory().clear();
			/* 220 */           p.getInventory().setArmorContents(null);
			/*  94 */       			/* 107 */       p.updateInventory();	p.getInventory().setArmorContents(null);
			/*     */       								ItemStack espada = new ItemStack(Material.IRON_SWORD);

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
			/* 107 */       p.updateInventory();
			/*     */           
			/*     */ 

			/*     */ 
			/* 235 */           p.setExp(0.0F);
			/* 236 */           p.setExhaustion(20.0F);
			/* 237 */           p.setFireTicks(0);
			/* 238 */           p.setFoodLevel(20000);
			/* 239 */           TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), BukkitMain.getInstance().getConfig().getString("Title.JoinTitle"), BukkitMain.getInstance().getConfig().getString("Title.JoinSubTitle"));
			BukkitMain.tirarEfeitos(p);
		}
		@EventHandler
		/*     */   public void onEvent2(PlayerJoinEvent e)
		/*     */   {
		     Player p = e.getPlayer(); 
		     if (!BukkitMain.plugin.getConfig().getBoolean("PlayersRemainOnThePitOnLeave")) {
			 return;
		}
		     if (BukkitMain.plugin.getConfig().getBoolean("bungeemode")) {
		    	 return;
		    }
		if (!MainCommand.game.contains(p.getName())) {
		/*  74 */     return;
		}
		new BukkitRunnable() {	
			@Override
				public void run() {
		/*     */           if (!MainCommand.game.contains(p.getName())) {
			return;
		}
		/*     */ 	/*     */       ;

				}};



		Bukkit.getConsoleSender().sendMessage("Putting " + p.getName() + " back on THEPIT!");
			/*     */ if (!MainCommand.game.contains(p.getName())) {
				Bukkit.getConsoleSender().sendMessage("Adding " + p.getName() + " thepit variable!");
				/*  74 */     MainCommand.game.add(p.getName());
				MainCommand.game.add(p.getName());
			}
			MainCommand.game.add(p.getName());
			/* 200 */           World w = Bukkit.getServer().getWorld(BukkitMain.plugin.getConfig().getString("Spawn.World"));
			/* 201 */           double x = BukkitMain.plugin.getConfig().getDouble("Spawn.X");
			/* 202 */           double y = BukkitMain.plugin.getConfig().getDouble("Spawn.Y");
			/* 203 */           double z = BukkitMain.plugin.getConfig().getDouble("Spawn.Z");
			/* 204 */           Location lobby = new Location(w, x, y, z);


			/*     */ 
			/* 211 */           lobby.setPitch((float)BukkitMain.plugin.getConfig().getDouble("Spawn.Pitch"));
			/* 212 */           lobby.setYaw((float)BukkitMain.plugin.getConfig().getDouble("Spawn.Yaw"));
			/* 213 */           p.getInventory().clear();
			/*     */           
			/*     */ 
			/* 216 */           p.teleport(lobby);
			/*     */           
			/*     */ 
			/* 219 */           p.getInventory().clear();
			/* 220 */           p.getInventory().setArmorContents(null);
			ItemStack espada = new ItemStack(Material.IRON_SWORD);

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
			/* 107 */       p.updateInventory();
			/*     */           
			/*     */ 

			/*     */ 
			/* 235 */           p.setExp(0.0F);
			/* 236 */           p.setExhaustion(20.0F);
			/* 237 */           p.setFireTicks(0);
			/* 238 */           p.setFoodLevel(20000);			TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), BukkitMain.getInstance().getConfig().getString("Title.JoinTitle"), BukkitMain.getInstance().getConfig().getString("Title.JoinSubTitle"));

			BukkitMain.tirarEfeitos(p);
		}
		/*     */   
		
		
		/*     */   @EventHandler
		/*     */   public void onLeave(PlayerQuitEvent e)
		/*     */   {
		/* 117 */     Player p = e.getPlayer();
		/*     */     if (MainCommand.game.contains(p.getName())&& !BukkitMain.plugin.getConfig().getBoolean("bungeemode") && !BukkitMain.plugin.getConfig().getBoolean("PlayersRemainOnThePitOnLeave")) {
			/*     */ 
			/* 283 */       MainCommand.game.remove(p.getName());
			/* 284 */       MainCommand.game.remove(p.getName());
			/* 285 */       MainCommand.game.remove(p.getName());
			/* 286 */       MainCommand.game.remove(p.getName());
			/* 287 */       MainCommand.game.remove(p.getName());
			/* 288 */       MainCommand.game.remove(p.getName());
			/* 289 */       MainCommand.game.remove(p.getName());
			/* 290 */       MainCommand.game.remove(p.getName());
			/* 291 */       MainCommand.game.remove(p.getName());
			/* 292 */       MainCommand.game.remove(p.getName());
			/* 293 */       MainCommand.game.remove(p.getName());
			/* 294 */       MainCommand.game.remove(p.getName());MainCommand.game.remove(p.getName());
			/* 295 */       MainCommand.game.remove(p.getName());
			/* 296 */       MainCommand.game.remove(p.getName());
			/* 297 */       MainCommand.game.remove(p.getName());

			/*     */ 
			/*     */ 
			/*     */ 
			/* 303 */       p.sendMessage(String.valueOf(BukkitMain.getInstance().getConfig().getString("Prefix").replace("&", "§")) + String.valueOf(BukkitMain.getInstance().getConfig().getString("Message.ThePitLeave-Message").replace("&", "§")));
			/* 304 */       p.getInventory().clear();
			/* 305 */       p.teleport((Location)MainCommand.saveworld.get(p.getName()));
			/* 306 */       p.getInventory().setContents((ItemStack[])MainCommand.saveinv.get(p.getName()));
			/* 307 */       p.setGameMode((GameMode)MainCommand.savegamemode.get(p.getName()));
			p.setScoreboard(MainCommand.savescore.get(p.getName()));
			p.setLevel(MainCommand.savelevel.get(p.getName()));
			p.setFoodLevel(MainCommand.savehunger.get(p.getName()));
			p.setRemainingAir(MainCommand.saveair.get(p.getName()));
			/* 308 */       p.getInventory().setArmorContents((ItemStack[])MainCommand.savearmor.get(p.getName()));
			TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), BukkitMain.getInstance().getConfig().getString("Title.LeaveTitle"), BukkitMain.getInstance().getConfig().getString("Title.LeaveSubTitle"));

			/*     */   
			/* 311 */       p.updateInventory();
		 BukkitMain.tirarEfeitos(p);
		}
		/*     */   }
		@EventHandler
		/*     */   public void onEventt(PlayerJoinEvent e)
		/*     */   {
		     Player p = e.getPlayer(); 
		     if (p.getGameMode() == GameMode.SPECTATOR) {
		    	 p.setGameMode(GameMode.SURVIVAL);
		     }
		}
		/*     */   
		/*     */   @EventHandler
		/*     */   public void onLeave(PlayerKickEvent e)
		/*     */   {
			
		/* 128 */     Player p = e.getPlayer();
		/*     */     if (MainCommand.game.contains(p.getName()) && !BukkitMain.plugin.getConfig().getBoolean("bungeemode") && !BukkitMain.plugin.getConfig().getBoolean("PlayersRemainOnThePitOnLeave")) {
			/*     */ 
			/* 283 */       MainCommand.game.remove(p.getName());
			/* 284 */       MainCommand.game.remove(p.getName());
			/* 285 */       MainCommand.game.remove(p.getName());
			/* 286 */       MainCommand.game.remove(p.getName());
			/* 287 */       MainCommand.game.remove(p.getName());
			/* 288 */       MainCommand.game.remove(p.getName());
			/* 289 */       MainCommand.game.remove(p.getName());
			/* 290 */       MainCommand.game.remove(p.getName());
			/* 291 */       MainCommand.game.remove(p.getName());
			/* 292 */       MainCommand.game.remove(p.getName());
			/* 293 */       MainCommand.game.remove(p.getName());
			/* 294 */       MainCommand.game.remove(p.getName());MainCommand.game.remove(p.getName());
			/* 295 */       MainCommand.game.remove(p.getName());
			/* 296 */       MainCommand.game.remove(p.getName());
			/* 297 */       MainCommand.game.remove(p.getName());

			/*     */ 
			/*     */ 
			/*     */ 
			/* 303 */       p.sendMessage(String.valueOf(BukkitMain.getInstance().getConfig().getString("Prefix").replace("&", "§")) + String.valueOf(BukkitMain.getInstance().getConfig().getString("Message.ThePitLeave-Message").replace("&", "§")));
			/* 304 */       p.getInventory().clear();
			/* 305 */       p.teleport((Location)MainCommand.saveworld.get(p.getName()));
			/* 306 */       p.getInventory().setContents((ItemStack[])MainCommand.saveinv.get(p.getName()));
			/* 307 */       p.setGameMode((GameMode)MainCommand.savegamemode.get(p.getName()));
			p.setScoreboard(MainCommand.savescore.get(p.getName()));
			p.setLevel(MainCommand.savelevel.get(p.getName()));
			p.setFoodLevel(MainCommand.savehunger.get(p.getName()));
			p.setRemainingAir(MainCommand.saveair.get(p.getName()));
			/* 308 */       p.getInventory().setArmorContents((ItemStack[])MainCommand.savearmor.get(p.getName()));
			TitleAPI.sendTitle(p, Integer.valueOf(40), Integer.valueOf(80), Integer.valueOf(40), BukkitMain.getInstance().getConfig().getString("Title.LeaveTitle"), BukkitMain.getInstance().getConfig().getString("Title.LeaveSubTitle"));

			/*     */   
			/* 311 */       p.updateInventory();
			BukkitMain.tirarEfeitos(p);
		}
		}
	
		/*     */   

				
			
		@EventHandler
		public void onShot(EntityDamageByEntityEvent e) {
			
			if (MainCommand.game.contains(e.getEntity().getName())) {
				
				if (e.getEntity() instanceof Player && e.getDamager() instanceof Arrow) {
					
					Player damagedPlayer = (Player) e.getEntity();
					Arrow arrow = (Arrow) e.getDamager();
					
					if (arrow.getShooter() != null && arrow.getShooter() instanceof Player) {
						
						Player shooter = (Player) arrow.getShooter();
						
						// ARROW HEALTH MESSAGE
						
						if (damagedPlayer.getName() != shooter.getName()) {
							
							new BukkitRunnable() {
								
								@Override
								public void run() {
									
									double health = Math.round(damagedPlayer.getHealth() * 10.0) / 10.0;
									
										if (health != 20.0) {	
											
											shooter.sendMessage(MainCommand.NomeServer + "" + BukkitMain.messages.getString("ArrowHit-Message").replace("%player%", damagedPlayer.getName()).replace("%health%", String.valueOf(health)).replace("&", "§"));									
										}						
																	}							
								}
								
							.runTaskLater(BukkitMain.instance, 2L);
							
						}
			
	
					}
				}

    }}}
