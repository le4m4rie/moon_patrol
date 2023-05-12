package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.MainCharacter;
import thd.gameobjects.base.Position;
import thd.gameobjects.unmovable.DirtObstacle;

/**
 * This class represents the playable game object.
 */
public class MoonBaggy extends CollidingGameObject implements MainCharacter {

    private double scaleFactor;

    private int shotDurationInMilliseconds;

    private final DirtObstacle dirtObstacle;


    /**
     * Constructs a new Shot with specified size, {@link Position}, rotation, width, height, and speedInPixel.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public MoonBaggy(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        this.size = 1;
        this.rotation = 0;
        this.width = 120;
        this.height = 70;
        this.speedInPixel = 2;
        this.scaleFactor = 0.3;
        this.shotDurationInMilliseconds = 300;
        this.hitBoxOffsets(0, 20, 0, 0);
        this.dirtObstacle = new DirtObstacle(gameView, gamePlayManager);
        position.updateCoordinates(100, 560);
    }

    @Override
    public String toString() {
        return "MoonBaggy: " + position;
    }


    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas("moon-car.png", position.getX(), position.getY(), this.scaleFactor, this.rotation);
    }

    private void left() {
        position.left(speedInPixel);
    }

    /**
     * Moves the MoonBaggy right according to its speedInPixel.
     */
    public void right() {
        position.right(speedInPixel);
        if (position.getX() == 1200) {
            this.left();
            if (collidesWith(dirtObstacle)) {
                position.left(speedInPixel);
            }
        }
    }
    private void up() {
        position.up(speedInPixel);
    }
    private void down() {
        position.down(speedInPixel);
    }

    @Override
    public void shoot() {
            if (gameView.timer(300, this)) {
                gamePlayManager.spawnGameObject(new Shot(gameView, gamePlayManager, this));
                gamePlayManager.spawnGameObject(new VerticalShot(gameView, gamePlayManager, this));
            }
    }

    @Override
    public void updateStatus() {
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
    }

}

