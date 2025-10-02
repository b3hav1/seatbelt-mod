package b3hav1.seatbelt.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class Display 
{
    /**
     * Отображает подсказку на клиенте указанного игрока.
     * @param player - игрок на сервере
     * @param key - ключ локализации
     */
    public static void hint(Player player, String key)
    {
        player.displayClientMessage(Component.translatable("message.seatbelt." + key), true);
    }
}
