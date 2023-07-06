package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * Represents the Moons surface.
 */
public class Background2 extends GameObject {


    /**
     * Creates a new background.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public Background2(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        this.size = 100;
        this.rotation = 0;
        this.width = 1200;
        this.height = 700;
        position.updateCoordinates(0, 0);
        this.distanceToBackground = 1;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("background2.png", position.getX(), position.getY(), 15, this.rotation);
    }
}
