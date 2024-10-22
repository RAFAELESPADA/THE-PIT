package net.helix.core.util;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class HelixCooldown {
    private static final HashMap<String, HashMap<String, Long>> cooldowns = new HashMap<>();

    public static void create(String playerName, String key, TimeUnit timeUnit, long time) {
        HashMap<String, Long> entry = cooldowns.containsKey(playerName) ? cooldowns.get(playerName) : new HashMap<>();
        entry.put(key, Long.valueOf(System.currentTimeMillis() + timeUnit.toMillis(time)));
        cooldowns.put(playerName, entry);
    }

    public static boolean has(String playerName, String key) {
        return (cooldowns.containsKey(playerName) && ((HashMap)cooldowns.get(playerName)).containsKey(key));
    }

    public static boolean inCooldown(String playerName, String key) {
        return (has(playerName, key) && ((Long)((HashMap)cooldowns.get(playerName)).get(key)).longValue() > System.currentTimeMillis());
    }

    public static void delete(String playerName, String key) {
        if (has(playerName, key))
            ((HashMap)cooldowns.get(playerName)).remove(key);
    }

    public static double getTime(String playerName, String key) {
        return has(playerName, key) ? Double.parseDouble(String.valueOf(TimeUnit.MILLISECONDS
                .toSeconds(((Long)((HashMap)cooldowns.get(playerName)).get(key)).longValue() - System.currentTimeMillis()))) : 0.0D;
    }
}
