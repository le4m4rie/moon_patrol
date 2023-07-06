package thd.gameobjects.movable;
import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;
import java.util.Random;

/**
 * This class represents the drone enemies of the game.
 */
public class Drone extends ShootingObject {

    private enum State {
        STANDARD("drone.png"), EXPLODING("ufoexploding.png"), EXPLODED("ufoexploded.png");

        private final String display;

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
     */
    public Drone(GameView gameView, GamePlayManager gamePlayManager, int speedInPixel) {
        super(gameView, gamePlayManager, speedInPixel);
        currentState = State.STANDARD;
        this.speedInPixel = speedInPixel;
        shotIntervalInMilliSeconds = 2000;
        size = 30;
        rotation = 0;
        width = 100;
        height = 30;
        scale = 2;
        hitBoxOffsets(0, 0, -10, 0);
        position.updateCoordinates(randomXCoordinate(), 620);
        distanceToBackground = 7;
    }

    private double randomXCoordinate() {
        Random r = new Random();
        return r.nextDouble(1300-1000) + 1000;
    }

    @Override
    public void updatePosition() {
        position.left(speedInPixel);
    }

    private void shoot() {
        DroneShot droneShot = new DroneShot(gameView, gamePlayManager, this);
        gamePlayManager.spawnGameObject(droneShot);
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
                position.updateCoordinates(position.getX()-10, position.getY()-5);
                image = currentState.display;
                if (gameView.timer(20, this)) {
                    currentState = State.EXPLODED;
                }
            }
            case EXPLODED -> {
                this.scale = 3;
                position.updateCoordinates(position.getX()-10, position.getY()-5);
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

