package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.ShiftableGameObject;

/**
 * Represents mountains.
 */
public class Mountains2 extends GameObject implements ShiftableGameObject {


    /**
     * Creates new mountains.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public Mountains2(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        this.size = 100;
        this.rotation = 0;
        this.width = 1200;
        this.height = 700;
        position.updateCoordinates(0, 500);
        this.distanceToBackground = 3;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("mountains2.png", position.getX(), position.getY(), 11, this.rotation);
    }


}
