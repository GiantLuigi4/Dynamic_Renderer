package tfc.dynamic_rendering.API;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;

public class TexturedQuad extends Quad {
	TextureAtlasSprite sprite;
	boolean isTransparent=false;
	boolean isPrimary=true;
	
	public TexturedQuad(Vec3d vec1, Vec3d vec2, Vec3d vec3, Vec3d vec4, TextureWrapper wrapper, TextureAtlasSprite sprite) {
		super(vec1, vec2, vec3, vec4, wrapper);
		this.sprite = sprite;
	}
	
	public TexturedQuad(Quad qd,TextureAtlasSprite sprite) {
		super(qd);
		this.sprite = sprite;
	}
	
	public TexturedQuad setTransparent() {
		this.isTransparent=true;
		return this;
	}
	
	public TexturedQuad setNotPrimary() {
		this.isPrimary=false;
		return this;
	}
	
	public TexturedQuad(Vec3d vec1, Vec3d vec2, Vec3d vec3, Vec3d vec4, TextureCoords coords, TextureAtlasSprite sprite) {
		super(vec1, vec2, vec3, vec4, coords);
		this.sprite = sprite;
	}
	
//	public TexturedQuad(Vec3d vec1, Vec3d vec2, Vec3d vec3, Vec3d vec4, TextureWrapper wrapper) {
//		super(vec1, vec2, vec3, vec4, wrapper);
//	}
	
	public TexturedQuad shrinkT(float amt) {
		return new TexturedQuad(super.shrink(amt),this.sprite);
	}
	
	public TexturedQuad shrinkThisT(float amt) {
		super.shrinkThis(amt);
		return this;
	}
	
	public TexturedQuad growT(float amt) {
		return new TexturedQuad(super.grow(amt),this.sprite);
	}
	
	public TexturedQuad growThisT(float amt) {
		super.growThis(amt);
		return this;
	}
}
