package bexa.seatbelt;

import bexa.seatbelt.server.PacketHandler;
import com.mojang.logging.LogUtils;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Seatbelt.MODID)
public class Seatbelt 
{
    public static final String MODID = "seatbelt";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Seatbelt()
    {
        @SuppressWarnings("removal")
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
    }

    // Создаем список для добавления пристегнутых игроков.
    private static final Map<UUID, Boolean> PLAYER_FASTENED = new ConcurrentHashMap<>();

    /**
     * Регистрирует отправку пользовательских сетевых пакетов.
     * @param event - событие регистрации
     */
    private void setup(final FMLCommonSetupEvent event)
    {
        PacketHandler.registerMessages();
    }

    /**
     * Отображает подсказку над хотбаром на клиенте указанного игрока.
     * @param player - игрок на сервере
     * @param key - ключ локализации
     */
    private static void displayHint(Player player, String key)
    {
        player.displayClientMessage(Component.translatable("message." + MODID + "." + key), true);
    }

    /**
     * Возвращает истину, если игрок есть в списке пристегнутых.
     * @param player - игрок на сервере
     */
    private static boolean isFastened(Player player)
    {
        return PLAYER_FASTENED.getOrDefault(player.getUUID(), false);
    }

    /**
     * Позволяет игроку управлять ремнем, если он на маунте.
     * @param player - игрок на сервере
     */
    public static void fasten(Player player)
    {
        // Сохраняем инвертированное состояние ремня.
        boolean state = !isFastened(player);
        PLAYER_FASTENED.put(player.getUUID(), state);

        // Отображаем подсказку о ремне безопасности.
        displayHint(player, state ? "fasten" : "unfasten");
    }

    /**
     * Не позволяет игроку слезть с маунта, если он пристегнут.
     * @param player - игрок на сервере
     * @param event - событие маунта
     */
    public static void dismount(Player player, EntityMountEvent event)
    {
        // Разрешаем слезть, если игрок не пристегнут.
        if (!isFastened(player)) return;

        // Отменяем событие и отображаем подсказку.
        event.setCanceled(true);
        displayHint(player, "dismount");
    }
}
