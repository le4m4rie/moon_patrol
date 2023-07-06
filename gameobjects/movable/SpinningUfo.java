package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;

import java.util.Random;

/**
 * This class represents the UFO enemies of the game.
 */
public class SpinningUfo extends ShootingObject {
    private final UpDownMovementPattern upDownMovementPattern;


    private enum State {
        SPIN1("ufospin1.png"), SPIN2("ufospin2.png"), SPIN3("ufospin3.png");

        private String display;

        State(String display) {
            this.display = display;
        }
    }

    private enum ExplodingState {
        STANDARD("ufospin1.png"), EXPLODING("ufoexploding.png"), EXPLODED("ufoexploded.png");


        private String display;

        ExplodingState(String display) {
            this.display = display;
        }
    }
    private State currentState;
    private ExplodingState explodingState;

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
    public SpinningUfo(GameView gameView, GamePlayManager gamePlayManager, int speedInPixel, int shotIntervalInMilliseconds) {
        super(gameView, gamePlayManager, speedInPixel);
        currentState = State.SPIN1;
        explodingState = ExplodingState.STANDARD;
        this.speedInPixel = speedInPixel;
        this.shotIntervalInMilliSeconds = shotIntervalInMilliseconds;
        size = 30;
        rotation = 0;
        width = 100;
        height = 30;
        scale = 1;
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

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(image, position.getX(), position.getY(), scale, this.rotation);
    }

    private void shoot() {
        SpinningUfoShot spinningUfoShot = new SpinningUfoShot(gameView, gamePlayManager, this);
        gamePlayManager.spawnGameObject(spinningUfoShot);
    }

    @Override
    public void updateStatus() {
        if (gameView.timer(8000, this)) {
            shoot();
        }
        if (gameView.timer(100, this)) {
           switchToNextState();
        }
        switch (currentState) {
            case SPIN1 -> {
                image = currentState.display;
            }
            case SPIN2 -> {
                image = currentState.display;
            }
            case SPIN3 -> {
                image = currentState.display;
                }
            }
        switch (explodingState) {
            case EXPLODING -> {
                image = explodingState.display;
                if (gameView.timer(50, this)) {
                    explodingState = ExplodingState.EXPLODED;
                }
            }
            case EXPLODED -> {
                image = explodingState.display;
                if (gameView.timer(50, this)) {
                    gamePlayManager.destroyGameObject(this);
                }
            }
        }
        }

    private void switchToNextState() {
        int nextState = (currentState.ordinal() + 1) % SpinningUfo.State.values().length;
        currentState = SpinningUfo.State.values()[nextState];
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        super.reactToCollisionWith(other);
        if (other instanceof Shot || other instanceof VerticalShot) {
            explodingState = ExplodingState.EXPLODING;
            gameView.playSound("ufodamage.wav", false);
        }
    }
}
