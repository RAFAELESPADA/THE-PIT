package me.rafaelauler.minigame.thepit;




import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;

public class StatusGUI implements Listener {



    @EventHandler
    private void onPlayerInteract(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType().equals(Material.PLAYER_HEAD)) {
            if (event.getRightClicked().getType() == EntityType.ARMOR_STAND)
                event.setCancelled(true);
        }
    }
    public static Inventory getTopInventory(InventoryEvent event) {
        try {
            Object view = event.getView();
            Method getTopInventory = view.getClass().getMethod("getTopInventory");
            getTopInventory.setAccessible(true);
            return (Inventory) getTopInventory.invoke(view);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public static Inventory getTopInventory2(InventoryEvent event) {
        try {
            Object view = event.getView();
            Method getTopInventory = view.getClass().getMethod("getTopInventory");
            getTopInventory.setAccessible(true);
            return (Inventory) getTopInventory.invoke(view);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @EventHandler
    private void onInventoryClick(InventoryClickEvent event) {
    	if (event.getClickedInventory() == null) {
    		return;
    	}
    	if (!(event.getClickedInventory().getHolder() instanceof CustomHolder3)) {
    		return;
    	}
    	else if (event.getWhoClicked() instanceof Player && event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta() && !event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Display status in chat")) {
            event.setCancelled(true);
        }
    }
    @EventHandler
    private void onInv2entoryClick(InventoryClickEvent event) {
    	Player p = (Player) event.getWhoClicked();
    	Player player = (Player) event.getWhoClicked();
    	if (event.getCurrentItem() == null) {
    		return;
    	}
    	
    	if (!(event.getClickedInventory().getHolder() instanceof CustomHolder3)) {
    		return;
    	}
    
    	else if (event.getWhoClicked() instanceof Player && event.getCurrentItem().getItemMeta().getDisplayName().equals("§6Display status in chat")) {
        	int kills = AntiDeathDrop.GetKills(p);
            int deaths = AntiDeathDrop.GetDeaths(p);
            WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
    		int ks = Sun8oxData.getPvp().getThepitstreak();
            p.sendMessage(BukkitMain.messages.getString("Status").replace("&", "§").replace("%player%", p.getName()));
            p.sendMessage("");
            p.sendMessage(BukkitMain.messages.getString("StatusKills").replace("&", "§") + kills);
            p.sendMessage(BukkitMain.messages.getString("StatusDeaths").replace("&", "§") + deaths);      
            p.sendMessage(BukkitMain.messages.getString("StatusCoins").replace("&", "§") + Coins.getCoins(p));
            p.sendMessage(BukkitMain.messages.getString("StatusKS").replace("&", "§") + ks);
            p.sendMessage(BukkitMain.messages.getString("StatusXP").replace("&", "§") + XP.getXP(p));
            p.sendMessage(BukkitMain.messages.getString("StatusLevel").replace("&", "§") + Level.getLevel(p));
            p.sendMessage("§b");
            p.playSound(p.getLocation(), Sound.valueOf(BukkitMain.getInstance().getConfig().getString("Sound.ShopMenu")), 12.0F, 1.0F);
            event.setCancelled(true);
        }
    }

    public static void openGUI(Player player, Player target) {
    	
        Inventory inv = Bukkit.createInventory(new CustomHolder3(), 54, BukkitMain.messages.getString("StatusGuiInventory").replace("&", "§"));
        ItemStack glass = getCustomItemStack(Material.getMaterial(BukkitMain.messages.getString("StatusGuiMaterial")), " ", " ");
        for (int i = 0; i < 54; ++i) {
            if ((i <= 9 || i >= 17) && (i <= 27 || i >= 35)) {
                if (i <= 36 || i >= 44) {
                    inv.setItem(i, glass);
                }
            }
        }
        double kdr = AntiDeathDrop.GetDeaths(player) == 0 ? (double) AntiDeathDrop.GetKills(player) : (double) AntiDeathDrop.GetKills(player) / (double) AntiDeathDrop.GetDeaths(player);
        inv.setItem(11, glass);
        inv.setItem(12, glass);
        inv.setItem(14, glass);
        inv.setItem(15, glass);
        WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(player.getName());
		int ks = Sun8oxData.getPvp().getKillstreak();
        for (int i = 17; i > 0; i--) {
        	if(BukkitMain.messages.getString("StatusGlassMaterial") == null){
        		inv.addItem(getCustomItemStack(Material.getMaterial("YELLOW_STAINED_GLASS_PANE"), "§7(" + "§6§lLEVEL" + "§7) §b§l" + i, Arrays.asList("§a" + player.getName() + " You are currently in Level: §b" + Level.getLevel(player),  "§fYou Reached the Level §b" + i  + "§f? " + (Level.getLevel(player) >= i ? BukkitMain.messages.getString("StatusGuiYes").replace("&", "§") : BukkitMain.messages.getString("StatusGuiNo").replace("&", "§")))));
        		}
        	else
            inv.addItem(getCustomItemStack(Material.getMaterial(BukkitMain.messages.getString("StatusGlassMaterial").toUpperCase()), "§7(" + "§6§lLEVEL" + "§7) §b§l" + i, Arrays.asList("§a" + player.getName() + " You are currently in Level: §b" + Level.getLevel(player),  "§fYou Reached the Level §b" + i  + "§f? " + (Level.getLevel(player) >= i ? BukkitMain.messages.getString("StatusGuiYes").replace("&", "§") : BukkitMain.messages.getString("StatusGuiNo").replace("&", "§")))));
    }
        inv.setItem(4, editItemStack(getPlayerSkull(player.getName()), BukkitMain.messages.getString("StatusGuiInformation").replace("&", "§"), Arrays.asList("§fNick: §a" + player.getName(), "§fUUID: §a" + player.getUniqueId(), "§fCoins: §a" + new DecimalFormat("###,###.##").format(Coins.getCoins(player)), BukkitMain.messages.getString("StatusGuiFirstAcess").replace("&", "§") + " §a" + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(player.getFirstPlayed()))));
        inv.setItem(10, getCustomItemStack(Material.DIAMOND_SWORD, BukkitMain.messages.getString("StatusGuiPlayerStats").replace("&", "§"), Arrays.asList("§fKills: §a" + AntiDeathDrop.GetKills(player), "§fDeaths: §a" + AntiDeathDrop.GetDeaths(player), "§fKDR: §a" + String.format("%.2f",kdr),"§fKillstreak: §a" + ks)));
        inv.setItem(13, getCustomItemStack(Material.ENDER_EYE, BukkitMain.messages.getString("StatusGuiBoosters").replace("&", "§"), Arrays.asList("§fXP Boost: §a" + (player.hasPermission("kitpvp.doublexp") ? "Yes" : "No"), "§fCoins Boost: §a" + (player.hasPermission("kitpvp.doublecoins") ? BukkitMain.messages.getString("StatusGuiYes").replace("&", "§") : BukkitMain.messages.getString("StatusGuiNo").replace("&", "§")))));
        inv.setItem(16, getCustomItemStack(Material.EXPERIENCE_BOTTLE, BukkitMain.messages.getString("StatusGuiLevel").replace("&", "§"), Arrays.asList("§fLevel: §7(" + "§6§lLEVEL" + "§7) §b" + Level.getLevel(player), "§fNext Level: §b" + (Level.getLevel(player) + 1), "§fXP Necessary to Next Level: §b" + Level.getXPToLevelUp(player) + "XP")));
        inv.setItem(22, getCustomItemStack(Material.DIAMOND_AXE, "§6Display status in chat", Arrays.asList(BukkitMain.messages.getString("StatusGuiYourStatsLore").replace("&", "§"))));
        target.openInventory(inv);
    }

    public static ItemStack editItemStack(ItemStack itemStack, String name, List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }

    public static ItemStack getPlayerSkull(String name) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();
        meta.setOwner(name);
        itemStack.setItemMeta(meta);
        return itemStack;
    }

public static ItemStack getCustomItemStack(Material material, String name, String lore) {
    ItemStack itemStack = new ItemStack(material);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName(name);
    if (lore != null) {
        List<String> l = Collections.singletonList(lore);
        itemMeta.setLore(l);
    }
    itemStack.setItemMeta(itemMeta);
    return itemStack;
}

public static ItemStack getCustomItemStack(Material material, String name, List<String> lore) {
    ItemStack itemStack = new ItemStack(material);
    ItemMeta itemMeta = itemStack.getItemMeta();
    itemMeta.setDisplayName(name);
    itemMeta.setLore(lore);
    itemStack.setItemMeta(itemMeta);
    return itemStack;
}

}

