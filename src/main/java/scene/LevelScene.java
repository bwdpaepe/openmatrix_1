package scene;

import domain.GameObject;
import org.joml.Vector3f;
import renderer.Mesh;

import java.util.ArrayList;
import java.util.List;

public class LevelScene extends Scene{

    private int scaleInc = 0;

    private List<GameObject> gameObjects;

    @Override
    public void init(float windowWidth, float windowHeight) throws Exception {
        // load game objects
        gameObjects = new ArrayList<>();

        float[] quadVertex = {-0.5f,  0.5f, -10.5f,
                -0.5f, -0.5f, -10.5f,
                0.5f, -0.5f, -10.5f,
                0.5f,  0.5f, -10.5f
        };

        int[] quadIndex = {
                0, 1, 3, 3, 1, 2
        };

        float[] quadColor = {1.0f, 0.0f, 0.0f, 1.0f,
                0.0f, 1.0f, 0.0f, 1.0f,
                0.0f, 0.0f, 1.0f, 1.0f,
                1.0f, 1.0f, 0.0f, 1.0f
        };

        this.gameObjects.add(new GameObject(new Mesh(quadVertex, quadColor, quadIndex)));

        this.renderer.init(windowWidth, windowHeight);
    }

    @Override
    public void input() {

    }

    @Override
    public void update(float dt) {
        /*for(GameObject go: this.gameObjects) {
            // Update scale
            float scale = go.getScale();
            scale += scaleInc * 0.05f;
            if ( scale < 0 ) {
                scale = 0;
            }
            go.setScale(scale);
            // Update rotation angle
            float rotation = go.getRotation().x + 1.5f;
            if ( rotation > 360 ) {
                rotation = 0;
            }
            go.setRotation(new Vector3f(rotation, rotation, rotation));
        }*/

    }

    @Override
    public void render() {
        this.renderer.render(this.gameObjects);
    }

    @Override
    public void cleanup() {
        for(GameObject go: gameObjects){
            go.getMesh().cleanup();
        }
        this.renderer.cleanup();
    }
}
