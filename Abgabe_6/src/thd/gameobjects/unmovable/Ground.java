package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * Represents the Moons surface.
 */
public class Ground extends GameObject {

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
        this.size = 20;
        this.rotation = 0;
        this.width = 1200;
        this.height = 200;
        position.updateCoordinates(0, 650);
        this.groundBlockImages = new GroundBlockImages();
    }

    @Override
    public void addToCanvas() {
        String ground = groundBlockImages.GROUND;
        gameView.addBlockImageToCanvas(ground, position.getX(), position.getY(), 20, this.rotation);
    }
}
