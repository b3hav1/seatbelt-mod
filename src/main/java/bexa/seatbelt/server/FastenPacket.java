package bexa.seatbelt.server;

import bexa.seatbelt.Seatbelt;
import java.util.function.Supplier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

public class FastenPacket 
{
    public static void encode(FastenPacket packet, FriendlyByteBuf buffer) {}

    public static FastenPacket decode(FriendlyByteBuf buffer) { return new FastenPacket(); }

    public static void handle(FastenPacket packet, Supplier<NetworkEvent.Context> provider)
    {
        NetworkEvent.Context ctx = provider.get();
        ctx.enqueueWork(() ->
        {
            Object sender = ctx.getSender();
            if (sender == null) return;

            if (sender instanceof ServerPlayer player)
            Seatbelt.fasten(player);
        });

        ctx.setPacketHandled(true);
    }
}
