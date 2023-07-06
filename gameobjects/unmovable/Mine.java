package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.ShiftableGameObject;
import thd.gameobjects.movable.MoonBaggy;
import thd.gameobjects.movable.Shot;
import java.util.Random;


/**
 * Represent the obstacles the MoonBaggy has to get over.
 */
public class Mine extends CollidingGameObject implements ShiftableGameObject {

    private String image;
    private double scale;
    private enum State {
        STANDARD("mine.png"), BLINKING("mineblinking.png"), BREAKING("breaking.png"), BROKEN("broken.png");

        private final String display;

        State(String display) {
            this.display = display;
        }
    }

    private State currentState;

    /**
     * Creates a new DirtObstacle.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public Mine(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        currentState = State.STANDARD;
        size = 20;
        rotation = 0;
        width = 50;
        height = 50;
        hitBoxOffsets(20, 30, -10, 0);
        scale = 2;
        position.updateCoordinates(randomXCoordinate(), 600);
        distanceToBackground = 6;
    }

    private double randomXCoordinate() {
        Random r = new Random();
        return r.nextDouble(1300-1000) + 1000;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(image, position.getX(), position.getY(), scale, this.rotation);
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Shot) {
            currentState = State.BREAKING;
            gameView.playSound("obstacledestroy.wav", false);
        }
        if (other instanceof MoonBaggy) {
            gamePlayManager.lifeLost();
            gameView.playSound("baggygotshot.wav", false);
            gamePlayManager.destroyGameObject(this);
        }
        if (other instanceof Crater) {
            gamePlayManager.destroyGameObject(this);
        }
    }


    @Override
    public void updateStatus() {
        if (gameView.timer(500, this)) {
            currentState = State.BLINKING;
        }
        switch (currentState) {
            case STANDARD -> {
                image = currentState.display;
            }
            case BLINKING -> {
                this.scale = 2;
                image = currentState.display;
                if (gameView.timer(100, this)) {
                    currentState = State.STANDARD;
                }
            }
            case BREAKING -> {
                this.scale = 1;
                image = currentState.display;
                if (gameView.timer(20, this)) {
                    currentState = State.BROKEN;
                }
            }
            case BROKEN -> {
                this.scale = 1;
                image = currentState.display;
                if (gameView.timer(20, this)) {
                    gamePlayManager.destroyGameObject(this);
                }
            }
        }
    }
}
