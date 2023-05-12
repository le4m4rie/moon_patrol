package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;

/**
 * This class represents the shots from the moon baggy used to kill opponents.
 */
public class UfoShot extends CollidingGameObject {

    private final Ufo2 ufo2;

    /**
     * Constructs a new Shot with specified size, {@link Position}, rotation, width, height, and speedInPixel.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     *
     * @param ufo2 an instance of the Ufo2 class.
     */
    public UfoShot(GameView gameView, GamePlayManager gamePlayManager, Ufo2 ufo2) {
        super(gameView, gamePlayManager);
        this.size = 30;
        this.rotation = 0;
        this.width = 10;
        this.height = 10;
        this.speedInPixel = 9;
        this.ufo2 = ufo2;
        this.hitBoxOffsets(0, 0, 0, 0);
        position.updateCoordinates(ufo2.getPosition());
    }

    @Override
    public String toString() {
        return "UfoShot: " + position;
    }

    @Override
    public void updatePosition() {
        position.down(speedInPixel);
        position.right(speedInPixel);
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

    }

}
