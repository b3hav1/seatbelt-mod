package b3hav1.seatbelt.events;

import b3hav1.seatbelt.Seatbelt;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Seatbelt.MODID)
public class ServerEvents 
{
    @SubscribeEvent
    public static void onMount(EntityMountEvent event)
    {
        // При попытке игрока на сервере слезть с маунта.
        if (event.getEntityMounting() instanceof Player player && event.isDismounting() && !player.level().isClientSide)
        Seatbelt.dismount(player, event);
    }
}
