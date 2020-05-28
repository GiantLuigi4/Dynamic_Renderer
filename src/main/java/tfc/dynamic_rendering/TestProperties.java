package tfc.dynamic_rendering;

import net.minecraft.item.Item;

public class TestProperties extends Item.Properties {
	public TestProperties() {
		this.setISTER(()->StackRenderer::new);
	}
}
