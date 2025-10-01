package bexa.seatbelt.events;

import bexa.seatbelt.Keybind;
import bexa.seatbelt.Seatbelt;
import bexa.seatbelt.server.FastenPacket;
import bexa.seatbelt.server.PacketHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent.Key;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.ClientTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Seatbelt.MODID, value = Dist.CLIENT)
public class PlayerEvents 
{
    @SubscribeEvent
    public static void onKeyInput(Key event)
    {
        // Получаем игрока на клиенте майнкрафта.
        Player player = Minecraft.getInstance().player;
        if (player == null) return;

        // Пристегиваем или отстегиваем игрока на сервере.
        if (Keybind.SEATBELT_KEY.consumeClick() && player.getVehicle() != null)
        PacketHandler.INSTANCE.sendToServer(new FastenPacket());
    }

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent event)
    {
        // Регистрируем только при начале события.
        if (event.phase != TickEvent.Phase.END) return;

        // Получаем клиент игрока и состояние в присяди.
        Minecraft game = Minecraft.getInstance();
        boolean sneaking = game.options.keyShift.isDown();

        // Оповещаем игрока при попытке слезть с маунта.
        Seatbelt.dismountNotify(game.player, sneaking);
    }
}