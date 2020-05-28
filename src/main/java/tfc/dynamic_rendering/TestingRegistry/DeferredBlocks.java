package tfc.dynamic_rendering.TestingRegistry;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tfc.dynamic_rendering.Dynamic_rendering;

public class DeferredBlocks {
	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, Dynamic_rendering.ModID);
	public static final RegistryObject<Block> BLOCK = BLOCKS.register("block", tfc.dynamic_rendering.Block::new);
}
