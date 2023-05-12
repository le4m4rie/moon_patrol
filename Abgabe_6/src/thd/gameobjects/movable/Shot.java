package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;

/**
 * This class represents the shots from the moon baggy used to kill opponents.
 */
public class Shot extends CollidingGameObject {

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
    public Shot(GameView gameView, GamePlayManager gamePlayManager, MoonBaggy moonBaggy) {
        super(gameView, gamePlayManager);
        this.size = 30;
        this.rotation = 0;
        this.width = 10;
        this.height = 10;
        this.speedInPixel = 5;
        this.moonBaggy = moonBaggy;
        this.hitBoxOffsets(0, 0, 0, 0);
        position.updateCoordinates(moonBaggy.getPosition().getX()+110, moonBaggy.getPosition().getY()+40);

    }

    @Override
    public String toString() {
        return "Shot: " + position;
    }

    @Override
    public void updatePosition() {
        position.right(speedInPixel);
    }

    @Override
    public void addToCanvas() {
        gameView.addBlockImageToCanvas(BulletBlockImages.BULLET, position.getX(), position.getY(), 2, 0);
    }

    @Override
    public void updateStatus() {
        if (position.getX() > 1280) {
            gamePlayManager.destroyGameObject(this);
        }
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {

    }

}
