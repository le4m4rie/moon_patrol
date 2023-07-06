package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;

/**
 * This class represents the shots from the moon baggy used to kill opponents.
 */
class VerticalShot extends CollidingGameObject {

    private final MoonBaggy moonBaggy;

    /**
     * Constructs a new Shot with specified size, {@link Position}, rotation, width, height, and speedInPixel.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     *
     * @param moonBaggy an instance of the MoonBaggy class.
     */
    VerticalShot(GameView gameView, GamePlayManager gamePlayManager, MoonBaggy moonBaggy) {
        super(gameView, gamePlayManager);
        this.size = 30;
        this.rotation = 0;
        this.width = 10;
        this.height = 10;
        this.speedInPixel = 5;
        this.moonBaggy = moonBaggy;
        this.hitBoxOffsets(0, 0, 0, 0);
        position.updateCoordinates(moonBaggy.getPosition().getX()+25, moonBaggy.getPosition().getY()+10);
        this.distanceToBackground = 5;
    }

    @Override
    public String toString() {
        return "VerticalShot: " + position;
    }

    @Override
    public void updatePosition() {
        position.up(speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(BulletBlockImages.VERTICAL_BULLET, position.getX(), position.getY(), 2, 0);
    }

    @Override
    public void updateStatus() {
        if (position.getY() < 0) {
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Ufo2) {
            gamePlayManager.addPoints(150);
        }
        gamePlayManager.destroyGameObject(this);
    }

}
