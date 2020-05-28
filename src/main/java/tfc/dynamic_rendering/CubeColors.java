package tfc.dynamic_rendering;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class CubeColors implements IItemColor {
	public static int tint=0;
	@Override
	public int getColor(ItemStack p_getColor_1_, int p_getColor_2_) {
		return tint;
	}
}
