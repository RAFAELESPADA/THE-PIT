package me.rafaelauler.minigame.thepit;


import org.bukkit.entity.Player;



public class Level
{
    public static Integer getLevel(final Player p) {
        final int a = XP.getXP(p);
        if (a == 0.0) {
            return 0;
        }
        if (a > 0.0 && a % BukkitMain.customization.getInt("XP-Required-To-LevelUP") >= 0) {
            return a/BukkitMain.customization.getInt("XP-Required-To-LevelUP");
        }
    return 0;
}
    public static final int getXPToLevelUp(Player p) {
        // How much XP is needed to level?
       return (BukkitMain.customization.getInt("XP-Required-To-LevelUP") - (XP.getXP(p) - BukkitMain.customization.getInt("XP-Required-To-LevelUP") * getLevel(p))); // 600XP for lvl1, 700XP for lvl2, 800XP for lvl3 ...
   }

public static String getPlayerLevelPrefix(Player username) {
    String playerLevel = String.valueOf(getLevel(username));
    return BukkitMain.customization.getString("Levels.Levels." + playerLevel + ".Prefix")
            .replace("%level%", playerLevel).replace("&", "§");
}
}
//1799
//0
