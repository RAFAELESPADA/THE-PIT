package me.rafaelauler.minigame.thepit;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class Shop
  implements Listener
{
  private BukkitMain main;
  public static Inventory shop = Bukkit.getServer().createInventory(new CustomHolder3(), 27, BukkitMain.messages.getString("ShopInventoryName").replace("&", "§"));
  
  static
  {
    createButton(Material.DIAMOND_SWORD, 1, shop, 11, "§6Diamond Sword", BukkitMain.messages.getString("ShopPriceLore").replace("&", "§") + " 200g");
    createButton(Material.IRON_HELMET, 1, shop, 12, "§6Iron Helmet", BukkitMain.messages.getString("ShopPriceLore").replace("&", "§") + " 250g");
    createButton(Material.DIAMOND_LEGGINGS, 1, shop, 13, "§6Diamond Leggings", BukkitMain.messages.getString("ShopPriceLore").replace("&", "§") + " 350g");
    createButton(Material.ENCHANTED_GOLDEN_APPLE, 1, shop, 14, "§6Enchanted Golden Apple", BukkitMain.messages.getString("ShopPriceLore").replace("&", "§") + " 1200g");
    
  }
  
  public Shop(BukkitMain main)
  {
	
    this.main = main;
  }
  
  private static void createButton(Material mat, int Amount, Inventory inv, int Slot, String name, String lore)
  {
    ItemStack item = new ItemStack(mat);
    ItemMeta meta = item.getItemMeta();
    meta.setDisplayName(name);
    meta.setLore(Arrays.asList(new String[] { lore }));
    item.setItemMeta(meta);
    item.setAmount(Amount);
    inv.setItem(Slot, item);
  }

public static void openMenu(Player p)
{
	if (MainCommand.game.contains(p.getName())) {
  p.openInventory(shop);
}
}

@EventHandler
public void kit(InventoryClickEvent e)
{
	  if (e.getClickedInventory() == null) {
		return;
	}
	  if (!(e.getInventory().getHolder() instanceof CustomHolder2)) {
		  	return;
		  }
    e.setCancelled(true);
  }



@EventHandler
public void warps(InventoryClickEvent e) {
if (e.getClickedInventory() == null) {
		return;
	}
	  if (!(e.getClickedInventory().getHolder() instanceof CustomHolder2)) {
	  	return;
	  }
  Player p = (Player)e.getWhoClicked();
  ItemStack clicked = e.getCurrentItem();
  if (e.getCurrentItem() == null) {
    return;
  }
  if ((clicked.getType() == Material.DIAMOND_SWORD)) {
  	
    if (Coins.getCoins(p) >= 200)
    {
    	if (p.getInventory().getItem(0).equals(new ItemStack(Material.DIAMOND_SWORD))) {
    		p.sendMessage(ChatColor.RED +"You already has that upgrade! You need to die to buy it again");
    		return;
    	}
      p.getInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));
      Coins.removeCoins(p, 200);
      e.setCancelled(true);

      p.sendMessage(BukkitMain.messages.getString("NoFundsShop").replace("&", "§"));
    }
    else if (Coins.getCoins(p) < 200)
    {
      p.sendMessage(BukkitMain.messages.getString("NoFundsShop").replace("&", "§"));
      e.setCancelled(true);
    }
  }
  if ((clicked.getType() == Material.IRON_HELMET)) {
	  	
	    if (Coins.getCoins(p) >= 250)
	    {
	    	if (p.getInventory().getHelmet().equals(new ItemStack(Material.IRON_HELMET))) {
	    		p.sendMessage(ChatColor.RED +"You already has that upgrade! You need to die to buy it again");
	    		return;
	    	}
	      p.getInventory().setHelmet(new ItemStack(Material.IRON_HELMET));
	      Coins.removeCoins(p, 250);

	      p.sendMessage(BukkitMain.messages.getString("NoFundsShop").replace("&", "§"));
	      e.setCancelled(true);
	    }
	    else if (Coins.getCoins(p) < 250)
	    {
	      p.sendMessage(BukkitMain.messages.getString("NoFundsShop").replace("&", "§"));
	      e.setCancelled(true);
	    }
	  }

  if ((clicked.getType() == Material.DIAMOND_LEGGINGS)) {
	  	
	    if (Coins.getCoins(p) >= 350)
	    {
	    	if (p.getInventory().getLeggings().equals(new ItemStack(Material.DIAMOND_LEGGINGS))) {
	    		p.sendMessage(ChatColor.RED +"You already has that upgrade! You need to die to buy it again");
	    		return;
	    	}

		      p.getInventory().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
	      Coins.removeCoins(p, 250);

	      p.sendMessage(BukkitMain.messages.getString("NoFundsShop").replace("&", "§"));
	      e.setCancelled(true);
	    }
	    else if (Coins.getCoins(p) < 250)
	    {
	      p.sendMessage(BukkitMain.messages.getString("NoFundsShop").replace("&", "§"));
	      e.setCancelled(true);
	    }
	  }
  if ((clicked.getType() == Material.ENCHANTED_GOLDEN_APPLE)) {
	  	
	    if (Coins.getCoins(p) >= 1200)
	    {
	    	

	        p.sendMessage(BukkitMain.messages.getString("NoFundsShop").replace("&", "§"));
		      p.getInventory().addItem(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE));
	      Coins.removeCoins(p, 1200);
	      e.setCancelled(true);
	    }
	    else if (Coins.getCoins(p) < 1200)
	    {
	      p.sendMessage(BukkitMain.messages.getString("NoFundsShop").replace("&", "§"));
	      e.setCancelled(true);
	    }
	  }
  }
}
