package net.helix.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.ItemStack;


public class HelixCooldown2 {

    private final static HashMap<String, HashMap<String, Long>> cooldowns = new HashMap<>();
    public static final Map<UUID, List<HelixCooldownAPI>> map = new ConcurrentHashMap<>();

    public static void removeAllCooldowns(Player player) {
        if (map.containsKey(player.getUniqueId())) {
            List<HelixCooldownAPI> list = map.get(player.getUniqueId());
            Iterator<HelixCooldownAPI> it = list.iterator();
            while (it.hasNext()) {
                HelixCooldownAPI cooldown = it.next();
                it.remove();
            }
        }
    }

    public static void sendMessage(Player player, String name) {
        if (map.containsKey(player.getUniqueId())) {
            List<HelixCooldownAPI> list = map.get(player.getUniqueId());
            for (HelixCooldownAPI cooldown : list)
                if (cooldown.getName().equals(name)) {
                    display(player , cooldown);
                }
        }
    }

    public static void addCooldown(Player player, HelixCooldownAPI cooldown) {
        CooldownStartEvent event = new CooldownStartEvent(player, cooldown);
        Bukkit.getServer().getPluginManager().callEvent(event);
        if (!event.isCancelled()) {
            List<HelixCooldownAPI> list = map.computeIfAbsent(player.getUniqueId(), v -> new ArrayList<>());
            list.add(cooldown);
        }
    }

    public static boolean removeCooldown(Player player, String name) {
        if (map.containsKey(player.getUniqueId())) {
            List<HelixCooldownAPI> list = map.get(player.getUniqueId());
            Iterator<HelixCooldownAPI> it = list.iterator();
            while (it.hasNext()) {
                HelixCooldownAPI cooldown = it.next();
                if (cooldown.getName().equals(name)) {
                    it.remove();
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean hasCooldown(Player player, String name) {
        if (map.containsKey(player.getUniqueId())) {
            List<HelixCooldownAPI> list = map.get(player.getUniqueId());
            for (HelixCooldownAPI cooldown : list)
                if (cooldown.getName().equals(name)) {
                    return true;
                }
        }
        return false;
    }

    public static boolean inCooldown(Player player, String name) {
        if (map.containsKey(player.getUniqueId())) {
            List<HelixCooldownAPI> list = map.get(player.getUniqueId());
            for (HelixCooldownAPI cooldown : list)
                if (cooldown.getName().equals(name)) {
                    if (cooldown.expired()) {
                        return false;
                    } else {
                        return true;
                    }
                }
        }
        return false;
    }


    @EventHandler
    public void onUpdate(UpdateEvent event) {
        if (event.getType() != UpdateEvent.UpdateType.SEGUNDO)
            return;

        for (UUID uuid : map.keySet()) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null) {
                List<HelixCooldownAPI> list = map.get(uuid);
                Iterator<HelixCooldownAPI> it = list.iterator();

                /* Found Cooldown */
                HelixCooldownAPI found = null;
                while (it.hasNext()) {
                    HelixCooldownAPI cooldown = it.next();
                    if (!cooldown.expired()) {
                        if (cooldown instanceof ItemCooldown) {
                            ItemStack hand = player.getInventory().getItemInHand();
                            if (hand != null && hand.getType() != Material.AIR) {
                                ItemCooldown item = (ItemCooldown) cooldown;
                                if (hand.equals(item.getItem())) {
                                    item.setSelected(true);
                                    found = item;
                                    break;
                                }
                            }
                            continue;
                        }
                        found = cooldown;
                        continue;
                    }
                    it.remove();
                    CooldownFinishEvent e = new CooldownFinishEvent(player, cooldown);
                    Bukkit.getServer().getPluginManager().callEvent(e);
                }

                /* Display Cooldown */
                if (found != null) {
                    display(player, found);
                } else if (list.isEmpty()) {
                    map.remove(uuid);
                } else {
                    HelixCooldownAPI cooldown = list.get(0);
                    if (cooldown instanceof ItemCooldown) {
                        ItemCooldown item = (ItemCooldown) cooldown;
                        if (item.isSelected()) {
                            item.setSelected(false);
                        }
                    }
                }
            }
        }
    }


    public static void display(Player player, HelixCooldownAPI cooldown) {
        StringBuilder bar = new StringBuilder();
        double percentage = cooldown.getPercentage();
        double remaining = cooldown.getRemaining();
        double count = 20 - Math.max(percentage > 0D ? 1 : 0, percentage / 5);

        for (int a = 0; a < count; a++)
            bar.append("§a§l:");

        for (int a = 0; a < 20 - count; a++)
            bar.append("§c§l:");

        String name = cooldown.getName();
    }
}
