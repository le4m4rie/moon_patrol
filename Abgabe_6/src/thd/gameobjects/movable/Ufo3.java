package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

/**
 * This class represents the UFO enemies of the game.
 */
class Ufo3 extends GameObject {
    private final LeftRightMovementPattern leftRightMovementPattern;

    /**
     * Constructs a new UFO with specified size, {@link Position}, rotation, width, height, and speedInPixel.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    Ufo3(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        this.size = 30;
        this.rotation = 0;
        this.width = 150;
        this.height = 33;
        this.speedInPixel = 8;
        this.leftRightMovementPattern = new LeftRightMovementPattern();
        position.updateCoordinates(leftRightMovementPattern.startPosition());
        targetPosition.updateCoordinates(leftRightMovementPattern.nextTargetPosition());
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
        //gameView.addImageToCanvas("ufo3.png", position.getX(), position.getY(), 0.1, this.rotation);
    }
}
