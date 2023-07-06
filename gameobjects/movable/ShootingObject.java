package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;

/**
 * Implements the shooting enemies of the game.
 */
class ShootingObject extends CollidingGameObject {

    protected String image;

    protected double scale;

    protected int shotIntervalInMilliSeconds;


    protected ShootingObject(GameView gameView, GamePlayManager gamePlayManager, int speedInPixel) {
        super(gameView, gamePlayManager);
        this.speedInPixel = speedInPixel;
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof MoonBaggy) {
            gamePlayManager.lifeLost();
            gameView.playSound("baggygotshot.wav", false);
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(image, position.getX(), position.getY(), scale, this.rotation);
    }

    @Override
    public String toString() {
        return getClass().getName() + ":" + position.toString();
    }


    @Override
    public void updateStatus() {
    }
}
