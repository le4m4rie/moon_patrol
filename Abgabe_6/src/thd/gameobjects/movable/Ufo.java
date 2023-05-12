package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;
import java.util.Random;

/**
 * This class represents the UFO enemies of the game.
 */
public class Ufo extends CollidingGameObject {
        private final LeftRightMovementPattern leftRightMovementPattern;

    /**
     * Constructs a new UFO with specified size, {@link Position}, rotation, width, height, and speedInPixel.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public Ufo(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        this.size = 10;
        this.rotation = 0;
        this.width = 100;
        this.height = 30;
        this.speedInPixel = 5;
        this.leftRightMovementPattern = new LeftRightMovementPattern();
        this.hitBoxOffsets(0, 0, 0, 0);
        position.updateCoordinates(leftRightMovementPattern.startPosition());
        targetPosition.updateCoordinates(leftRightMovementPattern.nextTargetPosition());
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
            targetPosition.updateCoordinates(leftRightMovementPattern.nextTargetPosition());
        }
        position.moveToPosition(targetPosition, speedInPixel);
        }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("ufo1.png", position.getX(), position.getY(), 0.07, this.rotation);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof VerticalShot) {
            gamePlayManager.addPoints(200);
            gamePlayManager.destroyGameObject(this);
        }
    }
}
