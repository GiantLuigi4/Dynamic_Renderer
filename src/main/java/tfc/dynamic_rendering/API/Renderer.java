package tfc.dynamic_rendering.API;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
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
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;
import tfc.dynamic_rendering.Color;
import tfc.dynamic_rendering.LineColors;
import tfc.dynamic_rendering.TestingRegistry.TintHelper;

import java.util.ArrayList;

public class Renderer {
	public static void render(Model mdl, ResourceLocation base, ResourceLocation mask, ResourceLocation overlay, ResourceLocation atlas, IRenderTypeBuffer buffer, MatrixStack matrixStack, int combinedLightIn,int combinedOverlayIn) {
		TextureAtlasSprite sprite= Minecraft.getInstance().getAtlasSpriteGetter(atlas).apply(base);
		TextureAtlasSprite sprite2=Minecraft.getInstance().getAtlasSpriteGetter(atlas).apply(mask);
		TextureAtlasSprite sprite3=Minecraft.getInstance().getAtlasSpriteGetter(atlas).apply(overlay);
		solid.clear();
		solid2.clear();
		transparent.clear();
		for (Quad qd:mdl.quads) {
			solid.add(createQuad(qd.vec1,qd.vec2,qd.vec3,qd.vec4,sprite2,Direction.NORTH,qd.wrapper));
			transparent.add(createQuad(qd.vec1,qd.vec2,qd.vec3,qd.vec4,sprite,Direction.NORTH,qd.wrapper,-1,0.5f));
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
		RenderSystem.enableBlend();
		RenderSystem.enableAlphaTest();
		Minecraft.getInstance().getItemRenderer().renderQuads(matrixStack,buffer.getBuffer(RenderType.getTranslucent()),transparent,new ItemStack(Items.DIRT),combinedLightIn,combinedOverlayIn);
		RenderSystem.disableAlphaTest();
		RenderSystem.disableBlend();
		Minecraft.getInstance().getItemRenderer().renderQuads(matrixStack,buffer.getBuffer(RenderType.getSolid()),solid2,new ItemStack(Items.DIRT),combinedLightIn,combinedOverlayIn);
	}
	public static void render(ResourceLocation base, ResourceLocation mask, ResourceLocation overlay, ResourceLocation mask2, ResourceLocation atlas, IRenderTypeBuffer buffer, MatrixStack matrixStack, int combinedLightIn,int combinedOverlayIn) {
		solid.clear();
		solid2.clear();
		transparent.clear();
		TextureAtlasSprite sprite= Minecraft.getInstance().getAtlasSpriteGetter(atlas).apply(base);
		TextureAtlasSprite sprite2=Minecraft.getInstance().getAtlasSpriteGetter(atlas).apply(mask);
		TextureAtlasSprite sprite3=Minecraft.getInstance().getAtlasSpriteGetter(atlas).apply(overlay);
		TextureAtlasSprite sprite4=Minecraft.getInstance().getAtlasSpriteGetter(atlas).apply(mask2);
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
		
		for (int x=0;x<sprite.getWidth();x++) {
			int startY=-999;
			for (int y=0;y<sprite.getWidth();y++) {
				if (new Color(sprite4.getPixelRGBA(0,x,y),true).getAlpha()==0&&y<sprite.getHeight()-1) {
					if (startY==-999) {
						startY=y;
					}
				} else {
					if (startY!=-999) {
						float xCoord=(float)x/sprite.getWidth();
						float yCoordS=(float)startY/sprite.getHeight();
						float yCoordE=(float)y/sprite.getHeight();
						solid.add(createQuad(new Vec3d(
										xCoord,
										yCoordS,
										zOff
								),new Vec3d(
										xCoord,
										yCoordE,
										zOff
								),new Vec3d(
										xCoord+(1f/sprite.getWidth()),
										yCoordE,
										zOff
								),new Vec3d(
										xCoord+(1f/sprite.getWidth()),
										yCoordS,
										zOff
								),
								sprite,
								Direction.NORTH,
								new TextureWrapper(x,startY,1,y-startY)
						));
					}
					startY=-999;
				}
			}
			if (new Color(sprite4.getPixelRGBA(0,x,15),true).getAlpha()==0) {
				float xCoord=(float)x/sprite.getWidth();
				float yCoordS=(float)15/sprite.getHeight();
				float yCoordE=(float)16/sprite.getHeight();
				solid.add(createQuad(new Vec3d(
								xCoord,
								yCoordS,
								zOff
						),new Vec3d(
								xCoord,
								yCoordE,
								zOff
						),new Vec3d(
								xCoord+(1f/sprite.getWidth()),
								yCoordE,
								zOff
						),new Vec3d(
								xCoord+(1f/sprite.getWidth()),
								yCoordS,
								zOff
						),
						sprite,
						Direction.NORTH,
						new TextureWrapper(x,15,1,1)
				));
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
	public static void render(ResourceLocation base, ResourceLocation mask, ResourceLocation overlay,ResourceLocation mask2, IRenderTypeBuffer buffer, MatrixStack matrixStack, int combinedLightIn,int combinedOverlayIn) {
		render(base,mask,overlay,mask2,AtlasTexture.LOCATION_BLOCKS_TEXTURE,buffer,matrixStack,combinedLightIn,combinedOverlayIn);
	}
	
	public static void renderTexturedModel(TexturedModel mdl, IRenderTypeBuffer buffer, MatrixStack matrixStack, int combinedLightIn,int combinedOverlayIn) {
		renderTexturedModel(mdl,buffer,matrixStack,combinedLightIn,combinedOverlayIn,false);
	}
	private static ArrayList<BakedQuad> transparent=new ArrayList<>();
	private static ArrayList<BakedQuad> solid=new ArrayList<>();
	private static ArrayList<BakedQuad> solid2=new ArrayList<>();
	public static void renderTexturedModel(TexturedModel mdl, IRenderTypeBuffer buffer, MatrixStack matrixStack, int combinedLightIn,int combinedOverlayIn,boolean inverted) {
		solid.clear();
		solid2.clear();
		transparent.clear();
		for (TexturedQuad qd:mdl.quads) {
			BakedQuad quad=createQuad(inverted?qd.vec4:qd.vec1,inverted?qd.vec3:qd.vec2,inverted?qd.vec2:qd.vec3,inverted?qd.vec1:qd.vec4,qd.sprite,Direction.NORTH,qd.wrapper,-1,0.5f);
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
	public static PreppedModel prepLastRendered() {
		return new PreppedModel(solid,solid2,transparent);
	}
	
	public static PreppedModel prepExtrudedTexture(ExtrudedTexture... textures) {
		return prepExtrudedTexture(false,textures);
	}
	public static PreppedModel prepExtrudedTexture(boolean oneSided,ExtrudedTexture... textures) {
		ArrayList<BakedQuad> transparent=new ArrayList<>();
		ArrayList<BakedQuad> solid=new ArrayList<>();
		int offset=0;
		int layer=0;
		for (ExtrudedTexture tx:textures) {
			offset+=tx.width;
			TextureAtlasSprite sprite1=Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(tx.base);
			TextureAtlasSprite sprite2=Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(tx.mask);
			int[] startY1=new int[sprite1.getHeight()];
			int[] startY2=new int[sprite1.getHeight()];
			int[] endY1=new int[sprite1.getHeight()];
			int[] endY2=new int[sprite1.getHeight()];
			for (int i=0;i<startY1.length;i++) {
				startY1[i]=-999;
				startY2[i]=-999;
				endY1[i]=-999;
				endY2[i]=-999;
			}
			for (int y=0;y<sprite1.getHeight();y++) {
				for (int x=0;x<sprite1.getHeight();x++) {
					if (new Color(sprite1.getPixelRGBA(0,x,y),true).getAlpha()!=0) {
						if (startY1[x]==-999) {
							startY1[x]=y;
						}
						endY1[x]=y;
					}
					if (new Color(sprite2.getPixelRGBA(0,x,y),true).getAlpha()!=0) {
						if (startY2[x]==-999) {
							startY2[x]=y;
						}
						endY2[x]=y;
					}
				}
			}
			for (int y=0;y<sprite1.getWidth();y++) {
				int rowStart1=-999;
				int rowEnd1=-999;
				int rowStart2=-999;
				int rowEnd2=-999;
				for (int x=0;x<sprite1.getWidth();x++) {
					if (new Color(sprite1.getPixelRGBA(0,x,y),true).getAlpha()!=0) {
						if (rowStart2==-999) {
							rowStart2=x;
						}
						rowEnd2=x;
					}
					if (new Color(sprite2.getPixelRGBA(0,x,y),true).getAlpha()!=0) {
						if (rowStart1==-999) {
							rowStart1=x;
						}
						rowEnd1=x;
					}
				}
				for (int x=rowStart2;x<rowEnd2+1;x++) {
					try {
						if (new Color(sprite1.getPixelRGBA(0,x,y),true).getAlpha()!=0) {
							float pct=Math.abs(((float)(x-rowStart1)/(float)(rowEnd1)));
							int px=(int)MathHelper.lerp(pct,rowStart1,rowEnd1);
							float pct2=0;
							int py=y;
							try {
								if (startY2[px]==-999||endY2[px]==-999||endY1[px]==-999||startY1[px]==-999) {
									pct2=Math.abs(((float)(y)/(float)(endY2[x])));
								} else {
									pct2=Math.abs(((float)(y)/(float)(endY2[px])));
								}
								py=(int)MathHelper.lerp(pct2,startY1[px],endY1[px]);
								if (pct2>1||pct2<0) {
									py=(int)MathHelper.lerp(pct,startY1[px],endY1[px]);
								}
								if (px<=-1||px>=15) {
									px = x;
								}
								if (py>=15||py<0) {
									py=(int)MathHelper.lerp(pct,startY1[px],endY1[px]);
								}
								if (new Color(sprite2.getPixelRGBA(0,px,py),true).getAlpha()!=0) {
									py+=2;
								}
								if (px<=-1||px>=15) {
									px = (int)MathHelper.lerp(pct,rowStart1,rowEnd1);
								}
								if (py>=15||py<0) {
									py=(int)MathHelper.lerp(pct,startY1[px],endY1[px]);
								}
								if (new Color(sprite2.getPixelRGBA(0,px,py),true).getAlpha()!=0) {
									py=(int)MathHelper.lerp(pct,startY1[px],endY1[px]);
								}
								if (px<=-1||px>=15) {
									px = (int)MathHelper.lerp(pct,rowStart1,rowEnd1);
								}
								if (py>=15||py<0) {
									py=(int)MathHelper.lerp(pct,startY1[px],endY1[px]);
								}
								if (new Color(sprite1.getPixelRGBA(0,px,py),true).getAlpha()==0) {
									py=(int)MathHelper.lerp(pct,startY1[px],endY1[px]);
									py+=px;
									py+=pct*2;
								}
								if (px<=-1||px>=15) {
									px = (int)MathHelper.lerp(pct,rowStart1,rowEnd1);
								}
								if (py>=15||py<0) {
									py=(int)MathHelper.lerp(pct,startY1[px],endY1[px]);
								}
								if (new Color(sprite1.getPixelRGBA(0,px,py),true).getAlpha()==0) {
									py=y;
								}
								if (new Color(sprite2.getPixelRGBA(0,px,py),true).getAlpha()!=0) {
									py=(int)(((float)py+(float)py+(float)py+(float)y)/4);
								}
							} catch (Exception err) {
								px=(int)MathHelper.lerp(pct,rowStart1,rowEnd1);
								py=y;
							}
							BakedQuad quad=createQuad(new Vec3d(x,y,offset),new Vec3d(x+1,y,offset),new Vec3d(x+1,y+1,offset),new Vec3d(x,y+1,offset),sprite2,Direction.NORTH,new TextureWrapper(px,py,1,1),-1);
							BakedQuad quad2=createQuad(new Vec3d(x+1,y,-offset),new Vec3d(x,y,-offset),new Vec3d(x,y+1,-offset),new Vec3d(x+1,y+1,-offset),sprite2,Direction.NORTH,new TextureWrapper(px,py,1,1),-1);
							BakedQuad quad3=createQuad(new Vec3d(x+1,y+1,offset),new Vec3d(x+1,y,offset),new Vec3d(x+1,y,-offset),new Vec3d(x+1,y+1,-offset),sprite2,Direction.NORTH,new TextureWrapper(px,py,1,1),-1);
							BakedQuad quad4=createQuad(new Vec3d(x,y,offset),new Vec3d(x,y+1,offset),new Vec3d(x,y+1,-offset),new Vec3d(x,y,-offset),sprite2,Direction.NORTH,new TextureWrapper(px,py,1,1),-1);
							BakedQuad quad5=createQuad(new Vec3d(x+1,y,offset),new Vec3d(x,y,offset),new Vec3d(x,y,-offset),new Vec3d(x+1,y,-offset),sprite2,Direction.NORTH,new TextureWrapper(px,py,1,1),-1);
							BakedQuad quad6=createQuad(new Vec3d(x,y+1,offset),new Vec3d(x+1,y+1,offset),new Vec3d(x+1,y+1,-offset),new Vec3d(x,y+1,-offset),sprite2,Direction.NORTH,new TextureWrapper(px,py,1,1),-1);
							transparent.add(quad);
							if (!oneSided) {
								transparent.add(quad2);
								try {
									if (new Color(sprite1.getPixelRGBA(0,x+1,y),true).getAlpha()==0) {
										transparent.add(quad3);
									}
								} catch (Exception err) {
									transparent.add(quad3);
								}
								try {
									if (new Color(sprite1.getPixelRGBA(0,x-1,y),true).getAlpha()==0) {
										transparent.add(quad4);
									}
								} catch (Exception err) {
									transparent.add(quad4);
								}
								try {
									if (new Color(sprite1.getPixelRGBA(0,x,y-1),true).getAlpha()==0) {
										transparent.add(quad5);
									}
								} catch (Exception err) {
									transparent.add(quad5);
								}
								try {
									if (new Color(sprite1.getPixelRGBA(0,x,y+1),true).getAlpha()==0) {
										transparent.add(quad6);
									}
								} catch (Exception err) {
									transparent.add(quad6);
								}
							}
							if (tx.twoTransparent) {
								transparent.add(quad);
								if (!oneSided) {
									transparent.add(quad2);
									try {
										if (new Color(sprite1.getPixelRGBA(0,x+1,y),true).getAlpha()==0) {
											transparent.add(quad3);
										}
									} catch (Exception err) {
										transparent.add(quad3);
									}
									try {
										if (new Color(sprite1.getPixelRGBA(0,x-1,y),true).getAlpha()==0) {
											transparent.add(quad4);
										}
									} catch (Exception err) {
										transparent.add(quad4);
									}
									try {
										if (new Color(sprite1.getPixelRGBA(0,x,y-1),true).getAlpha()==0) {
											transparent.add(quad5);
										}
									} catch (Exception err) {
										transparent.add(quad5);
									}
									try {
										if (new Color(sprite1.getPixelRGBA(0,x,y+1),true).getAlpha()==0) {
											transparent.add(quad6);
										}
									} catch (Exception err) {
										transparent.add(quad6);
									}
								}
							}

							quad=createQuad(new Vec3d(x,y,offset),new Vec3d(x+1,y,offset),new Vec3d(x+1,y+1,offset),new Vec3d(x,y+1,offset),sprite1,Direction.NORTH,new TextureWrapper(x,y,1,1),layer);
							if (!oneSided) {
								quad2=createQuad(new Vec3d(x+1,y,-offset),new Vec3d(x,y,-offset),new Vec3d(x,y+1,-offset),new Vec3d(x+1,y+1,-offset),sprite1,Direction.NORTH,new TextureWrapper(x,y,1,1),layer);
								quad3=createQuad(new Vec3d(x+1,y+1,offset),new Vec3d(x+1,y,offset),new Vec3d(x+1,y,-offset),new Vec3d(x+1,y+1,-offset),sprite1,Direction.NORTH,new TextureWrapper(x,y,1,1),layer);
								quad4=createQuad(new Vec3d(x,y,offset),new Vec3d(x,y+1,offset),new Vec3d(x,y+1,-offset),new Vec3d(x,y,-offset),sprite1,Direction.NORTH,new TextureWrapper(x,y,1,1),layer);
								quad5=createQuad(new Vec3d(x+1,y,offset),new Vec3d(x,y,offset),new Vec3d(x,y,-offset),new Vec3d(x+1,y,-offset),sprite1,Direction.NORTH,new TextureWrapper(x,y,1,1),layer);
								quad6=createQuad(new Vec3d(x,y+1,offset),new Vec3d(x+1,y+1,offset),new Vec3d(x+1,y+1,-offset),new Vec3d(x,y+1,-offset),sprite1,Direction.NORTH,new TextureWrapper(x,y,1,1),layer);
							}
							solid.add(quad);
							if (!oneSided) {
								solid.add(quad2);
								try {
									if (new Color(sprite1.getPixelRGBA(0,x+1,y),true).getAlpha()==0) {
										solid.add(quad3);
									}
								} catch (Exception err) {
									solid.add(quad3);
								}
								try {
									if (new Color(sprite1.getPixelRGBA(0,x-1,y),true).getAlpha()==0) {
										solid.add(quad4);
									}
								} catch (Exception err) {
									solid.add(quad4);
								}
								try {
									if (new Color(sprite1.getPixelRGBA(0,x,y-1),true).getAlpha()==0) {
										solid.add(quad5);
									}
								} catch (Exception err) {
									solid.add(quad5);
								}
								try {
									if (new Color(sprite1.getPixelRGBA(0,x,y+1),true).getAlpha()==0) {
										solid.add(quad6);
									}
								} catch (Exception err) {
									solid.add(quad6);
								}
							}
						}
					} catch (Exception err) {}
				}
			}
			layer++;
		}
		return new PreppedModel(solid,new ArrayList<>(),transparent);
	}
	public static PreppedModel prepExtrudedTextureNoTexCorrection(boolean oneSided, ExtrudedTexture... textures) {
		ArrayList<BakedQuad> transparent=new ArrayList<>();
		ArrayList<BakedQuad> solid=new ArrayList<>();
		int offset=0;
		int layer=0;
		for (ExtrudedTexture tx:textures) {
			offset+=tx.width;
			TextureAtlasSprite sprite1=Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(tx.base);
			TextureAtlasSprite sprite2=Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(tx.mask);
			for (int x=0;x<16+1;x++) {
				boolean isOnPixel=false;
				boolean justReleased=false;
				int startY=-999;
				int stopY=0;
				for (int y=0;y<16;y++) {
					try {
						if (new Color(sprite1.getPixelRGBA(0,x,y),true).getAlpha()!=0) {
							if (startY == -999) {
								startY = y;
							}
							stopY = y+1;
							isOnPixel=true;
							justReleased=false;
						} else {
							if (isOnPixel) {
								justReleased=true;
							}
						}
						if (justReleased||y==15) {
							if (isOnPixel) {
								try {
//										if (new Color(sprite1.getPixelRGBA(0, x, startY), true).getAlpha() != 0) {
									BakedQuad quad = createQuad(
											new Vec3d(x,stopY, -offset),
											new Vec3d(x+1,stopY, -offset),
											new Vec3d(x+1,startY, -offset),
											new Vec3d(x, startY, -offset),
											sprite2,
											Direction.NORTH, new TextureWrapper(stopY, x, -(stopY-startY),1), -1,1);
									BakedQuad quad2 = createQuad(
											new Vec3d(x + 1, stopY, offset),
											new Vec3d(x, stopY, offset),
											new Vec3d(x, startY, offset),
											new Vec3d(x + 1, startY, offset),
											sprite2,
											Direction.NORTH, new TextureWrapper(startY, x, (stopY-startY), 1), -1,0.5f);
									BakedQuad quad3 = createQuad(
											new Vec3d(x + 1, stopY, offset),
											new Vec3d(x + 1, startY, offset),
											new Vec3d(x + 1, startY, -offset),
											new Vec3d(x + 1, stopY, -offset),
											sprite2,
											Direction.NORTH, new TextureWrapper(x, startY, 1, (stopY-startY)), -1,0.5f);
									BakedQuad quad4 = createQuad(
											new Vec3d(x, startY, offset),
											new Vec3d(x, stopY, offset),
											new Vec3d(x, stopY, -offset),
											new Vec3d(x, startY, -offset), sprite2,
											Direction.NORTH, new TextureWrapper(x, startY, 1, (stopY-startY)), -1,0.5f);
									BakedQuad quad5 = createQuad(
											new Vec3d(x + 1, startY, offset),
											new Vec3d(x, startY, offset),
											new Vec3d(x, startY, -offset),
											new Vec3d(x + 1, startY, -offset),
											sprite2,
											Direction.NORTH, new TextureWrapper(startY, x, (stopY-startY), 1), -1,0.5f);
									BakedQuad quad6 = createQuad(
											new Vec3d(x, stopY, offset),
											new Vec3d(x + 1, stopY, offset),
											new Vec3d(x + 1, stopY, -offset),
											new Vec3d(x, stopY, -offset),
											sprite2,
											Direction.NORTH, new TextureWrapper(startY, x, (stopY-startY), 1), -1,0.5f);
									transparent.add(quad);
									if (!oneSided) {
										transparent.add(quad2);
										try {
											if (new Color(sprite1.getPixelRGBA(0, x + 1, y), true).getAlpha() == 0) {
												transparent.add(quad3);
											}
										} catch (Exception err) {
											transparent.add(quad3);
										}
										try {
											if (new Color(sprite1.getPixelRGBA(0, x - 1, y), true).getAlpha() == 0) {
												transparent.add(quad4);
											}
										} catch (Exception err) {
											transparent.add(quad4);
										}
										try {
											if (new Color(sprite1.getPixelRGBA(0, x, y - 1), true).getAlpha() == 0) {
												transparent.add(quad5);
											}
										} catch (Exception err) {
											transparent.add(quad5);
										}
										try {
											if (new Color(sprite1.getPixelRGBA(0, x, y + 1), true).getAlpha() == 0) {
												transparent.add(quad6);
											}
										} catch (Exception err) {
											transparent.add(quad6);
										}
									}
									if (tx.twoTransparent) {
										transparent.add(quad);
										if (!oneSided) {
											transparent.add(quad2);
											try {
												if (new Color(sprite1.getPixelRGBA(0, x + 1, y), true).getAlpha() == 0) {
													transparent.add(quad3);
												}
											} catch (Exception err) {
												transparent.add(quad3);
											}
											try {
												if (new Color(sprite1.getPixelRGBA(0, x - 1, y), true).getAlpha() == 0) {
													transparent.add(quad4);
												}
											} catch (Exception err) {
												transparent.add(quad4);
											}
											try {
												if (new Color(sprite1.getPixelRGBA(0, x, y - 1), true).getAlpha() == 0) {
													transparent.add(quad5);
												}
											} catch (Exception err) {
												transparent.add(quad5);
											}
											try {
												if (new Color(sprite1.getPixelRGBA(0, x, y + 1), true).getAlpha() == 0) {
													transparent.add(quad6);
												}
											} catch (Exception err) {
												transparent.add(quad6);
											}
										}
									}
									
									quad = createQuad(
											new Vec3d(x,stopY, -offset),
											new Vec3d(x+1,stopY, -offset),
											new Vec3d(x+1,startY, -offset),
											new Vec3d(x,startY, -offset),
											sprite1,
											Direction.NORTH, new TextureCoords(x,x+1,x+1,x,stopY,stopY,startY,startY), layer);
									if (!oneSided) {
										quad2 = createQuad(
												new Vec3d(x + 1, startY, -offset),
												new Vec3d(x, startY, -offset),
												new Vec3d(x, stopY, -offset),
												new Vec3d(x + 1, stopY, -offset),
												sprite1,
												Direction.NORTH, new TextureCoords(x+1,x,x,x+1,startY,startY,stopY,stopY), layer);
										quad3 = createQuad(
												new Vec3d(x + 1, stopY, offset),
												new Vec3d(x + 1, startY, offset),
												new Vec3d(x + 1, startY, -offset),
												new Vec3d(x + 1, stopY, -offset),
												sprite1,
												Direction.NORTH, new TextureWrapper(x, startY, 1, (stopY-startY)), layer);
										quad4 = createQuad(
												new Vec3d(x, startY, offset),
												new Vec3d(x, stopY, offset),
												new Vec3d(x, stopY, -offset),
												new Vec3d(x, startY, -offset), sprite1,
												Direction.NORTH, new TextureWrapper(x, startY, 1, (stopY-startY)), layer);
										quad5 = createQuad(
												new Vec3d(x + 1, startY, offset),
												new Vec3d(x, startY, offset),
												new Vec3d(x, startY, -offset),
												new Vec3d(x + 1, startY, -offset),
												sprite1,
												Direction.NORTH, new TextureWrapper(startY, x, (stopY-startY), 1), layer);
										quad6 = createQuad(
												new Vec3d(x, stopY, offset),
												new Vec3d(x + 1, stopY, offset),
												new Vec3d(x + 1, stopY, -offset),
												new Vec3d(x, stopY, -offset),
												sprite1,
												Direction.NORTH, new TextureWrapper(startY, x, (stopY-startY), 1), layer);
									}
									solid.add(quad);
									if (!oneSided) {
										solid.add(quad2);
										try {
											if (new Color(sprite1.getPixelRGBA(0, x + 1, y), true).getAlpha() == 0) {
												solid.add(quad3);
											}
										} catch (Exception err) {
											solid.add(quad3);
										}
										try {
											if (new Color(sprite1.getPixelRGBA(0, x - 1, y), true).getAlpha() == 0) {
												solid.add(quad4);
											}
										} catch (Exception err) {
											solid.add(quad4);
										}
										try {
											if (new Color(sprite1.getPixelRGBA(0, x, y - 1), true).getAlpha() == 0) {
												solid.add(quad5);
											}
										} catch (Exception err) {
											solid.add(quad5);
										}
										try {
											if (new Color(sprite1.getPixelRGBA(0, x, y + 1), true).getAlpha() == 0) {
												solid.add(quad6);
											}
										} catch (Exception err) {
											solid.add(quad6);
										}
									}
//										}
								} catch (Exception err) {
								}
								isOnPixel=false;
								startY=-999;
							}
						}
					} catch (Exception err) {}
				}
			}
			layer++;
		}
		return new PreppedModel(solid,new ArrayList<>(),transparent);
	}

	public static PreppedModel prepModel(TexturedModel mdl,boolean inverted) {
		ArrayList<BakedQuad> transparent=new ArrayList<>();
		ArrayList<BakedQuad> solid=new ArrayList<>();
		ArrayList<BakedQuad> solid2=new ArrayList<>();
		for (TexturedQuad qd:mdl.quads) {
			try {
				if (!qd.useCoords) {
					BakedQuad quad=createQuad(inverted?qd.vec4:qd.vec1,inverted?qd.vec3:qd.vec2,inverted?qd.vec2:qd.vec3,inverted?qd.vec1:qd.vec4,qd.sprite,Direction.NORTH,qd.wrapper,-1,0.5f);
					if (qd.isTransparent) {
						transparent.add(quad);
					} else if (qd.isPrimary) {
						solid.add(quad);
					} else {
						solid2.add(quad);
					}
				} else {
					BakedQuad quad=createQuad(inverted?qd.vec4:qd.vec1,inverted?qd.vec3:qd.vec2,inverted?qd.vec2:qd.vec3,inverted?qd.vec1:qd.vec4,qd.sprite,Direction.NORTH,qd.coords,-1);
					if (qd.isTransparent) {
						transparent.add(quad);
					} else if (qd.isPrimary) {
						solid.add(quad);
					} else {
						solid2.add(quad);
					}
				}
			} catch (Exception err) {
			}
		}
		return new PreppedModel(solid,solid2,transparent);
	}
	
	public static TexturedModel createExtrudedTextureNoTexCorrection(boolean oneSided, ExtrudedTexture... textures) {
		ArrayList<TexturedQuad> Quads=new ArrayList<>();
		int offset=0;
		int layer=0;
		for (ExtrudedTexture tx:textures) {
			offset+=tx.width;
			TextureAtlasSprite sprite1=Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(tx.base);
			TextureAtlasSprite sprite2=Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(tx.mask);
			for (int x=0;x<16+1;x++) {
				boolean isOnPixel=false;
				boolean justReleased=false;
				int startY=-999;
				int stopY=0;
				for (int y=0;y<16;y++) {
					try {
						if (new Color(sprite1.getPixelRGBA(0,x,y),true).getAlpha()!=0) {
							if (startY == -999) {
								startY = y;
							}
							stopY = y+1;
							isOnPixel=true;
							justReleased=false;
						} else {
							if (isOnPixel) {
								justReleased=true;
							}
						}
						if (justReleased||y==15) {
							if (isOnPixel) {
								try {
//										if (new Color(sprite1.getPixelRGBA(0, x, startY), true).getAlpha() != 0) {
									TexturedQuad quad = new TexturedQuad(
											new Vec3d(x,stopY, -offset),
											new Vec3d(x+1,stopY, -offset),
											new Vec3d(x+1,startY, -offset),
											new Vec3d(x, startY, -offset),
											new TextureWrapper(stopY, x, -(stopY-startY),1),
											sprite2);
									TexturedQuad quad2 = new TexturedQuad(
											new Vec3d(x + 1, stopY, offset),
											new Vec3d(x, stopY, offset),
											new Vec3d(x, startY, offset),
											new Vec3d(x + 1, startY, offset),
											new TextureWrapper(startY, x, (stopY-startY), 1),
											sprite2);
									TexturedQuad quad3 = new TexturedQuad(
											new Vec3d(x + 1, stopY, offset),
											new Vec3d(x + 1, startY, offset),
											new Vec3d(x + 1, startY, -offset),
											new Vec3d(x + 1, stopY, -offset),
											new TextureWrapper(x, startY, 1, (stopY-startY)),
											sprite2);
									TexturedQuad quad4 = new TexturedQuad(
											new Vec3d(x, startY, offset),
											new Vec3d(x, stopY, offset),
											new Vec3d(x, stopY, -offset),
											new Vec3d(x, startY, -offset),
											new TextureWrapper(x, startY, 1, (stopY-startY)),
											sprite2);
									TexturedQuad quad5 = new TexturedQuad(
											new Vec3d(x + 1, startY, offset),
											new Vec3d(x, startY, offset),
											new Vec3d(x, startY, -offset),
											new Vec3d(x + 1, startY, -offset),
											new TextureWrapper(startY, x, (stopY-startY), 1),
											sprite2);
									TexturedQuad quad6 = new TexturedQuad(
											new Vec3d(x, stopY, offset),
											new Vec3d(x + 1, stopY, offset),
											new Vec3d(x + 1, stopY, -offset),
											new Vec3d(x, stopY, -offset),
											new TextureWrapper(startY, x, (stopY-startY), 1),
											sprite2);
									quad.setTransparent();
									quad2.setTransparent();
									quad3.setTransparent();
									quad4.setTransparent();
									quad5.setTransparent();
									quad6.setTransparent();
									Quads.add(quad);
									if (!oneSided) {
										Quads.add(quad2);
										try {
											if (new Color(sprite1.getPixelRGBA(0, x + 1, y), true).getAlpha() == 0) {
												Quads.add(quad3);
											}
										} catch (Exception err) {
											Quads.add(quad3);
										}
										try {
											if (new Color(sprite1.getPixelRGBA(0, x - 1, y), true).getAlpha() == 0) {
												Quads.add(quad4);
											}
										} catch (Exception err) {
											Quads.add(quad4);
										}
										try {
											if (new Color(sprite1.getPixelRGBA(0, x, y - 1), true).getAlpha() == 0) {
												Quads.add(quad5);
											}
										} catch (Exception err) {
											Quads.add(quad5);
										}
										try {
											if (new Color(sprite1.getPixelRGBA(0, x, y + 1), true).getAlpha() == 0) {
												Quads.add(quad6);
											}
										} catch (Exception err) {
											Quads.add(quad6);
										}
									}
									if (tx.twoTransparent) {
										Quads.add(quad);
										if (!oneSided) {
											Quads.add(quad2);
											try {
												if (new Color(sprite1.getPixelRGBA(0, x + 1, y), true).getAlpha() == 0) {
													Quads.add(quad3);
												}
											} catch (Exception err) {
												Quads.add(quad3);
											}
											try {
												if (new Color(sprite1.getPixelRGBA(0, x - 1, y), true).getAlpha() == 0) {
													Quads.add(quad4);
												}
											} catch (Exception err) {
												Quads.add(quad4);
											}
											try {
												if (new Color(sprite1.getPixelRGBA(0, x, y - 1), true).getAlpha() == 0) {
													Quads.add(quad5);
												}
											} catch (Exception err) {
												Quads.add(quad5);
											}
											try {
												if (new Color(sprite1.getPixelRGBA(0, x, y + 1), true).getAlpha() == 0) {
													Quads.add(quad6);
												}
											} catch (Exception err) {
												Quads.add(quad6);
											}
										}
									}
									
									quad = new TexturedQuad(
											new Vec3d(x,stopY, -offset),
											new Vec3d(x+1,stopY, -offset),
											new Vec3d(x+1,startY, -offset),
											new Vec3d(x,startY, -offset),
											new TextureCoords(x,x+1,x+1,x,stopY,stopY,startY,startY),
											sprite1);
									if (!oneSided) {
										quad2 = new TexturedQuad(
												new Vec3d(x + 1, startY, -offset),
												new Vec3d(x, startY, -offset),
												new Vec3d(x, stopY, -offset),
												new Vec3d(x + 1, stopY, -offset),
												new TextureCoords(x+1,x,x,x+1,startY,startY,stopY,stopY),
												sprite1);
										quad3 = new TexturedQuad(
												new Vec3d(x + 1, stopY, offset),
												new Vec3d(x + 1, startY, offset),
												new Vec3d(x + 1, startY, -offset),
												new Vec3d(x + 1, stopY, -offset),
												new TextureWrapper(x, startY, 1, (stopY-startY)),
												sprite1);
										quad4 = new TexturedQuad(
												new Vec3d(x, startY, offset),
												new Vec3d(x, stopY, offset),
												new Vec3d(x, stopY, -offset),
												new Vec3d(x, startY, -offset),
												new TextureWrapper(x, startY, 1, (stopY-startY)),
												sprite1);
										quad5 = new TexturedQuad(
												new Vec3d(x + 1, startY, offset),
												new Vec3d(x, startY, offset),
												new Vec3d(x, startY, -offset),
												new Vec3d(x + 1, startY, -offset),
												new TextureWrapper(startY, x, (stopY-startY), 1),
												sprite1);
										quad6 = new TexturedQuad(
												new Vec3d(x, stopY, offset),
												new Vec3d(x + 1, stopY, offset),
												new Vec3d(x + 1, stopY, -offset),
												new Vec3d(x, stopY, -offset),
												new TextureWrapper(startY, x, (stopY-startY), 1),
												sprite1);
									}
									Quads.add(quad);
									if (!oneSided) {
										Quads.add(quad2);
										try {
											if (new Color(sprite1.getPixelRGBA(0, x + 1, y), true).getAlpha() == 0) {
												Quads.add(quad3);
											}
										} catch (Exception err) {
											Quads.add(quad3);
										}
										try {
											if (new Color(sprite1.getPixelRGBA(0, x - 1, y), true).getAlpha() == 0) {
												Quads.add(quad4);
											}
										} catch (Exception err) {
											Quads.add(quad4);
										}
										try {
											if (new Color(sprite1.getPixelRGBA(0, x, y - 1), true).getAlpha() == 0) {
												Quads.add(quad5);
											}
										} catch (Exception err) {
											Quads.add(quad5);
										}
										try {
											if (new Color(sprite1.getPixelRGBA(0, x, y + 1), true).getAlpha() == 0) {
												Quads.add(quad6);
											}
										} catch (Exception err) {
											Quads.add(quad6);
										}
									}
//										}
								} catch (Exception err) {
								}
								isOnPixel=false;
								startY=-999;
							}
						}
					} catch (Exception err) {}
				}
			}
			layer++;
		}
		return new TexturedModel(Quads);
	}
	
	public static PreppedModel createFlatPreppedModel(ResourceLocation sprite,boolean transparent) {
		int a=0;
		int b=16;
		BakedQuad qd=createQuad(
				new Vec3d(a,a, 0),
				new Vec3d(a,b, 0),
				new Vec3d(b,b, 0),
				new Vec3d(b,a, 0),
				Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(sprite),Direction.NORTH,new TextureWrapper(0,0,16,16));
		ArrayList<BakedQuad> qds=new ArrayList<>();
		qds.add(qd);
		return new PreppedModel(transparent?new ArrayList<>():qds,new ArrayList<>(),transparent?qds:new ArrayList<>());
	}
	
	public static TexturedModel createFlatTexturedModel(ResourceLocation sprite,boolean transparent) {
		int a=0;
		int b=16;
		TexturedQuad qd=new TexturedQuad(
				new Vec3d(a,a, 0),
				new Vec3d(a,b, 0),
				new Vec3d(b,b, 0),
				new Vec3d(b,a, 0),
				new TextureWrapper(0,0,16,16),
				Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(sprite));
		ArrayList<TexturedQuad> qds=new ArrayList<>();
		if (transparent) {
			qd=qd.setTransparent();
		}
		qds.add(qd);
		return new TexturedModel(qds);
	}
	
	public static void renderPreparedModel(PreppedModel mdl,  IRenderTypeBuffer buffer, MatrixStack matrixStack, int combinedLightIn,int combinedOverlayIn, int... tints) {
		LineColors.colors=new ArrayList<>();
		for (int tint : tints) {
			LineColors.colors.add(tint);
		}
		Minecraft.getInstance().getItemRenderer().renderQuads(matrixStack,buffer.getBuffer(RenderType.getSolid()),mdl.solid1,new ItemStack(TintHelper.CUBE.get()),combinedLightIn,combinedOverlayIn);
		Minecraft.getInstance().getItemRenderer().renderQuads(matrixStack,buffer.getBuffer(RenderType.getTranslucent()),mdl.transparent,new ItemStack(TintHelper.CUBE.get()),combinedLightIn,combinedOverlayIn);
		Minecraft.getInstance().getItemRenderer().renderQuads(matrixStack,buffer.getBuffer(RenderType.getSolid()),mdl.solid2,new ItemStack(TintHelper.CUBE.get()),combinedLightIn,combinedOverlayIn);
		LineColors.colors.clear();
	}
	
	//MCJTY:https://github.com/McJty/YouTubeModding14/blob/master/src/main/java/com/mcjty/mytutorial/blocks/FancyBakedModel.java
	public static BakedQuad createQuad(
			Vec3d v1, Vec3d v2, Vec3d v3, Vec3d v4,
			TextureAtlasSprite sprite, Direction dir, TextureWrapper r,int tint,float a
	) {
		Vec3d normal = v3.subtract(v2).crossProduct(v1.subtract(v2)).normalize();
		
		BakedQuadBuilder builder = new BakedQuadBuilder(sprite);
		builder.setQuadOrientation(dir);
		putVertex(builder, normal, v1.x, v1.y, v1.z, r.tx, r.ty, sprite, 1.0f, 1.0f, 1.0f,a);
		putVertex(builder, normal, v2.x, v2.y, v2.z, r.tx, r.ty+r.th, sprite, 1.0f, 1.0f, 1.0f,a);
		putVertex(builder, normal, v3.x, v3.y, v3.z, r.tx+r.tw, r.ty+r.th, sprite, 1.0f, 1.0f, 1.0f,a);
		putVertex(builder, normal, v4.x, v4.y, v4.z, r.tx+r.tw, r.ty, sprite, 1.0f, 1.0f, 1.0f,a);
		builder.setQuadTint(tint);
		return builder.build();
	}
	public static BakedQuad createQuad(
			Vec3d v1, Vec3d v2, Vec3d v3, Vec3d v4,
			TextureAtlasSprite sprite, Direction dir, TextureCoords r,int tint
	) {
		Vec3d normal = v3.subtract(v2).crossProduct(v1.subtract(v2)).normalize();
		
		BakedQuadBuilder builder = new BakedQuadBuilder(sprite);
		builder.setQuadOrientation(dir);
		putVertex(builder, normal, v1.x, v1.y, v1.z, r.tx1, r.ty1, sprite, 1.0f, 1.0f, 1.0f,1);
		putVertex(builder, normal, v2.x, v2.y, v2.z, r.tx2, r.ty2, sprite, 1.0f, 1.0f, 1.0f,1);
		putVertex(builder, normal, v3.x, v3.y, v3.z, r.tx3, r.ty3, sprite, 1.0f, 1.0f, 1.0f,1);
		putVertex(builder, normal, v4.x, v4.y, v4.z, r.tx4, r.ty4, sprite, 1.0f, 1.0f, 1.0f,1);
		builder.setQuadTint(tint);
		return builder.build();
	}
	public static BakedQuad createQuad(
			Vec3d v1, Vec3d v2, Vec3d v3, Vec3d v4,
			TextureAtlasSprite sprite, Direction dir, TextureWrapper r,int tint
	) {
		return createQuad(v1,v2,v3,v4,sprite,dir,r,tint,1);
	}
	public static BakedQuad createQuad(
			Vec3d v1, Vec3d v2, Vec3d v3, Vec3d v4,
			TextureAtlasSprite sprite, Direction dir, TextureWrapper r
	) {
		return createQuad(v1,v2,v3,v4,sprite,dir,r,0,1);
	}
	
	//MCJTY:https://github.com/McJty/YouTubeModding14/blob/master/src/main/java/com/mcjty/mytutorial/blocks/FancyBakedModel.java
	private static void putVertex(BakedQuadBuilder builder, Vec3d normal,
						   double x, double y, double z, double u, double v, TextureAtlasSprite sprite, double r, double g, double b,float a) {
		
		ImmutableList<VertexFormatElement> elements = builder.getVertexFormat().getElements().asList();
		for (int j = 0 ; j < elements.size() ; j++) {
			VertexFormatElement e = elements.get(j);
			switch (e.getUsage()) {
				case POSITION:
					builder.put(j, (float) x, (float) y, (float) z, 1.0f);
					break;
				case COLOR:
					builder.put(j, (float) r, (float) g, (float) b, a);
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
