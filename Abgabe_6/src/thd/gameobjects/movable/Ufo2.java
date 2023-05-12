package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;

import java.util.Random;

/**
 * This class represents the UFO enemies of the game.
 */
public class Ufo2 extends CollidingGameObject {
    private final UpDownMovementPattern upDownMovementPattern;

    /**
     * Constructs a new UFO with specified size, {@link Position}, rotation, width, height, and speedInPixel.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public Ufo2(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        this.size = 30;
        this.rotation = 0;
        this.width = 100;
        this.height = 30;
        this.speedInPixel = 7;
        this.upDownMovementPattern = new UpDownMovementPattern();
        this.hitBoxOffsets(0, 0, 0, 0);
        position.updateCoordinates(upDownMovementPattern.startPosition().getX() + randomPixelOffset(), upDownMovementPattern.startPosition().getY() + randomPixelOffset());
        targetPosition.updateCoordinates(upDownMovementPattern.nextTargetPosition().getX() + randomPixelOffset(), upDownMovementPattern.nextTargetPosition().getY() + randomPixelOffset());
    }

    private int randomPixelOffset() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(20) + 1;
        return randomNumber;
    }

    @Override
    public String toString() {
        return getClass().getName() + ":" + position.toString();
    }

    @Override
    public void updatePosition() {
        if (position.similarTo(targetPosition)) {
            targetPosition.updateCoordinates(upDownMovementPattern.nextTargetPosition());
        }
        position.moveToPosition(targetPosition, speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("ufo2.png", position.getX(), position.getY(), 0.06, this.rotation);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof VerticalShot) {
            gamePlayManager.addPoints(150);
            gamePlayManager.destroyGameObject(this);
        }
    }
}
