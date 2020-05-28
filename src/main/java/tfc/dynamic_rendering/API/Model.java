package tfc.dynamic_rendering.API;

public class Model {
	Quad[] quads;
	
	public Model(Quad[] quads) {
		this.quads = quads;
	}
	public Model shrink(float amt) {
		Model newModel=new Model(new Quad[quads.length]);
		for (int i=0;i<=quads.length;i++) {
			newModel.quads[i]=quads[i].shrink(amt);
		}
		return newModel;
	}
	public Model shrinkThis(float amt) {
		for (int i=0;i<=quads.length;i++) {
			quads[i].shrinkThis(amt);
		}
		return this;
	}
	public Model grow(float amt) {
		return shrink(1f/amt);
	}
	public Model growThis(float amt) {
		return shrinkThis(1f/amt);
	}
}
