package tfc.dynamic_rendering.TestingRegistry;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tfc.dynamic_rendering.CubeProperties;
import tfc.dynamic_rendering.Dynamic_rendering;
import tfc.dynamic_rendering.ItemTest;
import tfc.dynamic_rendering.TestProperties;

public class DeferredItems {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, Dynamic_rendering.ModID);
	public static final RegistryObject<Item> STACK_RENDERER_TEST = ITEMS.register("stack_renderer_test", () -> new ItemTest(new TestProperties()));
	public static final RegistryObject<Item> CUBE = ITEMS.register("cube", () -> new Item(new CubeProperties()));
	public static final RegistryObject<Item> LINE = ITEMS.register("line", () -> new Item(new CubeProperties()));
	public static final RegistryObject<Item> FACE = ITEMS.register("face", () -> new Item(new CubeProperties()));
	public static final RegistryObject<Item> TEST = ITEMS.register("pillar_thing_base", () -> new Item(new CubeProperties()));
	public static final RegistryObject<Item> TEXTURE_LOADER = ITEMS.register("texture_loader", () -> new Item(new CubeProperties()));
	public static final RegistryObject<Item> BLOCK = ITEMS.register("block", () -> new BlockItem(DeferredBlocks.BLOCK.get(),new CubeProperties()));
}
