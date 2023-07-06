package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * Represents the Moons surface.
 */
public class Earth extends GameObject {


    /**
     * Creates a new background.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public Earth(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        this.size = 10;
        this.rotation = 0;
        this.width = 50;
        this.height = 50;
        position.updateCoordinates(900, 250);
        this.distanceToBackground = 2;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("earth.png", position.getX(), position.getY(), 8, this.rotation);
    }
}
