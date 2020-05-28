package tfc.dynamic_rendering.API;

public class TexturedModel {
	TexturedQuad[] quads;
	
	public TexturedModel(TexturedQuad[] quads) {
		this.quads = quads;
	}
	public TexturedModel shrink(float amt) {
		TexturedModel newModel=new TexturedModel(new TexturedQuad[quads.length]);
		for (int i=0;i<=quads.length;i++) {
			newModel.quads[i]=quads[i].shrinkT(amt);
		}
		return newModel;
	}
	public TexturedModel shrinkThis(float amt) {
		for (int i=0;i<=quads.length;i++) {
			quads[i].shrinkThisT(amt);
		}
		return this;
	}
	public TexturedModel grow(float amt) {
		return shrink(1f/amt);
	}
	public TexturedModel growThis(float amt) {
		return shrinkThis(1f/amt);
	}
}
