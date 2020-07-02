package tfc.dynamic_rendering.API;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.ResourceLocation;
import tfc.dynamic_rendering.Color;

public class ColorHelper {
	public static int getColor(ResourceLocation location) {
		TextureAtlasSprite sprite2=Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).apply(location);
		Color c=null;
		for (int x=0;x<sprite2.getWidth();x++) {
			for (int y=0;y<sprite2.getHeight();y++) {
				Color c1=new Color(sprite2.getPixelRGBA(0,x,y));
				if (c1.getAlpha()!=0) {
					if (c==null) {
						c=c1;
					} else {
						c=c1.average(c);
					}
				}
			}
		}
		return c.getRGB();
	}
}
