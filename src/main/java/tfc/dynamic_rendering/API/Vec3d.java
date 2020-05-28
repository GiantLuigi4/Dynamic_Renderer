package tfc.dynamic_rendering.API;

import net.minecraft.util.math.MathHelper;

//Redo of minecraft vec3D that fits my needs
public class Vec3d {
	public static final Vec3d ZERO = new Vec3d(0.0D, 0.0D, 0.0D);
	
	protected double x;
	protected double y;
	protected double z;
	
	public Vec3d(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vec3d crossProduct(Vec3d vec) {
		return new Vec3d(this.y * vec.z - this.z * vec.y, this.z * vec.x - this.x * vec.z, this.x * vec.y - this.y * vec.x);
	}
	
	public Vec3d subtract(Vec3d vec) {
		return this.subtract(vec.x, vec.y, vec.z);
	}
	
	public Vec3d subtract(double x, double y, double z) {
		return this.add(-x, -y, -z);
	}
	
	public Vec3d add(double x, double y, double z) {
		return new Vec3d(this.x + x, this.y + y, this.z + z);
	}
	
	public Vec3d normalize() {
		double d0 = MathHelper.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
		return d0 < 1.0E-4D ? ZERO : new Vec3d(this.x / d0, this.y / d0, this.z / d0);
	}
	
	public double distanceTo(Vec3d vec) {
		double d0 = vec.x - this.x;
		double d1 = vec.y - this.y;
		double d2 = vec.z - this.z;
		return MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
	}
	public double distanceTo(double xIn, double yIn, double zIn) {
		double d0 = xIn - this.x;
		double d1 = yIn - this.y;
		double d2 = zIn - this.z;
		return MathHelper.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
	}
	
	public double squareDistanceTo(Vec3d vec) {
		double d0 = vec.x - this.x;
		double d1 = vec.y - this.y;
		double d2 = vec.z - this.z;
		return d0 * d0 + d1 * d1 + d2 * d2;
	}
	
	public double squareDistanceTo(double xIn, double yIn, double zIn) {
		double d0 = xIn - this.x;
		double d1 = yIn - this.y;
		double d2 = zIn - this.z;
		return d0 * d0 + d1 * d1 + d2 * d2;
	}
}
