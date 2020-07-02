package tfc.dynamic_rendering.API;

import java.util.ArrayList;
import java.util.Arrays;

public class TexturedModel {
	TexturedQuad[] quads;
	
	public TexturedModel(TexturedQuad[] quads) {
		this.quads = quads;
	}
	
	public TexturedModel(ArrayList<TexturedQuad> qds) {
		quads=new TexturedQuad[qds.size()];
		for (int i=0;i<qds.size();i++) {
			quads[i]=qds.get(i);
		}
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
	
	public TexturedModel merge(TexturedModel... others) {
		ArrayList<TexturedQuad> newQuads=new ArrayList<>();
		for (TexturedModel mdl:others) {
			newQuads.addAll(Arrays.asList(mdl.quads));
		}
		newQuads.addAll(Arrays.asList(quads));
		return new TexturedModel(newQuads);
	}
	
	public TexturedModel mirror() {
		TexturedQuad[] newQuads=Arrays.copyOf(quads,quads.length);
		for (int i=0;i<newQuads.length;i++) {
			TexturedQuad newQd;
			if (newQuads[i].useCoords) {
				newQd = new TexturedQuad(
						quads[i].vec4,
						quads[i].vec3,
						quads[i].vec2,
						quads[i].vec1,
						newQuads[i].coords,
						newQuads[i].sprite
				);
			} else {
				newQd = new TexturedQuad(
						quads[i].vec4,
						quads[i].vec3,
						quads[i].vec2,
						quads[i].vec1,
						newQuads[i].wrapper,
						newQuads[i].sprite
				);
			}
			if (!quads[i].isPrimary) {
				newQd.setNotPrimary();
			}
			if (quads[i].isTransparent) {
				newQd.setTransparent();
			}
			newQuads[i]=newQd;
		}
		return new TexturedModel(newQuads);
	}
	
	public TexturedModel rotate(float x, float y) {
		TexturedQuad[] newQuads=Arrays.copyOf(quads,quads.length);
		for (int i=0;i<newQuads.length;i++) {
			TexturedQuad newQd;
			if (newQuads[i].useCoords) {
				newQd=new TexturedQuad(
						newQuads[i].vec1.rotateYaw(x).rotatePitch(y),
						newQuads[i].vec2.rotateYaw(x).rotatePitch(y),
						newQuads[i].vec3.rotateYaw(x).rotatePitch(y),
						newQuads[i].vec4.rotateYaw(x).rotatePitch(y),
						newQuads[i].coords,
						newQuads[i].sprite
				);
			} else {
				newQd=new TexturedQuad(
						newQuads[i].vec1.rotateYaw(x).rotatePitch(y),
						newQuads[i].vec2.rotateYaw(x).rotatePitch(y),
						newQuads[i].vec3.rotateYaw(x).rotatePitch(y),
						newQuads[i].vec4.rotateYaw(x).rotatePitch(y),
						newQuads[i].wrapper,
						newQuads[i].sprite
				);
			}
			if (!quads[i].isPrimary) {
				newQd.setNotPrimary();
			}
			if (quads[i].isTransparent) {
				newQd.setTransparent();
			}
			newQuads[i]=newQd;
		}
		return new TexturedModel(newQuads);
	}
	
	public TexturedModel scale(float x, float y, float z) {
		TexturedQuad[] newQuads=Arrays.copyOf(quads,quads.length);
		for (int i=0;i<newQuads.length;i++) {
			TexturedQuad newQd;
			if (newQuads[i].useCoords) {
				newQd=new TexturedQuad(
						new Vec3d(newQuads[i].vec1.x*x,newQuads[i].vec1.y*y,newQuads[i].vec1.z*z),
						new Vec3d(newQuads[i].vec2.x*x,newQuads[i].vec2.y*y,newQuads[i].vec2.z*z),
						new Vec3d(newQuads[i].vec3.x*x,newQuads[i].vec3.y*y,newQuads[i].vec3.z*z),
						new Vec3d(newQuads[i].vec4.x*x,newQuads[i].vec4.y*y,newQuads[i].vec4.z*z),
						newQuads[i].coords,
						newQuads[i].sprite
				);
			} else {
				newQd=new TexturedQuad(
						new Vec3d(newQuads[i].vec1.x*x,newQuads[i].vec1.y*y,newQuads[i].vec1.z*z),
						new Vec3d(newQuads[i].vec2.x*x,newQuads[i].vec2.y*y,newQuads[i].vec2.z*z),
						new Vec3d(newQuads[i].vec3.x*x,newQuads[i].vec3.y*y,newQuads[i].vec3.z*z),
						new Vec3d(newQuads[i].vec4.x*x,newQuads[i].vec4.y*y,newQuads[i].vec4.z*z),
						newQuads[i].wrapper,
						newQuads[i].sprite
				);
			}
			if (!quads[i].isPrimary) {
				newQd.setNotPrimary();
			}
			if (quads[i].isTransparent) {
				newQd.setTransparent();
			}
			newQuads[i]=newQd;
		}
		return new TexturedModel(newQuads);
	}
	public TexturedModel translate(float x, float y, float z) {
		TexturedQuad[] newQuads=Arrays.copyOf(quads,quads.length);
		Vec3d offset=new Vec3d(x,y,z);
		for (int i=0;i<newQuads.length;i++) {
			TexturedQuad newQd;
			if (newQuads[i].useCoords) {
				newQd=new TexturedQuad(
						newQuads[i].vec1.add(offset),
						newQuads[i].vec2.add(offset),
						newQuads[i].vec3.add(offset),
						newQuads[i].vec4.add(offset),
						newQuads[i].coords,
						newQuads[i].sprite
				);
			} else {
				newQd=new TexturedQuad(
						newQuads[i].vec1.add(offset),
						newQuads[i].vec2.add(offset),
						newQuads[i].vec3.add(offset),
						newQuads[i].vec4.add(offset),
						newQuads[i].wrapper,
						newQuads[i].sprite
				);
			}
			if (!quads[i].isPrimary) {
				newQd.setNotPrimary();
			}
			if (quads[i].isTransparent) {
				newQd.setTransparent();
			}
			newQuads[i]=newQd;
		}
		return new TexturedModel(newQuads);
	}
}
