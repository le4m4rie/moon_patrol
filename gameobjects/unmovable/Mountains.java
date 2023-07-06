package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.ShiftableGameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Moons surface.
 */
class Mountains extends GameObject implements ShiftableGameObject {

    private List<Mountains> mountains;

    /**
     * Creates new mountains.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    Mountains(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 100;
        rotation = 0;
        width = 1200;
        height = 700;
        position.updateCoordinates(0, 500);
        distanceToBackground = 3;
        mountains = new ArrayList<>();
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("mountains.png", position.getX(), position.getY(), 11, this.rotation);
    }

    @Override
    public void updateStatus() {
        if (this.position.getX() + this.getWidth() < 0) {
            this.position.updateCoordinates(1300, 500);
        }
    }


}
