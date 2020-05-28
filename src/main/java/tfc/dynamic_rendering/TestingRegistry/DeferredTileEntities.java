package tfc.dynamic_rendering.TestingRegistry;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tfc.dynamic_rendering.Block;
import tfc.dynamic_rendering.Dynamic_rendering;

public class DeferredTileEntities {
	public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, Dynamic_rendering.ModID);
	public static final RegistryObject<TileEntityType<?>> TILE_ENTITY = TILE_ENTITIES.register("tool_forge", () -> TileEntityType.Builder.create(Block.te::new, DeferredBlocks.BLOCK.get()).build(null));
}
