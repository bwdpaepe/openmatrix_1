package renderer;

import domain.GameObject;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import util.Utils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Renderer {

    private ShaderProgram shaderProgram;

    /**
     * Field of View in Radians
     */
    private static final float FOV = (float) Math.toRadians(60.0f);

    private static final float Z_NEAR = 0.01f;

    private static final float Z_FAR = 1000.f;

    private float windowWidth, windowHeight;

    private Transformation transformation;

    public Renderer() {
        transformation = new Transformation();
    }

    public void init(float windowWidth, float windowHeight) throws Exception {
        this.shaderProgram = new ShaderProgram();
        this.shaderProgram.createVertexShader(Utils.loadResource("/vertex.glsl"));
        this.shaderProgram.createFragmentShader(Utils.loadResource("/fragment.glsl"));
        this.shaderProgram.link();
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
    }

    public void render(List<GameObject> gameObjects) {
        shaderProgram.bind();

        // projection matrix
        Matrix4f projectionMatrix = transformation.getProjectionMatrix(this.FOV, this.windowWidth, this.windowHeight, this.Z_NEAR, this.Z_FAR );
        shaderProgram.uploadMat4f("projectionMatrix", projectionMatrix);

        // render each game object
        for(GameObject go: gameObjects) {
            // world matrix
            Matrix4f worldMatrix = transformation.getWorldMatrix(
                    go.getPosition(),
                    go.getRotation(),
                    go.getScale());
            shaderProgram.uploadMat4f("worldMatrix", worldMatrix);
            go.getMesh().render();
        }

        shaderProgram.unbind();
    }

    public void cleanup() {
        if (shaderProgram != null) {
            shaderProgram.cleanup();
        }
    }

}
