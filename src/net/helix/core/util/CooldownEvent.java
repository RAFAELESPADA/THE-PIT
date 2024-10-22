package net.helix.core.util;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;


@RequiredArgsConstructor
public abstract class CooldownEvent extends Event {

    @Getter
    @NonNull
    private Player player;

    @Getter
    @NonNull
    private HelixCooldownAPI cooldown;
}
