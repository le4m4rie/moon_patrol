package thd.gameobjects.unmovable;
import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;

/**
 * Represents the Moons surface.
 */
public class Ground extends CollidingGameObject {

    private final GroundBlockImages groundBlockImages;

    /**
     * Creates a new ground.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public Ground(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 30;
        rotation = 0;
        width = 1200;
        height = 200;
        position.updateCoordinates(0, 650);
        distanceToBackground = 5;
        groundBlockImages = new GroundBlockImages();
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

    @Override
    public void addToCanvas() {
        String ground = groundBlockImages.GROUND;
        gameView.addBlockImageToCanvas(ground, position.getX(), position.getY(), 15, this.rotation);
    }
}
