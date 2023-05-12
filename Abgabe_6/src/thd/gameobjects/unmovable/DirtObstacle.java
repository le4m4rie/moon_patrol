package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.movable.MoonBaggy;
import thd.gameobjects.movable.Shot;


/**
 * Represent the obstacles the MoonBaggy has to get over.
 */
public class DirtObstacle extends CollidingGameObject {

    /**
     * Creates a new DirtObstacle.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public DirtObstacle(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        this.size = 20;
        this.rotation = 0;
        this.width = 50;
        this.height = 50;
        this.hitBoxOffsets(0, 0, 0, 0);
        position.updateCoordinates(600, 600);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("dirt.png", position.getX(), position.getY(), 1, this.rotation);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Shot) {
            gamePlayManager.addPoints(100);
            gamePlayManager.destroyGameObject(this);
        }
        if (other instanceof MoonBaggy) {
            gamePlayManager.lifeLost();
            gamePlayManager.destroyGameObject(this);
        }
    }
}
