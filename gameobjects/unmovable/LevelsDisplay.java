package thd.gameobjects.unmovable;
import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

import java.awt.*;

/**
 * This class represents the levels (panel) in the game which represents the stations A, E, J, O, T, Z.
 */
public class LevelsDisplay extends GameObject {

    /**
     * Constructs a new Levels (panel) with specified size, {@link Position}, rotation, width and height.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public LevelsDisplay(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        position.updateCoordinates(370, 20);
        distanceToBackground = 6;
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas("A     E     J     O     T     Z", position.getX(), position.getY(), 15, true, Color.WHITE, 0, "font.ttf");

    }
}