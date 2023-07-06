package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

import java.awt.*;
import java.util.Random;

/**
 * Represents the display that warns the player from any danger.
 */
public class CautionDisplay extends GameObject {

    private int randomY;

    private enum State {
        STANDARD(), BLINKING();
    }

    private State currentState;

    /**
     * Constructs a new Caution (panel) with specified size, {@link Position}, rotation, width and height.
     *
     * @param gameView        an instance of the GameView class.
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public CautionDisplay(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        position.updateCoordinates(370, 20);
        distanceToBackground = 6;
        currentState = State.STANDARD;
    }

    @Override
    public void addToCanvas() {
        gameView.addOvalToCanvas(950, 20, 18, 18, 4, false, Color.WHITE);
        gameView.addOvalToCanvas(950, 40, 18, 18, 4, false, Color.WHITE);
        gameView.addOvalToCanvas(950, 60, 18, 18, 4, false, Color.WHITE);
    }

    private int randomBlink() {
        int int1 = 20;
        int int2 = 40;
        int int3 = 60;
        Random rand = new Random();
        int randomNumber = rand.nextInt(3 - 1 + 1) + 1;
        switch (randomNumber) {
            case 1: return int1;
            case 2: return int2;
            case 3: return int3;
            default: throw new IllegalArgumentException("Invalid random number: " + randomNumber);
        }
    }

    @Override
    public void updateStatus() {
        if (gameView.timer(5000, this)) {
            randomY = randomBlink();
            currentState = State.BLINKING;
        }
        switch (currentState) {
            case BLINKING -> {
                gameView.addOvalToCanvas(950, randomY, 20, 20, 2, true, Color.RED);
                gameView.addTextToCanvas("CAUTION!", 980, 30, 12, true, Color.RED, 0, "font.ttf");
                if (gameView.timer(2000, this)) {
                    currentState = State.STANDARD;
                }
            }
        }

    }

}
