package b3hav1.seatbelt.server;

import b3hav1.seatbelt.Seatbelt;
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
            // Получаем отправителя пакета.
            Object sender = ctx.getSender();
            if (sender == null) return;

            // Пристегиваем или отстегиваем отправителя.
            if (sender instanceof ServerPlayer player)
            Seatbelt.toggle(player);
        });

        ctx.setPacketHandled(true);
    }
}
