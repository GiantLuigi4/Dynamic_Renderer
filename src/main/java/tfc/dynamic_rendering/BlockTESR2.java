package tfc.dynamic_rendering;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import tfc.dynamic_rendering.TestingRegistry.DeferredItems;

public class BlockTESR2 extends TileEntityRenderer<Block.te> {
	public BlockTESR2(TileEntityRendererDispatcher rendererDispatcherIn) {
		super(rendererDispatcherIn);
	}
	
	@Override
	public void render(Block.te tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
//		Minecraft.getInstance().getTextureManager().bindTexture(AtlasTexture.LOCATION_BLOCKS_TEXTURE);
//		TextureAtlasSprite sprite=Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(new ResourceLocation("dynamic_rendering:blocks/corel_stone"));
//		TextureAtlasSprite sprite2=Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(new ResourceLocation("dynamic_rendering:blocks/pillar_overlay"));
//		TextureAtlasSprite sprite3=Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(new ResourceLocation("dynamic_rendering:blocks/sky_stone"));
//		ArrayList<BakedQuad> transparent=new ArrayList<>();
//		ArrayList<BakedQuad> solid=new ArrayList<>();
//		ArrayList<BakedQuad> solid2=new ArrayList<>();
//		solid.add(Renderer.createQuad(new Vec3d(
//						0,0,0
//				),new Vec3d(
//						0,1,0
//				),new Vec3d(
//						1,1,0
//				),new Vec3d(
//						1,0,0
//				),
//				sprite2,
//				Direction.NORTH,
//				new TextureWrapper(0,0,16,16)
//		));
//		transparent.add(Renderer.createQuad(new Vec3d(
//						0,0,0
//				),new Vec3d(
//						0,1,0
//				),new Vec3d(
//						1,1,0
//				),new Vec3d(
//						1,0,0
//				),
//				sprite,
//				Direction.NORTH,
//				new TextureWrapper(0,0,16,16)
//		));
//		transparent.add(Renderer.createQuad(new Vec3d(
//						0,0,0
//				),new Vec3d(
//						0,1,0
//				),new Vec3d(
//						1,1,0
//				),new Vec3d(
//						1,0,0
//				),
//				sprite,
//				Direction.NORTH,
//				new TextureWrapper(0,0,16,16)
//		));
//		double zOff=-0.0002;
//		for (int x=0;x<sprite3.getWidth();x++) {
//			int startY=-999;
//			for (int y=0;y<sprite3.getWidth();y++) {
//				if (new Color(sprite2.getPixelRGBA(0,x,y),true).getAlpha()==0&&y<sprite3.getHeight()-1) {
//					if (startY==-999) {
//						startY=y;
//					}
//				} else {
//					if (startY!=-999) {
//						float xCoord=(float)x/sprite3.getWidth();
//						float yCoordS=(float)startY/sprite3.getHeight();
//						float yCoordE=(float)y/sprite3.getHeight();
//						solid2.add(Renderer.createQuad(new Vec3d(
//										xCoord,yCoordS,zOff
//								),new Vec3d(
//										xCoord,yCoordE,zOff
//								),new Vec3d(
//										xCoord+(1f/sprite3.getWidth()),yCoordE,zOff
//								),new Vec3d(
//										xCoord+(1f/sprite3.getWidth()),yCoordS,zOff
//								),
//								sprite3,
//								Direction.NORTH,
//								new TextureWrapper(x,startY,x+1,y)
//						));
//					}
//					startY=-999;
//				}
//			}
//			if (new Color(sprite2.getPixelRGBA(0,x,15),true).getAlpha()==0) {
//				float xCoord=(float)x/sprite3.getWidth();
//				float yCoordS=(float)15/sprite3.getHeight();
//				float yCoordE=(float)16/sprite3.getHeight();
//				solid2.add(Renderer.createQuad(new Vec3d(
//								xCoord,yCoordS,zOff
//						),new Vec3d(
//								xCoord,yCoordE,zOff
//						),new Vec3d(
//								xCoord+(1f/sprite3.getWidth()),yCoordE,zOff
//						),new Vec3d(
//								xCoord+(1f/sprite3.getWidth()),yCoordS,zOff
//						),
//						sprite3,
//						Direction.NORTH,
//						new TextureWrapper(x,15,x+1,16)
//				));
//			}
//		}
//		Minecraft.getInstance().getItemRenderer().renderQuads(matrixStackIn,bufferIn.getBuffer(RenderType.getSolid()),solid,new ItemStack(Items.DIRT),combinedLightIn,combinedOverlayIn);
//		Minecraft.getInstance().getItemRenderer().renderQuads(matrixStackIn,bufferIn.getBuffer(RenderType.getTranslucent()),transparent,new ItemStack(Items.DIRT),combinedLightIn,combinedOverlayIn);
//		Minecraft.getInstance().getItemRenderer().renderQuads(matrixStackIn,bufferIn.getBuffer(RenderType.getSolid()),solid2,new ItemStack(Items.DIRT),combinedLightIn,combinedOverlayIn);

//		ResourceLocation loc1=new ResourceLocation("dynamic_rendering:blocks/corel_stone");
//		ResourceLocation loc2=new ResourceLocation("dynamic_rendering:blocks/pillar_overlay");
//		ResourceLocation loc3=new ResourceLocation("dynamic_rendering:blocks/sky_stone");
//		Renderer.render(loc1,loc2,loc3,bufferIn,matrixStackIn,combinedLightIn,combinedOverlayIn);
		
		Minecraft.getInstance().getItemRenderer().renderItem(new ItemStack(DeferredItems.STACK_RENDERER_TEST.get()), ItemCameraTransforms.TransformType.NONE,combinedLightIn,combinedOverlayIn,matrixStackIn,bufferIn);
	}
}
