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
	
	public static PreppedModel merge(PreppedModel mdl1,PreppedModel mdl2) {
		ArrayList<BakedQuad> solid1=new ArrayList<>();
		ArrayList<BakedQuad> solid2=new ArrayList<>();
		ArrayList<BakedQuad> transparent=new ArrayList<>();
		solid1.addAll(mdl1.solid1);
		solid2.addAll(mdl1.solid2);
		transparent.addAll(mdl1.transparent);
		solid1.addAll(mdl2.solid1);
		solid2.addAll(mdl2.solid2);
		transparent.addAll(mdl2.transparent);
		return new PreppedModel(solid1,solid2,transparent);
	}
}
