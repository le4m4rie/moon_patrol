package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;


/**
 * This class represents the shots from the moon baggy used to kill opponents.
 */
class DroneShot extends CollidingGameObject {

    private final Drone drone;

    /**
     * Constructs a new Shot with specified size, {@link Position}, rotation, width, height, and speedInPixel.
     *
     * @param gameView        an instance of the GameView class.
     * @param gamePlayManager an instance of the GamePlayManager class.
     * @param drone          an instance of the drone class.
     */
    DroneShot(GameView gameView, GamePlayManager gamePlayManager, Drone drone) {
        super(gameView, gamePlayManager);
        size = 30;
        rotation = 0;
        width = 10;
        height = 10;
        speedInPixel = 10;
        this.drone = drone;
        hitBoxOffsets(0, 0, 0, 0);
        position.updateCoordinates(drone.getPosition());
        targetPosition.updateCoordinates(600, 750);
        distanceToBackground = 6;
    }

    @Override
    public String toString() {
        return "DroneSHot: " + position;
    }

    @Override
    public void updatePosition() {
        position.left(speedInPixel);
    }


    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(BulletBlockImages.UFO_BULLET, position.getX(), position.getY(), 2, 0);
    }

    @Override
    public void updateStatus() {
        if (position.getX() < 0) {
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
