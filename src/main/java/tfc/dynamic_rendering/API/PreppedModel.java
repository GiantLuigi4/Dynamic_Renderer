package tfc.dynamic_rendering.API;

import net.minecraft.client.renderer.model.BakedQuad;

import java.util.ArrayList;

public class PreppedModel {
	protected ArrayList<BakedQuad> solid1;
	protected ArrayList<BakedQuad> solid2;
	protected ArrayList<BakedQuad> transparent;
	
	protected PreppedModel(ArrayList<BakedQuad> solid1, ArrayList<BakedQuad> solid2, ArrayList<BakedQuad> transparent) {
		this.solid1 = solid1;
		this.solid2 = solid2;
		this.transparent = transparent;
	}
}
