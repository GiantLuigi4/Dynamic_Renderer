package tfc.dynamic_rendering;

public class Color{
	int value;
	public Color(int r, int g, int b) { this(r, g, b, 255); }
	public Color(int r, int g, int b, int a) {
		value = ((a & 0xFF) << 24) |
				((r & 0xFF) << 16) |
				((g & 0xFF) << 8)  |
				((b & 0xFF));
	}
	public Color(int rgb) { value = 0xff000000 | rgb; }
	public Color(int rgba, boolean hasalpha) {
		if (hasalpha) {
			this.value = rgba;
		} else {
			this.value = -16777216 | rgba;
		}
	}
	public int getRGB() { return value; }
	public int getRed() { return (getRGB() >> 16) & 0xFF; }
	public int getGreen() { return (getRGB() >> 8) & 0xFF; }
	public int getBlue() { return (getRGB() >> 0) & 0xFF; }
	public int getAlpha() { return (getRGB() >> 24) & 0xff; }
	public boolean equals(Object obj) { return obj instanceof Color && ((Color)obj).getRGB() == this.getRGB(); }
	public String toString() { return getClass().getName() + "[r=" + getRed() + ",g=" + getGreen() + ",b=" + getBlue() + "]"; }
	public Color average(Color col) {
		return new Color(
				(this.getRed()+col.getRed())/2,
				(this.getGreen()+col.getGreen())/2,
				(this.getBlue()+col.getBlue())/2
		);
	}
}