package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.ShiftableGameObject;


/**
 * The mountains in the game.
 */
public class Mount extends GameObject implements ShiftableGameObject {


    private double scale;

    private String pngFile;

    /**
     * Creates a new Mountain.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     *
     * @param pngFile the pngFile of the corresponding mountain.
     */
    public Mount(GameView gameView, GamePlayManager gamePlayManager, String pngFile) {
        super(gameView, gamePlayManager);
        size = 20;
        rotation = 0;
        width = 1200;
        height = 200;
        scale = 15;
        position.updateCoordinates(0, 20);
        distanceToBackground = 3;
        this.pngFile = pngFile;
    }


    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(pngFile, position.getX(), position.getY(), scale, this.rotation);
    }


    @Override
    public void updateStatus() {
        if (this.position.getX() + this.getWidth() < -100) {
            gamePlayManager.destroyGameObject(this);
        }
    }
}
