package bexa.seatbelt;

import net.minecraft.core.Holder;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;

public class SoundUtils 
{
    /**
     * Проигрывает звук на сервере или клиенте, исходящий от указанного игрока.
     * @param target - целевой игрок
     * @param event - событие SoundEvents
     * @param onClient - проигрывать на клиенте
     */
    public static void play(Player target, SoundEvent event, boolean onClient)
    {
        Player listener = onClient ? target : null;
        target.level().playSound(listener, target.blockPosition(), event, SoundSource.PLAYERS);
    }

    /**
     * Проигрывает звук на сервере, исходящий от указанного игрока.
     * @param target - целевой игрок
     * @param event - событие SoundEvents
     */
    public static void onPlayer(Player target, SoundEvent event)
    {
        play(target, event, false);
    }

    /**
     * Проигрывает звук на сервере, исходящий от указанного игрока.
     * @param target - целевой игрок
     * @param event - событие SoundEvents
     */
    public static void onPlayer(Player target, Holder<SoundEvent> event)
    {
        play(target, event.value(), false);
    }

    /**
     * Проигрывает звук на клиенте указанного игрока.
     * @param target - целевой игрок
     * @param event - событие SoundEvents
     */
    public static void onClient(Player target, SoundEvent event)
    {
        play(target, event, true);
    }

    /**
     * Проигрывает звук на клиенте указанного игрока.
     * @param target - целевой игрок
     * @param event - событие SoundEvents
     */
    public static void onClient(Player target, Holder<SoundEvent> event)
    {
        play(target, event.value(), true);
    }
}
