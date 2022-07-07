package domain;

import org.joml.Vector3f;
import renderer.Mesh;

public class GameObject {

    private final Mesh mesh;

    private Vector3f position;

    private float scale;

    private Vector3f rotation;


    public GameObject(Mesh mesh) {
        this.mesh = mesh;
        position = new Vector3f();
        scale = 1.0f;
        rotation = new Vector3f();
    }

    public Mesh getMesh() {
        return mesh;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }
}
