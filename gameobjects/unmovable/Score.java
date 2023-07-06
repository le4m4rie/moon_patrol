package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

/**
 * Represents how many lives the player has left.
 */
public class Score extends GameObject {

    private int score;

    /**
     * Creates a new Lives indicator.
     *
     * @param gameView an instance of the GameView object.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public Score(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 20;
        rotation = 0;
        width = 10;
        height = 7;
        position.updateCoordinates(40, 40);
        distanceToBackground = 6;
        score = 0;
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas("P1 " + String.valueOf(score), position.getX(), position.getY(), 20, true, Color.WHITE, 0, "font.ttf");
    }

    /**
     * Sets the current score.
     *
     * @param score The current score.
     */
    public void setScore(int score) {
        this.score = score;
    }

}

