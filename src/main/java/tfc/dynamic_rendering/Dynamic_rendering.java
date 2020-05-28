package tfc.dynamic_rendering;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tfc.dynamic_rendering.TestingRegistry.DeferredItems;
import tfc.dynamic_rendering.TestingRegistry.DeferredTileEntities;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("dynamic_rendering")
public class Dynamic_rendering {
	
	public static String ModID="dynamic_rendering";
	public static final Logger LOGGER = LogManager.getLogger();
	
	
	public Dynamic_rendering() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		bus.addListener(this::setup);
		bus.addListener(this::enqueueIMC);
		bus.addListener(this::processIMC);
		bus.addListener(this::doClientStuff);
		
//		DeferredItems.ITEMS.register(bus);
//		DeferredTileEntities.TILE_ENTITIES.register(bus);
//		DeferredBlocks.BLOCKS.register(bus);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event) {
	}
	
	private void doClientStuff(final FMLClientSetupEvent event) {
		ClientRegistry.bindTileEntityRenderer((TileEntityType<tfc.dynamic_rendering.Block.te>) DeferredTileEntities.TILE_ENTITY.get(), BlockTESR::new);
		Minecraft.getInstance().getItemColors().register(new CubeColors(),DeferredItems.CUBE.get());
		Minecraft.getInstance().getItemColors().register(new LineColors(),DeferredItems.LINE.get());
		Minecraft.getInstance().getItemColors().register(new LineColors(),DeferredItems.FACE.get());
		Minecraft.getInstance().getItemColors().register(new LineColors(),DeferredItems.TEST.get());
	}
	
	private void enqueueIMC(final InterModEnqueueEvent event) {
	}
	
	private void processIMC(final InterModProcessEvent event) {
	}
	
	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event) {
	}
	
	@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents {
		@SubscribeEvent
		public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
		}
	}
}
