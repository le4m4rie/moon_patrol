package thd.gameobjects.movable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.CollidingGameObject;
import thd.gameobjects.base.MainCharacter;
import thd.gameobjects.base.Position;
import thd.gameobjects.unmovable.Crater;
import thd.gameobjects.unmovable.DirtObstacle;
import thd.gameobjects.unmovable.Mine;

import java.awt.*;


/**
 * This class represents the playable game object.
 */
public class MoonBaggy extends CollidingGameObject implements MainCharacter {

    private double scaleFactor;

    /**
     * When the moon baggy has died.
     */
    public boolean isDead;

    /**
     * When the moon baggy should hide (in between level changes).
     */
    public boolean hide;

    private enum State {
        STANDARD("moon-car.png"), DAMAGED("moon-car-damaged.png"), EXPLODING("mooncarexploding.png"), EXPLODED("mooncarexploded.png"), JUMPING("moon-car.png");

        private final String display;


        State(String display) {
            this.display = display;
        }
    }

    private State currentState;

    private String image;

    private JumpingState jumpingState;

    private enum JumpingState {
        JUMPING_1("moon-car.png", 0),
        JUMPING_2("moon-car-jump.png", 90),
        JUMPING_3("moon-car-jump.png", 80),
        JUMPING_4("moon-car-jump.png", 70),
        JUMPING_5("moon-car-jump.png", 0),
        JUMPING_6("moon-car-jump.png", 0),
        JUMPING_7("moon-car-jump.png", 0),
        JUMPING_8("moon-car-jump.png", 0),
        JUMPING_9("moon-car.png", -70),
        JUMPING_10("moon-car.png", -80),
        JUMPING_11("moon-car.png", -90),
        JUMPING_12("moon-car.png", 0);

        private final String display;
        private final int up;

        JumpingState(String display, int up) {
            this.display = display;
            this.up = up;
        }
    }


    /**
     * Constructs a new MoonBaggy with specified size, {@link Position}, rotation, width, height, and speedInPixel.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public MoonBaggy(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        currentState = State.STANDARD;
        size = 1;
        rotation = 0;
        width = 120;
        height = 70;
        speedInPixel = 4;
        scaleFactor = 2;
        hitBoxOffsets(10, 20, -50, -40);
        position.updateCoordinates(100, 560);
        distanceToBackground = 6;
        jumpingState = JumpingState.JUMPING_1;
        isDead = false;
        hide = false;
    }

    @Override
    public String toString() {
        return "MoonBaggy: " + position;
    }


    @Override
    public void addToCanvas() {
        if (!isDead && !hide) {
            gameView.addImageToCanvas(image, position.getX(), position.getY(), this.scaleFactor, this.rotation);
        }
    }

    /**
     * Sets the state back to standart when starting a new game.
     */
    public void revive() {
        currentState = State.STANDARD;
        isDead = false;
    }

    /**
     * Moves the MoonBaggy right according to its speedInPixel.
     */
    public void right() {
        if (!hide) {
            if (position.getX() < (GameView.WIDTH / 2) - 300) {
                position.right(speedInPixel);
            } else {
                gamePlayManager.moveWorldToLeft(speedInPixel);
            }
        }
    }

    /**
     * Lets the MoonBaggy jump.
     */
    public void jump() {
        if (!hide) {
            currentState = State.JUMPING;
        }
    }


    @Override
    public void shoot() {
        if (!isDead && !hide) {
            if (gameView.timer(300, this)) {
                gamePlayManager.spawnGameObject(new Shot(gameView, gamePlayManager, this));
                gameView.playSound("pew.wav", false);
            }
            if (gameView.timer(600, this)) {
                gamePlayManager.spawnGameObject(new VerticalShot(gameView, gamePlayManager, this));
            }
        }
    }

    private void invokeExplosion() {
        currentState = State.EXPLODING;
        if (gameView.timer(30, this)) {
            currentState = State.EXPLODED;
        }
        if (gameView.timer(10, this)) {
            gamePlayManager.destroyGameObject(this);
            this.isDead = true;
        }

    }

    @Override
    public void updateStatus() {
        if (gamePlayManager.getLives() == 0) {
            invokeExplosion();
        }
        switch (currentState) {
            case STANDARD -> {
                this.scaleFactor = 2;
                image = currentState.display;
            }
            case DAMAGED -> {
                image = currentState.display;
                if (gameView.timer(100, this)) {
                    currentState = State.STANDARD;
                }
            }
            case EXPLODING -> {
                this.scaleFactor = 3;
                this.position.updateCoordinates(position.getX(), 530);
                image = currentState.display;
            }
            case EXPLODED -> {
                this.scaleFactor = 3;
                this.position.updateCoordinates(position.getX(), 530);
                image = currentState.display;
            }
            case JUMPING -> {
                image = jumpingState.display;
                if (gameView.timer(45, this)) {
                    switchToNextJumpingState();
                    position.up(jumpingState.up);
                }
            }
        }
    }

    private void switchToNextJumpingState() {
        int nextJumpingState = (jumpingState.ordinal() + 1) % JumpingState.values().length;
        jumpingState = JumpingState.values()[nextJumpingState];
        if (jumpingState == JumpingState.JUMPING_12) {
            currentState = State.STANDARD;
        }
    }

    @Override
    public void reactToCollisionWith(CollidingGameObject other) {
        if (other instanceof UfoShot || other instanceof DroneShot) {
            gamePlayManager.addPoints(-50);
            gameView.playSound("baggygotshot.wav", false);
            currentState = State.DAMAGED;
            gameView.addTextToCanvas("-50", position.getX(), position.getY(), 50, true, Color.RED, 0, "font.ttf");
        }
        if (other instanceof DirtObstacle || other instanceof Drone || other instanceof Crater || other instanceof Mine) {
            currentState = State.DAMAGED;
        }
    }

}

