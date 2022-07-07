import engine.GameEngine;
import renderer.Window;

public class Main {
    public static void main(String[] args) {
        boolean vSync = true;
        GameEngine engine = new GameEngine("GAME", 900.0f, 480.0f, vSync);
        try {
            engine.run();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
