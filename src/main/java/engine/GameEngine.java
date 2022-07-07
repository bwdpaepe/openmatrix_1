package engine;

import org.lwjgl.Version;
import renderer.Window;
import scene.LevelScene;
import scene.Scene;
import util.Timer;

import static org.lwjgl.opengl.GL11.glViewport;

public class GameEngine implements Runnable{
    public static final int TARGET_FPS = 75;
    public static final int TARGET_UPS = 30;
    private final Window window;
    private final Timer timer;

    private static Scene currentScene;

    public GameEngine(String windowTitle, float width, float height, boolean vSync) {
        this.window = new Window(windowTitle, width, height, vSync);
        this.timer = new Timer();
    }

    @Override
    public void run() {
        try {
            System.out.println("Hello LWJGL " + Version.getVersion() + "!");
            this.init();
            this.loop();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            this.cleanup();
        }
    }

    private void init() throws Exception {
        this.window.init();
        this.timer.init();

        this.changeScene(0);
    }

    private void loop() {
        float elapsedTime;
        float accumulator = 0f;
        float interval = 1f / TARGET_UPS;

        boolean running = true;
        while (running && !window.windowShouldClose()) {
            elapsedTime = timer.getElapsedTime();
            accumulator += elapsedTime;

            // handle input
            this.input();

            while (accumulator >= interval) {
                // update game state
                this.update(interval);
                accumulator -= interval;
            }

            this.render();

            if (!window.isvSync()) {
                this.sync();
            }
        }
    }

    private void cleanup() {
        this.currentScene.cleanup();
        this.window.cleanup();

    }

    private void sync() {
        float loopSlot = 1f / TARGET_FPS;
        double endTime = timer.getLastLoopTime() + loopSlot;
        while (timer.getTime() < endTime) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ie) {
            }
        }
    }

    private void input() {
        this.currentScene.input();
    }

    private void update(float interval) {
        this.currentScene.update(interval);
    }

    private void render() {
        //this.dc.render(currentScene);
        //currentScene.render();
        if (this.window.isResized()) {
            glViewport(0, 0, (int)this.window.getWidth(), (int)this.window.getHeight());
            this.window.setResized(false);
        }
        this.window.clear();
        // render game objects
        currentScene.render();
        this.window.loop();
    }

    public void changeScene(int newScene) throws Exception {
        switch (newScene) {
            case 0:
                currentScene = new LevelScene();
                currentScene.init(this.window.getWidth(), this.window.getHeight());
                break;
            default:
                assert false : "Unknown scene '" + newScene + "'";
                break;
        }
    }
}
