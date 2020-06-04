package tfc.dynamic_rendering;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Quaternion;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import tfc.dynamic_rendering.API.ExtrudedTexture;
import tfc.dynamic_rendering.API.PreppedModel;
import tfc.dynamic_rendering.API.Renderer;

public class StackRenderer extends ItemStackTileEntityRenderer {
	private static boolean up=true;
	private static boolean dn=true;
	private static boolean no=true;
	private static boolean ea=true;
	private static boolean so=true;
	private static boolean we=true;
	
	@Override
	public void render(ItemStack p_228364_1_, MatrixStack p_228364_2_, IRenderTypeBuffer p_228364_3_, int p_228364_4_, int p_228364_5_) {
//		TextureAtlasSprite tx=Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(
////				new ResourceLocation("etset:blocks/pillar_overlay")
//				new ResourceLocation("etset:blocks/pillar_thing_decor")
////				new ResourceLocation("etset:blocks/test_overlay")
////				new ResourceLocation("block/glass")
//		);
//		TextureAtlasSprite tx1=Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(
////				new ResourceLocation("etset:blocks/pillar_base")
//				new ResourceLocation("etset:blocks/pillar_thing_base")
////				new ResourceLocation("etset:blocks/test")
////				new ResourceLocation("block/white_concrete")
//		);
//
//		TextureAtlasSprite tx2=Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(
////				new ResourceLocation("etset:blocks/corel_stone")
//				new ResourceLocation("block/gold_block")
//		);
//		TextureAtlasSprite tx3=Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(
////				new ResourceLocation("etset:blocks/sky_stone")
//				new ResourceLocation("block/diamond_block")
//		);
//		ItemStack renderStack=new ItemStack(DeferredItems.CUBE.get());
//		for (int x=0;x<=15;x++) {
//			for (int y=0;y<=15;y++) {
//				p_228364_2_.push();
//				if (x==13) {
//					Etset.map.setHeight(x,y,12);
//				} else if (x==2) {
//					Etset.map.setHeight(x,y,12);
//				} else if (x>=4&&x<=11) {
//					Etset.map.setHeight(x,y,14);
//				} else if (x<=2) {
//					Etset.map.setHeight(x,y,-999);
//				} else if (x>=13) {
//					Etset.map.setHeight(x,y,-999);
//				} else {
//					Etset.map.setHeight(x,y,13);
//				}
//				try {
//					if (Etset.map.getHeight(x,y)!=-999) {
//						Color col1=new Color(tx.getPixelRGBA(0,x,y),true);
//						Color col2=new Color(tx1.getPixelRGBA(0,x,y),true);
//						Color col3=new Color(tx2.getPixelRGBA(0,x,y),true);
//						Color col4=new Color(tx3.getPixelRGBA(0,x,y),true);
//						if (col1.getAlpha()!=0) {
//							CubeColors.tint=new Color(
//									(int)((col1.getBlue()/255f)*col3.getBlue()),
//									(int)((col1.getGreen()/255f)*col3.getGreen()),
//									(int)((col1.getRed()/255f)*col3.getRed()),
//									col3.getAlpha()
//							).getRGB();
//							if (col3.getAlpha()!=0) {
//								drawOne(renderStack,p_228364_2_,p_228364_3_,p_228364_4_,p_228364_5_,x,y,ea,so,we,no,Etset.map.getHeight(x,y));
//							}
//						} else if (col2.getAlpha()!=0) {
//							CubeColors.tint=new Color(
//									(int)((col2.getBlue()/255f)*col4.getBlue()),
//									(int)((col2.getGreen()/255f)*col4.getGreen()),
//									(int)((col2.getRed()/255f)*col4.getRed()),
//									col3.getAlpha()
//							).getRGB();
//							if (col4.getAlpha()!=0) {
//								drawOne(renderStack,p_228364_2_,p_228364_3_,p_228364_4_,p_228364_5_,x,y,ea,so,we,no,Etset.map.getHeight(x,y));
//							}
//						}
//					}
//				} catch (Exception err) {}
//				p_228364_2_.pop();
////				Minecraft.getInstance().getItemRenderer().renderItem(new ItemStack(DeferredItems.LINE.get()), ItemCameraTransforms.TransformType.GUI,p_228364_4_,p_228364_5_,p_228364_2_,p_228364_3_);
//			}
////			LineColors.colors.clear();
//		}
		
//		mdl=null;
		if (mdl==null) {
//			ResourceLocation loc1=new ResourceLocation("dynamic_rendering:item/metal_pickaxe_leather");
			ResourceLocation loc1=new ResourceLocation("dynamic_rendering:blocks/pillar_base");
////			ResourceLocation loc1=new ResourceLocation("dynamic_rendering:item/test_resource_crystal");
////			ResourceLocation loc1=new ResourceLocation("dynamic_rendering:item/metal_van_pick_metal");
//			ResourceLocation loc2=new ResourceLocation("minecraft:item/leather");
			ResourceLocation loc2=new ResourceLocation("minecraft:block/iron_block");
////			ResourceLocation loc2=new ResourceLocation("minecraft:item/gold_ingot");
////			ResourceLocation loc2=new ResourceLocation("dynamic_rendering:item/iron");
//			ResourceLocation loc3=new ResourceLocation("dynamic_rendering:item/metal_pickaxe_metal");
			ResourceLocation loc3=new ResourceLocation("dynamic_rendering:blocks/pillar_overlay");
////			ResourceLocation loc3=new ResourceLocation("dynamic_rendering:item/metal_van_pick_wood");
//			ResourceLocation loc4=new ResourceLocation("minecraft:block/gold_block");
			ResourceLocation loc4=new ResourceLocation("minecraft:block/gold_block");
////			ResourceLocation loc4=new ResourceLocation("dynamic_rendering:item/oak");
			ResourceLocation loc5=new ResourceLocation("dynamic_rendering:item/metal_pickaxe_metal_o");
			ResourceLocation loc6=new ResourceLocation("minecraft:block/diamond_block");
			ResourceLocation loc7=new ResourceLocation("dynamic_rendering:item/metal_pickaxe_wood");
			ResourceLocation loc8=new ResourceLocation("minecraft:block/oak_planks");
			mdl=Renderer.prepExtrudedTextureNoTexCorrection(true
//					,new ExtrudedTexture(loc1,loc2,1,false)
					,new ExtrudedTexture(loc3,loc4,1,false)
//					,new ExtrudedTexture(loc5,loc6,1,false)
//					,new ExtrudedTexture(loc7,loc8,-1,true)
			);
			mdl=PreppedModel.merge(mdl,Renderer.createFlatPreppedModel(loc1,false));
			mdl=PreppedModel.merge(mdl,Renderer.createFlatPreppedModel(loc2,true));
		}
		p_228364_2_.push();
		
		p_228364_2_.scale(0.1f,0.1f,0.1f);
		
//		p_228364_2_.rotate(new Quaternion(180,-45,-50,true));
//		p_228364_2_.translate(0,-8,-16);
		
		p_228364_2_.rotate(new Quaternion(0,0,90,true));
		p_228364_2_.translate(0,-16,7);
		p_228364_2_.rotate(new Quaternion(0,0,90,true));
		p_228364_2_.translate(2,-13,0);

//		p_228364_2_.rotate(new Quaternion(180,-45,0,true));
//		p_228364_2_.translate(0,-16,-16);

		p_228364_2_.scale(1,1,0.01f);
//		Renderer.renderPreparedModel(mdl,p_228364_3_,p_228364_2_,p_228364_4_,p_228364_5_,11034929,16238893,16777215,9664325);
		Renderer.renderPreparedModel(mdl,p_228364_3_,p_228364_2_,p_228364_4_,p_228364_5_);
		super.render(p_228364_1_, p_228364_2_, p_228364_3_, p_228364_4_, p_228364_5_);
		p_228364_2_.pop();
	}
	
	PreppedModel mdl=null;
	
	public static void prepCulling(boolean north,boolean east,boolean south,boolean west,boolean up1,boolean down) {
		up=up1;
		dn=down;
		ea=east;
		so=south;
		no=north;
		we=west;
	}
	
	public static void drawOne(ItemStack p_228364_1_, MatrixStack p_228364_2_, IRenderTypeBuffer p_228364_3_, int p_228364_4_, int p_228364_5_,int x,int y,boolean _1,boolean _2,boolean _3,boolean _4, int z) {
		p_228364_2_.rotate(new Quaternion(90,0,0,true));
		p_228364_2_.translate(0,0,-15f/16);
		if (_1||z<16) {
			drawPixel(p_228364_2_,x,y,z,p_228364_4_,p_228364_5_,p_228364_3_,p_228364_1_);
		}
		if (_2||z<16) {
			p_228364_2_.push();
			p_228364_2_.rotate(new Quaternion(0,0,90,true));
			p_228364_2_.translate(0,-15f/16,0);
			drawPixel(p_228364_2_,x,y,z,p_228364_4_,p_228364_5_,p_228364_3_,p_228364_1_);
			p_228364_2_.pop();
		}
		if (_3||z<16) {
			p_228364_2_.push();
			p_228364_2_.rotate(new Quaternion(0,0,180,true));
			p_228364_2_.translate(-15f/16,-15f/16,0);
			drawPixel(p_228364_2_,x,y,z,p_228364_4_,p_228364_5_,p_228364_3_,p_228364_1_);
			p_228364_2_.pop();
		}
		if (_4||z<16) {
			p_228364_2_.push();
			p_228364_2_.rotate(new Quaternion(0,0,-90,true));
			p_228364_2_.translate(-15f/16,0,0);
			drawPixel(p_228364_2_,x,y,z,p_228364_4_,p_228364_5_,p_228364_3_,p_228364_1_);
			p_228364_2_.pop();
		}
	}
	
	public static void drawPixel(MatrixStack p_228364_2_,int x,int y,int z,int p_228364_4_,int p_228364_5_,IRenderTypeBuffer p_228364_3_, ItemStack p_228364_1_) {
		p_228364_2_.push();
		p_228364_2_.translate(0,x*0.0625,y*0.0625);
		p_228364_2_.translate(1-z*0.0625,0,0);
		p_228364_2_.rotate(new Quaternion(-90,90,0,true));
		Minecraft.getInstance().getItemRenderer().renderItem(p_228364_1_, ItemCameraTransforms.TransformType.GUI,p_228364_4_,p_228364_5_,p_228364_2_,p_228364_3_);
		p_228364_2_.pop();
	}
}
