package me.rafaelauler.minigame.thepit;


import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import net.wavemc.core.bukkit.WaveBukkit;
import net.wavemc.core.bukkit.account.WavePlayer;

/**
 * This class will be registered through the register-method in the 
 * plugins onEnable-method.
 */
 public class PlaceHolderAPIHook extends PlaceholderExpansion {

	 /*    */   
	 /*    */   static BukkitMain plugin;

    /**
     * Since we register the expansion inside our own plugin, we
     * can simply use this method here to get an instance of our
     * plugin.
     *
     * @param plugin
     *        The instance of our plugin.
     */
    /*    */   public PlaceHolderAPIHook(BukkitMain main)
    /*    */   {
    /* 20 */     this.plugin = main;
    /* 21 */     plugin = main;
    /*    */   }
    /*    */   

    /**
     * Because this is an internal class,
     * you must override this method to let PlaceholderAPI know to not unregister your expansion class when
     * PlaceholderAPI is reloaded
     *
     * @return true to persist through reloads
     */
    @Override
    public boolean persist(){
        return true;
    }

    /**
     * Because this is a internal class, this check is not needed
     * and we can simply return {@code true}
     *
     * @return Always true since it's an internal class.
     */
    @Override
    public boolean canRegister(){
        return true;
    }

    /**
     * The name of the person who created this expansion should go here.
     * <br>For convienience do we return the author from the plugin.yml
     * 
     * @return The name of the author as a String.
     */
    @Override
    public String getAuthor(){
        return plugin.getDescription().getAuthors().toString();
    }

    /**
     * The placeholder identifier should go here.
     * <br>This is what tells PlaceholderAPI to call our onRequest 
     * method to obtain a value if a placeholder starts with our 
     * identifier.
     * <br>This must be unique and can not contain % or _
     *
     * @return The identifier in {@code %<identifier>_<value>%} as String.
     */
    @Override
    public String getIdentifier(){
        return "the-pit";
    }

    /**
     * This is the version of the expansion.
     * <br>You don't have to use numbers, since it is set as a String.
     *
     * For convienience do we return the version from the plugin.yml
     *
     * @return The version as a String.
     */
    @Override
    public String getVersion(){
        return plugin.getDescription().getVersion();
    }

    /**
     * This is the method called when a placeholder with our identifier 
     * is found and needs a value.
     * <br>We specify the value identifier in this method.
     * <br>Since version 2.9.1 can you use OfflinePlayers in your requests.
     *
     * @param  player
     *         A {@link org.bukkit.Player Player}.
     * @param  identifier
     *         A String containing the identifier/value.
     *
     * @return possibly-null String of the requested identifier.
     */


    @Override
  public String onPlaceholderRequest(Player p, String indentifier)
  {
    if (indentifier.equals("player_kills")) {
      return String.valueOf(AntiDeathDrop.GetKills(p));
    }
    if (p == null) {
      return "";
    }
    if (indentifier.equals("player_deaths")) {
      return String.valueOf(AntiDeathDrop.GetDeaths(p));
    }
    if (indentifier.equals("players_count_total")) {
      return String.valueOf(MainCommand.game.size());
    }
    if (indentifier.equals("player_levelprefix")) {
        return String.valueOf(Level.getPlayerLevelPrefix(p));
      }
    if (indentifier.equals("player_streak"))
    {
    	WavePlayer Sun8oxData = WaveBukkit.getInstance().getPlayerManager().getPlayer(p.getName());
    	if (Sun8oxData == null) {
    		  return "0";
    	  }
		int kills = Sun8oxData.getPvp().getThepitstreak();
      
    return Integer.toString(kills);
    }
    if (indentifier.equals("player_gold")) {
        Integer coins = Coins.getCoins(p);
        return Integer.toString(coins);
      }
    if (indentifier.equals("player_xp")) {
        int xp = XP.getXP(p);
        return String.valueOf(xp);
      }
    if (indentifier.equals("player_level")) {
        Integer level = Level.getLevel(p);
        return String.valueOf(level);
      }
    if (indentifier.equals("player_xp_to_levelup")) {
        Integer level = Level.getXPToLevelUp(p);
        return String.valueOf(level);
      }
    if (indentifier.equals("player_is_on_kitpvp")) {
        return String.valueOf(MainCommand.game.contains(p.getName()));
      }
    
   
    return null;
  }
}

