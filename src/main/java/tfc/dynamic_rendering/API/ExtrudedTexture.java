package tfc.dynamic_rendering.API;

import net.minecraft.util.ResourceLocation;

public class ExtrudedTexture {
	public ResourceLocation base;
	public ResourceLocation mask;
	public int width;
	public boolean twoTransparent=false;
	
	public ExtrudedTexture(ResourceLocation base, ResourceLocation mask, int width) {
		this.base = base;
		this.mask = mask;
		this.width = width;
	}
	
	public ExtrudedTexture(ResourceLocation base, ResourceLocation mask, int width,boolean doubleTransparent) {
		this(base, mask, width);
		this.twoTransparent=doubleTransparent;
	}
}
