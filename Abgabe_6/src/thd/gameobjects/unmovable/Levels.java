package thd.gameobjects.unmovable;


import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.base.Position;

import java.awt.*;

/**
 * This class represents the levels (panel) in the game which represents the stations A, E, J, O, T, Z.
 */
public class Levels extends GameObject {

    /**
     * Constructs a new Levels (panel) with specified size, {@link Position}, rotation, width and height.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public Levels(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        position.updateCoordinates(450, 10);
    }

    @Override
    public void addToCanvas() {
        gameView.addTextToCanvas("A     E     J     O     T     Z", position.getX(), position.getY(), 20, true, Color.WHITE, 0);
        gameView.addOvalToCanvas(455, 22, 20, 20, 2, false, Color.GREEN);
    }
}