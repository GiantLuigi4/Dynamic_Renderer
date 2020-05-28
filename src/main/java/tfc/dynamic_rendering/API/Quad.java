package tfc.dynamic_rendering.API;

public class Quad {
	protected Vec3d vec1;
	protected Vec3d vec2;
	protected Vec3d vec3;
	protected Vec3d vec4;
	protected TextureWrapper wrapper;
	
	public Quad(Vec3d vec1, Vec3d vec2, Vec3d vec3, Vec3d vec4, TextureWrapper wrapper) {
		this.vec1 = vec1;
		this.vec2 = vec2;
		this.vec3 = vec3;
		this.vec4 = vec4;
		this.wrapper = wrapper;
	}
	public Quad shrink(float amt) {
		Quad newQuad=new Quad(new Vec3d(
				vec1.x*(1f/amt),
				vec1.y*(1f/amt),
				vec1.z*(1f/amt)
		),new Vec3d(
				vec2.x*(1f/amt),
				vec2.y*(1f/amt),
				vec2.z*(1f/amt)
		),new Vec3d(
				vec3.x*(1f/amt),
				vec3.y*(1f/amt),
				vec3.z*(1f/amt)
		),new Vec3d(
				vec4.x*(1f/amt),
				vec4.y*(1f/amt),
				vec4.z*(1f/amt)
		),wrapper
		);
		return newQuad;
	}
	public Quad shrinkThis(float amt) {
		vec1.x*=(1f/amt);
		vec1.y*=(1f/amt);
		vec1.z*=(1f/amt);
		vec2.x*=(1f/amt);
		vec2.y*=(1f/amt);
		vec2.z*=(1f/amt);
		vec3.x*=(1f/amt);
		vec3.y*=(1f/amt);
		vec3.z*=(1f/amt);
		vec4.x*=(1f/amt);
		vec4.y*=(1f/amt);
		vec4.z*=(1f/amt);
		return this;
	}
	public Quad grow(float amt) {
		return shrink(1f/amt);
	}
	public Quad growThis(float amt) {
		return shrinkThis(1f/amt);
	}
}
