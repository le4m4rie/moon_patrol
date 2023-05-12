package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

/**
 * Represents how many lives the player has left.
 */
public class Score extends GameObject {

    /**
     * Creates a new Lives indicator.
     *
     * @param gameView an instance of the GameView object.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public Score(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        this.size = 20;
        this.rotation = 0;
        this.width = 10;
        this.height = 7;
        position.updateCoordinates(40, 40);
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas("P1 " + String.valueOf(gamePlayManager.getPoints()), position.getX(), position.getY(), 20, true, Color.WHITE, 0);
    }

}

