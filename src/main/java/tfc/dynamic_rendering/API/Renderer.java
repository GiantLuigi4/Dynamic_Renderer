package tfc.dynamic_rendering.API;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;
import tfc.dynamic_rendering.Color;

import java.util.ArrayList;

public class Renderer {
	public static void render(Model mdl, ResourceLocation base, ResourceLocation mask, ResourceLocation overlay, ResourceLocation atlas, IRenderTypeBuffer buffer, MatrixStack matrixStack, int combinedLightIn,int combinedOverlayIn) {
		TextureAtlasSprite sprite= Minecraft.getInstance().getAtlasSpriteGetter(atlas).apply(base);
		TextureAtlasSprite sprite2=Minecraft.getInstance().getAtlasSpriteGetter(atlas).apply(mask);
		TextureAtlasSprite sprite3=Minecraft.getInstance().getAtlasSpriteGetter(atlas).apply(overlay);
		ArrayList<BakedQuad> transparent=new ArrayList<>();
		ArrayList<BakedQuad> solid=new ArrayList<>();
		ArrayList<BakedQuad> solid2=new ArrayList<>();
		for (Quad qd:mdl.quads) {
			solid.add(createQuad(qd.vec1,qd.vec2,qd.vec3,qd.vec4,sprite2,Direction.NORTH,qd.wrapper));
			transparent.add(createQuad(qd.vec1,qd.vec2,qd.vec3,qd.vec4,sprite,Direction.NORTH,qd.wrapper));
//			transparent.add(createQuad(qd.vec1,qd.vec2,qd.vec3,qd.vec4,sprite,Direction.NORTH,qd.wrapper));
			double zOff=-0.0002;
			for (int x=0;x<sprite3.getWidth();x++) {
				int startY=-999;
				for (int y=0;y<sprite3.getWidth();y++) {
					if (new Color(sprite2.getPixelRGBA(0,x,y),true).getAlpha()==0&&y<sprite3.getHeight()-1) {
						if (startY==-999) {
							startY=y;
						}
					} else {
						if (startY!=-999) {
							float xCoord=(float)x/sprite3.getWidth();
							float yCoordS=(float)startY/sprite3.getHeight();
							float yCoordE=(float)y/sprite3.getHeight();
							solid2.add(createQuad(new Vec3d(
											xCoord,
											yCoordS,
											zOff
									),new Vec3d(
											xCoord,
											yCoordE,
											zOff
									),new Vec3d(
											xCoord+(1f/sprite3.getWidth()),
											yCoordE,
											zOff
									),new Vec3d(
											xCoord+(1f/sprite3.getWidth()),
											yCoordS,
											zOff
									),
									sprite3,
									Direction.NORTH,
									new TextureWrapper(x,startY,1,y-startY)
							));
						}
						startY=-999;
					}
				}
				if (new Color(sprite2.getPixelRGBA(0,x,15),true).getAlpha()==0) {
					float xCoord=(float)x/sprite3.getWidth();
					float yCoordS=(float)15/sprite3.getHeight();
					float yCoordE=(float)16/sprite3.getHeight();
					solid2.add(createQuad(new Vec3d(
									xCoord,
									yCoordS,
									zOff
							),new Vec3d(
									xCoord,
									yCoordE,
									zOff
							),new Vec3d(
									xCoord+(1f/sprite3.getWidth()),
									yCoordE,
									zOff
							),new Vec3d(
									xCoord+(1f/sprite3.getWidth()),
									yCoordS,
									zOff
							),
							sprite3,
							Direction.NORTH,
							new TextureWrapper(x,15,1,1)
					));
				}
			}
		}
		Minecraft.getInstance().getItemRenderer().renderQuads(matrixStack,buffer.getBuffer(RenderType.getSolid()),solid,new ItemStack(Items.DIRT),combinedLightIn,combinedOverlayIn);
		Minecraft.getInstance().getItemRenderer().renderQuads(matrixStack,buffer.getBuffer(RenderType.getTranslucent()),transparent,new ItemStack(Items.DIRT),combinedLightIn,combinedOverlayIn);
		Minecraft.getInstance().getItemRenderer().renderQuads(matrixStack,buffer.getBuffer(RenderType.getSolid()),solid2,new ItemStack(Items.DIRT),combinedLightIn,combinedOverlayIn);
	}
	public static void render(Model mdl, ResourceLocation base, ResourceLocation mask, ResourceLocation overlay, IRenderTypeBuffer buffer, MatrixStack matrixStack, int combinedLightIn,int combinedOverlayIn) {
		render(mdl,base,mask,overlay,AtlasTexture.LOCATION_BLOCKS_TEXTURE,buffer,matrixStack,combinedLightIn,combinedOverlayIn);
	}
	public static void render(ResourceLocation base, ResourceLocation mask, ResourceLocation overlay, IRenderTypeBuffer buffer, MatrixStack matrixStack, int combinedLightIn,int combinedOverlayIn) {
		render(new Model(new Quad[]{
				new Quad(
						new Vec3d(0,0,0),
						new Vec3d(0,1,0),
						new Vec3d(1,1,0),
						new Vec3d(1,0,0),
						new TextureWrapper(0,0,16,16)
				),
				new Quad(
						new Vec3d(0,0,0),
						new Vec3d(0,1,0),
						new Vec3d(1,1,0),
						new Vec3d(1,0,0),
						new TextureWrapper(0,0,16,16)
				)
		}),base,mask,overlay,buffer,matrixStack,combinedLightIn,combinedOverlayIn);
	}
	
	public static void renderTexturedModel(TexturedModel mdl, ResourceLocation base, ResourceLocation mask, ResourceLocation overlay, IRenderTypeBuffer buffer, MatrixStack matrixStack, int combinedLightIn,int combinedOverlayIn) {
		renderTexturedModel(mdl,base,mask,overlay,AtlasTexture.LOCATION_BLOCKS_TEXTURE,buffer,matrixStack,combinedLightIn,combinedOverlayIn);
	}
	public static void renderTexturedModel(TexturedModel mdl, ResourceLocation base, ResourceLocation mask, ResourceLocation overlay, ResourceLocation atlas, IRenderTypeBuffer buffer, MatrixStack matrixStack, int combinedLightIn,int combinedOverlayIn) {
		ArrayList<BakedQuad> transparent=new ArrayList<>();
		ArrayList<BakedQuad> solid=new ArrayList<>();
		ArrayList<BakedQuad> solid2=new ArrayList<>();
		for (TexturedQuad qd:mdl.quads) {
			BakedQuad quad=createQuad(qd.vec1,qd.vec2,qd.vec3,qd.vec4,qd.sprite,Direction.NORTH,qd.wrapper);
			if (qd.isTransparent) {
				transparent.add(quad);
			} else if (qd.isPrimary) {
				solid.add(quad);
			} else {
				solid2.add(quad);
			}
		}
		Minecraft.getInstance().getItemRenderer().renderQuads(matrixStack,buffer.getBuffer(RenderType.getSolid()),solid,new ItemStack(Items.DIRT),combinedLightIn,combinedOverlayIn);
		Minecraft.getInstance().getItemRenderer().renderQuads(matrixStack,buffer.getBuffer(RenderType.getTranslucent()),transparent,new ItemStack(Items.DIRT),combinedLightIn,combinedOverlayIn);
		Minecraft.getInstance().getItemRenderer().renderQuads(matrixStack,buffer.getBuffer(RenderType.getSolid()),solid2,new ItemStack(Items.DIRT),combinedLightIn,combinedOverlayIn);
	}
	
	
	//MCJTY:https://github.com/McJty/YouTubeModding14/blob/master/src/main/java/com/mcjty/mytutorial/blocks/FancyBakedModel.java
	public static BakedQuad createQuad(
		Vec3d v1, Vec3d v2, Vec3d v3, Vec3d v4,
		TextureAtlasSprite sprite, Direction dir, TextureWrapper r
	) {
		Vec3d normal = v3.subtract(v2).crossProduct(v1.subtract(v2)).normalize();
		
		BakedQuadBuilder builder = new BakedQuadBuilder(sprite);
		builder.setQuadOrientation(dir);
		putVertex(builder, normal, v1.x, v1.y, v1.z, r.tx, r.ty, sprite, 1.0f, 1.0f, 1.0f);
		putVertex(builder, normal, v2.x, v2.y, v2.z, r.tx, r.ty+r.th, sprite, 1.0f, 1.0f, 1.0f);
		putVertex(builder, normal, v3.x, v3.y, v3.z, r.tx+r.tw, r.ty+r.th, sprite, 1.0f, 1.0f, 1.0f);
		putVertex(builder, normal, v4.x, v4.y, v4.z, r.tx+r.tw, r.ty, sprite, 1.0f, 1.0f, 1.0f);
		return builder.build();
	}
	//MCJTY:https://github.com/McJty/YouTubeModding14/blob/master/src/main/java/com/mcjty/mytutorial/blocks/FancyBakedModel.java
	private static void putVertex(BakedQuadBuilder builder, Vec3d normal,
						   double x, double y, double z, double u, double v, TextureAtlasSprite sprite, double r, double g, double b) {
		
		ImmutableList<VertexFormatElement> elements = builder.getVertexFormat().getElements().asList();
		for (int j = 0 ; j < elements.size() ; j++) {
			VertexFormatElement e = elements.get(j);
			switch (e.getUsage()) {
				case POSITION:
					builder.put(j, (float) x, (float) y, (float) z, 1.0f);
					break;
				case COLOR:
					builder.put(j, (float) r, (float) g, (float) b, 1.0f);
					break;
				case UV:
					switch (e.getIndex()) {
						case 0:
							float iu = sprite.getInterpolatedU(u);
							float iv = sprite.getInterpolatedV(v);
							builder.put(j, iu, iv);
							break;
						case 2:
							builder.put(j, 0f, 1f);
							break;
						default:
							builder.put(j);
							break;
					}
					break;
				case NORMAL:
					builder.put(j, (float) normal.x, (float) normal.y, (float) normal.z);
					break;
				default:
					builder.put(j);
					break;
			}
		}
	}
}
