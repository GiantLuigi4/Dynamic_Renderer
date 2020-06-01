package tfc.dynamic_rendering.TestingRegistry;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tfc.dynamic_rendering.CubeProperties;
import tfc.dynamic_rendering.Dynamic_rendering;

public class TintHelper {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Dynamic_rendering.ModID);
	
	public static final RegistryObject<Item> CUBE = ITEMS.register("tint_helper", () -> new Item(new CubeProperties()));
}
