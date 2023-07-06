
package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.Position;
import thd.gameobjects.unmovable.Crater;
import thd.gameobjects.unmovable.Ground;

import java.util.Random;

/**
 * This class represents the shots from the moon baggy used to kill opponents.
 */
class SpinningUfoShot extends CollidingGameObject {

    private enum State {
        STANDARD("spinningufobullet.png"), EXPLODING("spinningshotexploding.png"), EXPLODED("spinningshotexploded.png");

        private String display;

        State(String display) {
            this.display = display;
        }
    }

    private State currentState;
    private final SpinningUfo spinningUfo;
    private String image;
    private int scale;

    /**
     * Constructs a new Shot with specified size, {@link Position}, rotation, width, height, and speedInPixel.
     *
     * @param gameView        an instance of the GameView class.
     * @param gamePlayManager an instance of the GamePlayManager class.
     * @param spinningUfo           an instance of the Ufo2 class.
     */
    SpinningUfoShot(GameView gameView, GamePlayManager gamePlayManager, SpinningUfo spinningUfo) {
        super(gameView, gamePlayManager);
        this.spinningUfo = spinningUfo;
        currentState = State.STANDARD;
        size = 30;
        rotation = 0;
        width = 10;
        height = 10;
        speedInPixel = 10;
        hitBoxOffsets(0, 0, 0, 0);
        position.updateCoordinates(spinningUfo.getPosition());
        targetPosition.updateCoordinates(600, 750);
        distanceToBackground = 6;
        scale = 2;
    }

    @Override
    public String toString() {
        return "SpinningUfoShot: " + position;
    }

    @Override
    public void updatePosition() {
        targetPosition.updateCoordinates(calculateRandomXPosition(), 750);
        position.moveToPosition(targetPosition, speedInPixel);
    }

    private double calculateRandomXPosition() {
        Random rand = new Random();
        return rand.nextDouble(1200-800) + 800;
    }

    @Override
    public void addToCanvas() {
        gameView.addImageToCanvas(image, position.getX(), position.getY(), scale, this.rotation);
    }

    @Override
    public void updateStatus() {
        switch (currentState) {
            case STANDARD:
            case EXPLODING:
            case EXPLODED:
                image = currentState.display;
                break;
        }
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof Ground) {
            this.scale = 3;
            position.updateCoordinates(position.getX()-60, position.getY()-60);
            currentState = State.EXPLODING;
            gameView.playSound("ufodamage.wav", false);
            if (gameView.timer(80, this)) {
                currentState = State.EXPLODED;
            }
            if (gameView.timer(80, this)) {
                Crater crater = new Crater(gameView, gamePlayManager);
                crater.getPosition().updateCoordinates(position.getX(), 650);
                gamePlayManager.spawnGameObject(crater);
                gamePlayManager.destroyGameObject(this);
            }
        }
    }

}