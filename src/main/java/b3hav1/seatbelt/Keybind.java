package b3hav1.seatbelt;

import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class Keybind 
{
    private static final String KEYCAT_MISC = "key.categories.misc"; 
    private static final String KEY_SEATBELT = "key.seatbelt.toggle";
    
    public static final KeyMapping SEATBELT_KEY = new KeyMapping(KEY_SEATBELT, GLFW.GLFW_KEY_B, KEYCAT_MISC);
}
