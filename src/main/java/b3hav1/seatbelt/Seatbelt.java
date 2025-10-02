package b3hav1.seatbelt;

import b3hav1.seatbelt.custom.Display;
import b3hav1.seatbelt.custom.Sound;
import b3hav1.seatbelt.server.PacketHandler;
import com.mojang.logging.LogUtils;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.sounds.SoundEvents;
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

    // Список пристегнутых игроков и флаг уведомления.
    private static final Map<UUID, Boolean> PLAYER_FASTENED = new ConcurrentHashMap<>();
    private static boolean playerNotified = false;
    
    /**
     * Регистрирует отправку пользовательских сетевых пакетов.
     * @param event - событие регистрации
     */
    private void setup(final FMLCommonSetupEvent event)
    {
        PacketHandler.registerMessages();
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
        
        // Проигрываем уведомление о ремне безопасности.
        Display.hint(player, state ? "fasten" : "unfasten");
        Sound.onServer(player, state ? SoundEvents.ARMOR_EQUIP_LEATHER : SoundEvents.ARMOR_EQUIP_GENERIC);
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
        
        // Отменяем событие и проигрываем предупреждение.
        event.setCanceled(true);
        Display.hint(player, "dismount");
    }

    /**
     * Оповещает игрока при необходимости отстегнуть ремень.
     * @param player - игрок на сервере
     * @param dismount - его попытка слезть
     */
    public static void dismountNotify(Player player, boolean dismount)
    {
        // При нажатии клавиши, если игрок пристегнут.
        if (dismount && !playerNotified && isFastened(player))
        Sound.onClient(player, SoundEvents.NOTE_BLOCK_HAT);
    
        // Инвертируем флаг уведомления.
        playerNotified = dismount;
    }
}
