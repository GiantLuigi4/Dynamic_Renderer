package tfc.dynamic_rendering;

import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;

import java.util.ArrayList;

public class CustomBuffer implements IRenderTypeBuffer {
	public ArrayList<IVertexBuilder> builders=new ArrayList<>();
	
	@Override
	public IVertexBuilder getBuffer(RenderType p_getBuffer_1_) {
		builders.add(new CustomVertexBuilder(p_getBuffer_1_));
		return builders.get(builders.size()-1);
	}
	
	public static class CustomVertexBuilder implements IVertexBuilder {
		public RenderType type;
		public ArrayList<Vertex> vertices=new ArrayList<>();
		private Vertex vert=new Vertex();
		
		public CustomVertexBuilder(RenderType type) {
			this.type=type;
		}
		
		@Override
		public IVertexBuilder pos(double x, double y, double z) {
			vert.x=x;
			vert.y=y;
			vert.z=z;
			return this;
		}
		
		@Override
		public IVertexBuilder color(int red, int green, int blue, int alpha) {
			vert.r=red;
			vert.g=green;
			vert.b=blue;
			vert.a=alpha;
			return this;
		}
		
		@Override
		public IVertexBuilder tex(float u, float v) {
			vert.tx=v;
			vert.ty=u;
			return this;
		}
		
		@Override
		public IVertexBuilder overlay(int u, int v) {
			vert.ou=u;
			vert.ov=v;
			return this;
		}
		
		@Override
		public IVertexBuilder lightmap(int u, int v) {
			vert.lu=u;
			vert.lv=v;
			return this;
		}
		
		@Override
		public IVertexBuilder normal(float x, float y, float z) {
			vert.nx=x;
			vert.ny=y;
			vert.nz=z;
			return this;
		}
		
		@Override
		public void endVertex() {
			this.vertices.add(vert);
			this.vert=new Vertex();
		}
		
		public static class Vertex {
			public double x;
			public double y;
			public double z;
			public double r;
			public double g;
			public double b;
			public double a;
			public double tx;
			public double ty;
			public double ou;
			public double ov;
			public double lu;
			public double lv;
			public double nx;
			public double ny;
			public double nz;
		}
	}
}
