package net.kyrptonaught.inventorysorter;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.keybinding.FabricKeyBinding;
import net.fabricmc.fabric.api.client.keybinding.KeyBindingRegistry;
import net.kyrptonaught.inventorysorter.config.ConfigManager;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class InventorySorterMod implements ModInitializer, ClientModInitializer {
    public static final String MOD_ID = "inventorysorter";
    private static final String KEY_BINDING_CATEGORY = "key.categories." + MOD_ID;
    public static ConfigManager config = new ConfigManager();
    public static FabricKeyBinding keyBinding;

    @Override
    public void onInitialize() {
        InventorySortPacket.registerReceivePacket();
    }

    @Override
    public void onInitializeClient() {
        config.loadConfig();
        keyBinding = FabricKeyBinding.Builder.create(
                new Identifier(MOD_ID, "sort"),
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_P,
                KEY_BINDING_CATEGORY
        ).build();
        KeyBindingRegistry.INSTANCE.addCategory(KEY_BINDING_CATEGORY);
        KeyBindingRegistry.INSTANCE.register(keyBinding);
    }
}
