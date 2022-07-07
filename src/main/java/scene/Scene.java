package scene;

import renderer.Renderer;

public abstract class Scene {

    protected Renderer renderer = new Renderer();

    public void init(float windowWidth, float windowHeight) throws Exception {};

    public abstract void input();

    public abstract void update(float dt);

    public abstract void render();

    public abstract void cleanup();
}
