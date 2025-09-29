package bexa.seatbelt.server;

import bexa.seatbelt.Seatbelt;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler 
{
    private static final String VERSION = "1";

    @SuppressWarnings("removal")
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel
    (
        new ResourceLocation(Seatbelt.MODID, "main"),
        () -> VERSION, VERSION::equals, VERSION::equals
    );
        
    private static int id = 0;
    public static void registerMessages()
    {
        INSTANCE.registerMessage
        (
            id++,
            FastenPacket.class,
            FastenPacket::encode,
            FastenPacket::decode,
            FastenPacket::handle
        );
    }
}
 