package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;

import java.util.Random;

/**
 * This class represents the shots from the moon baggy used to kill opponents.
 */
class UfoShot extends CollidingGameObject {

    private final Ufo2 ufo2;

    /**
     * Constructs a new Shot with specified size, {@link Position}, rotation, width, height, and speedInPixel.
     *
     * @param gameView        an instance of the GameView class.
     * @param gamePlayManager an instance of the GamePlayManager class.
     * @param ufo2           an instance of the Ufo2 class.
     */
    UfoShot(GameView gameView, GamePlayManager gamePlayManager, Ufo2 ufo2) {
        super(gameView, gamePlayManager);
        size = 30;
        rotation = 0;
        width = 10;
        height = 10;
        speedInPixel = 10;
        this.ufo2 = ufo2;
        hitBoxOffsets(0, 0, 0, 0);
        position.updateCoordinates(ufo2.getPosition());
        targetPosition.updateCoordinates(600, 750);
        distanceToBackground = 6;
    }

    @Override
    public String toString() {
        return "UfoShot: " + position;
    }

    @Override
    public void updatePosition() {
        targetPosition.updateCoordinates(calculateRandomXPosition(), 750);
        position.moveToPosition(targetPosition, speedInPixel);
    }

    private double calculateRandomXPosition() {
        Random rand = new Random();
        return rand.nextDouble(600-300) + 300;
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(BulletBlockImages.UFO_BULLET, position.getX(), position.getY(), 2, 0);
    }

    @Override
    public void updateStatus() {
        if (position.getY() > 720) {
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof MoonBaggy) {
            gamePlayManager.destroyGameObject(this);
        }
    }

}
