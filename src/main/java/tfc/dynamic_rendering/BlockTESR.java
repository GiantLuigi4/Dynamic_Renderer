package tfc.dynamic_rendering;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

public class BlockTESR extends TileEntityRenderer<Block.te> {
	public BlockTESR(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}
	
	public static boolean solid(TileEntity te, BlockPos pos) {
		return te.getWorld().getBlockState(pos).isSolid() || te.getWorld().getBlockState(pos).getShape(te.getWorld(), pos).equals(net.minecraft.block.Block.makeCuboidShape(0, 0, 0, 16, 16, 16));
	}
	
	@Override
	public void render(Block.te tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
		World world=tileEntityIn.getWorld();
		BlockPos pos=tileEntityIn.getPos();
		Direction dir=tileEntityIn.getBlockState().get(Block.FACING);
		boolean up=true;
		boolean down=true;
		boolean north=true;
		boolean south=true;
		boolean east=true;
		boolean west=true;
		if (solid(tileEntityIn,pos.offset(dir))) {
			up=false;
		}
		if (solid(tileEntityIn,pos.offset(dir.getOpposite()))) {
			down=false;
		}
		if (dir.equals(Direction.UP)) {
			if (Minecraft.getInstance().player!=null) {
				if (Minecraft.getInstance().player.getEyePosition(partialTicks).getY()<tileEntityIn.getPos().getY()+1) {
					up=false;
				}
				if (Minecraft.getInstance().player.getEyePosition(partialTicks).getY()>tileEntityIn.getPos().getY()) {
					down=false;
				}
			}
		}
		if (dir.equals(Direction.UP)||dir.equals(Direction.DOWN)) {
			if (solid(tileEntityIn,pos.north())) {
				north=false;
			}
			if (solid(tileEntityIn,pos.south())) {
				south=false;
			}
			if (solid(tileEntityIn,pos.east())) {
				east=false;
			}
			if (solid(tileEntityIn,pos.west())) {
				west=false;
			}
			if (dir.equals(Direction.UP)) {
				boolean e1=east;
				boolean w1=west;
				east=w1;
				west=e1;
			}
		} else {
			if (solid(tileEntityIn,pos.down())) {
				south=false;
			}
			if (solid(tileEntityIn,pos.up())) {
				north=false;
			}
			if (dir.equals(Direction.NORTH)||dir.equals(Direction.SOUTH)) {
				if (solid(tileEntityIn,dir.equals(Direction.SOUTH)?pos.west():pos.east())) {
					east=false;
				}
				if (solid(tileEntityIn,dir.equals(Direction.SOUTH)?pos.east():pos.west())) {
					west=false;
				}
			} else {
				if (solid(tileEntityIn,dir.equals(Direction.WEST)?pos.north():pos.south())) {
					east=false;
				}
				if (solid(tileEntityIn,dir.equals(Direction.WEST)?pos.south():pos.north())) {
					west=false;
				}
			}
		}
		if (north||south||west||east||up||down) {
			if (dir.equals(Direction.NORTH)) {
				matrixStackIn.rotate(new Quaternion(90,180,0,true));
				matrixStackIn.translate(0,0,1);
			} else if (dir.equals(Direction.SOUTH)) {
				matrixStackIn.rotate(new Quaternion(-90,0,0,true));
				matrixStackIn.translate(1,-1,1);
			} else if (dir.equals(Direction.WEST)) {
				matrixStackIn.rotate(new Quaternion(-90,0,-90,true));
				matrixStackIn.translate(1,0,1);
			} else if (dir.equals(Direction.EAST)) {
				matrixStackIn.rotate(new Quaternion(-90,0,90,true));
				matrixStackIn.translate(0,-1,1);
			} else if (dir.equals(Direction.DOWN)) {
				matrixStackIn.rotate(new Quaternion(180,0,180,true));
//				matrixStackIn.translate(-16f/16,0,-16f/16);
			} else {
				matrixStackIn.rotate(new Quaternion(180,0,0,true));
				matrixStackIn.translate(1,-1,0);
			}
			try {
				matrixStackIn.translate(-1,0,-1);
				if (south) {
					new BlockTESR2(renderDispatcher).render(tileEntityIn,partialTicks,matrixStackIn,bufferIn,combinedLightIn,combinedOverlayIn);
				}
				matrixStackIn.rotate(new Quaternion(0,90,0,true));
				matrixStackIn.translate(-1,0,0);
				if (east) {
					new BlockTESR2(renderDispatcher).render(tileEntityIn,partialTicks,matrixStackIn,bufferIn,combinedLightIn,combinedOverlayIn);
				}
				matrixStackIn.rotate(new Quaternion(0,90,0,true));
				matrixStackIn.translate(-1,0,0);
				if (north) {
					new BlockTESR2(renderDispatcher).render(tileEntityIn,partialTicks,matrixStackIn,bufferIn,combinedLightIn,combinedOverlayIn);
				}
				matrixStackIn.rotate(new Quaternion(0,90,0,true));
				matrixStackIn.translate(-1,0,0);
				if (west) {
					new BlockTESR2(renderDispatcher).render(tileEntityIn,partialTicks,matrixStackIn,bufferIn,combinedLightIn,combinedOverlayIn);
				}
			} catch (Exception err) { //stupid optifine, lol
				StringBuilder message= new StringBuilder("\nSomething unexpected happened.\nDo you have optifine?\n");
				message.append(err.getLocalizedMessage());
				for (StackTraceElement element:err.getStackTrace()) {
					message.append(element.toString());
				}
				Dynamic_rendering.LOGGER.log(Level.INFO,message.toString());
			}
		}
	}
}
