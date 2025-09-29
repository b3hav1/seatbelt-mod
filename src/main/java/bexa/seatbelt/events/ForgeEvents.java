package bexa.seatbelt.events;

import bexa.seatbelt.Keybind;
import bexa.seatbelt.Seatbelt;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Seatbelt.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeEvents 
{
    @SubscribeEvent
    public static void onKeyRegister(RegisterKeyMappingsEvent event)
    {
        event.register(Keybind.SEATBELT_KEY);
    }
}
