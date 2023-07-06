package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;

import java.util.Random;

/**
 * This class represents the UFO enemies of the game.
 */
public class Ufo2 extends ShootingObject {
    private final UpDownMovementPattern upDownMovementPattern;

    private enum State {
        STANDARD("ufo2.png"), EXPLODING("ufoexploding.png"), EXPLODED("ufoexploded.png");

        private String display;

        State(String display) {
            this.display = display;
        }
    }
    private State currentState;

    /**
     * Constructs a new UFO with specified size, {@link Position}, rotation, width, height, and speedInPixel.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     *
     * @param speedInPixel the speed in pixel.
     *
     * @param shotIntervalInMilliseconds the shot interval.
     */
    public Ufo2(GameView gameView, GamePlayManager gamePlayManager, int speedInPixel, int shotIntervalInMilliseconds) {
        super(gameView, gamePlayManager, speedInPixel);
        currentState = State.STANDARD;
        this.speedInPixel = speedInPixel;
        this.shotIntervalInMilliSeconds = shotIntervalInMilliseconds;
        size = 30;
        rotation = 0;
        width = 100;
        height = 30;
        scale = 0.04;
        upDownMovementPattern = new UpDownMovementPattern();
        hitBoxOffsets(0, 0, -10, 0);
        position.updateCoordinates(upDownMovementPattern.startPosition().getX() + randomPixelOffset(), upDownMovementPattern.startPosition().getY() + randomPixelOffset());
        targetPosition.updateCoordinates(upDownMovementPattern.nextTargetPosition().getX() + randomPixelOffset(), upDownMovementPattern.nextTargetPosition().getY() + randomPixelOffset());
        distanceToBackground = 4;
    }

    private int randomPixelOffset() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(20) + 1;
        return randomNumber;
    }

    @Override
    public void updatePosition() {
        if (position.similarTo(targetPosition)) {
            targetPosition.updateCoordinates(upDownMovementPattern.nextTargetPosition());
        }
        position.moveToPosition(targetPosition, speedInPixel);
    }

    private void shoot() {
        UfoShot ufoShot = new UfoShot(gameView, gamePlayManager, this);
        gamePlayManager.spawnGameObject(ufoShot);
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(image, position.getX(), position.getY(), scale, this.rotation);
    }

    @Override
    public void updateStatus() {
        if (gameView.timer(shotIntervalInMilliSeconds, this)) {
            shoot();
        }
        switch (currentState) {
            case STANDARD -> {
                image = currentState.display;
            }
            case EXPLODING -> {
                this.scale = 3;
                image = currentState.display;
                if (gameView.timer(20, this)) {
                    currentState = State.EXPLODED;
                }
            }
            case EXPLODED -> {
                this.scale = 3;
                image = currentState.display;
                if (gameView.timer(20, this)) {
                    gamePlayManager.destroyGameObject(this);
                }
            }
        }
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        super.reactToCollisionWith(other);
        if (other instanceof Shot || other instanceof VerticalShot) {
            currentState = State.EXPLODING;
            gameView.playSound("ufodamage.wav", false);
        }
    }
}
