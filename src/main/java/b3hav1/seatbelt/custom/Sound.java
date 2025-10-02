package b3hav1.seatbelt.custom;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

public class Sound 
{
    /**
     * Проигрывает звук на сервере или клиенте.
     * @param player - игрок на сервере
     * @param event - событие SoundEvents
     * @param onClient - проигрывать на клиенте
     */
    public static void play(Player player, SoundEvent event, boolean onClient)
    {
        Player listener = onClient ? player : null;
        player.level().playSound(listener, player.blockPosition(), event, SoundSource.PLAYERS);
    }

    /**
     * Проигрывает звук на сервере, исходящий от указанного игрока.
     * @param player - игрок на сервере
     * @param event - событие SoundEvents
     */
    public static void onServer(Player player, SoundEvent event)
    {
        play(player, event, false);
    }

    /**
     * Проигрывает звук на сервере, исходящий от указанного игрока.
     * @param player - игрок на сервере
     * @param event - событие SoundEvents
     */
    public static void onServer(Player player, Holder<SoundEvent> event)
    {
        play(player, event.value(), false);
    }

    /**
     * Проигрывает звук на клиенте указанного игрока.
     * @param player - игрок на сервере
     * @param event - событие SoundEvents
     */
    public static void onClient(Player player, SoundEvent event)
    {
        play(player, event, true);
    }

    /**
     * Проигрывает звук на клиенте указанного игрока.
     * @param player - игрок на сервере
     * @param event - событие SoundEvents
     */
    public static void onClient(Player player, Holder<SoundEvent> event)
    {
        play(player, event.value(), true);
    }
}
