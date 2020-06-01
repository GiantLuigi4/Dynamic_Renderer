package tfc.dynamic_rendering;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

public class LineColors implements IItemColor {
	public static ArrayList<Integer> colors=new ArrayList<>();
	@Override
	public int getColor(ItemStack p_getColor_1_, int p_getColor_2_) {
		if (p_getColor_2_==-1) {
			return new Color(255,255,255).getRGB();
		}
		try {
			return colors.get(p_getColor_2_);
		} catch (Exception err) {
			return new Color(255,255,255).getRGB();
		}
	}
}
