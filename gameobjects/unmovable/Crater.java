package thd.gameobjects.unmovable;
import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.movable.MoonBaggy;
import java.util.Random;


/**
 * Represent the crater the MoonBaggy has to get over.
 */
public class Crater extends CollidingGameObject implements ShiftableGameObject {

    private double scale;
    private boolean isHitable;

    /**
     * Creates a new Crater.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public Crater(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 20;
        rotation = 0;
        width = 50;
        height = 50;
        hitBoxOffsets(15, -5, -30, 5);
        scale = 2;
        position.updateCoordinates(randomXCoordinate(), 650);
        distanceToBackground = 6;
        isHitable = true;
    }

    private double randomXCoordinate() {
        Random r = new Random();
        return r.nextDouble(1300-1000) + 1000;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("crater.png", position.getX(), position.getY(), scale, this.rotation);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (isHitable) {
            if (other instanceof MoonBaggy) {
                isHitable = false;
                gamePlayManager.lifeLost();
                gameView.playSound("baggygotshot.wav", false);
            }
        }
        if (other instanceof DirtObstacle || other instanceof Mine) {
            gamePlayManager.destroyGameObject(this);
        }
    }


    @Override
    public void updateStatus() {
        if (position.getX() < 0) {
            gamePlayManager.destroyGameObject(this);
        }
    }
}
