package Liff.Renderer;

public class Vertex {
    public float x, y, z;
    public float u, v;

    @Override
    public String toString() {
        return "Vertex{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", u=" + u +
                ", v=" + v +
                '}';
    }

    public Vertex(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.u = 0.0f;
        this.v = 0.0f;
    }
    public Vertex(float x, float y, float z, float u, float v) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.u = u;
        this.v = v;
    }
}
