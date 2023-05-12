package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

import java.awt.*;

/**
 * This class represents the time (panel) in the game which is measured to determine
 * how long it takes the player to get through a level.
 */
public class Time extends GameObject {

    /**
     * Constructs a new Time (panel) with specified size, {@link Position}, rotation, width and height.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public Time(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        this.size = 30;
        this.width = 150;
        this.rotation = 0;
        this.height = 33;
        position.updateCoordinates(GameView.WIDTH-width, 10);
    }

    @Override
    public String toString() {
        return getClass().getName() + ":" + position.toString();
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas("TIME " + gameView.gameTimeInMilliseconds()/1000, position.getX(), position.getY(), 20, true, Color.WHITE, 0);
    }
}
