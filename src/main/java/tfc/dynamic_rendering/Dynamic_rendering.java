package tfc.dynamic_rendering;

import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tfc.dynamic_rendering.TestingRegistry.DeferredItems;
import tfc.dynamic_rendering.TestingRegistry.DeferredTileEntities;
import tfc.dynamic_rendering.TestingRegistry.TintHelper;

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
		try {
			if (Minecraft.getInstance()!=null) {
				bus.addListener(this::doClientStuff);
			}
		} catch (Exception ignored) {}
		
//		DeferredItems.ITEMS.register(bus);
//		DeferredTileEntities.TILE_ENTITIES.register(bus);
//		DeferredBlocks.BLOCKS.register(bus);
		
		TintHelper.ITEMS.register(bus);
		
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	private void setup(final FMLCommonSetupEvent event) {
	}
	
	@OnlyIn(Dist.CLIENT)
	private void doClientStuff(final FMLClientSetupEvent event) {
		try {
			ClientRegistry.bindTileEntityRenderer((TileEntityType<tfc.dynamic_rendering.Block.te>) DeferredTileEntities.TILE_ENTITY.get(), BlockTESR::new);
			Minecraft.getInstance().getItemColors().register(new CubeColors(),DeferredItems.CUBE.get());
			Minecraft.getInstance().getItemColors().register(new LineColors(),DeferredItems.LINE.get());
			Minecraft.getInstance().getItemColors().register(new LineColors(),DeferredItems.FACE.get());
			Minecraft.getInstance().getItemColors().register(new LineColors(),DeferredItems.TEST.get());
		} catch (Exception err) {}
		Minecraft.getInstance().getItemColors().register(new LineColors(), TintHelper.CUBE.get());
	}
	
	private void enqueueIMC(final InterModEnqueueEvent event) {
	}
	
	private void processIMC(final InterModProcessEvent event) {
	}
}
