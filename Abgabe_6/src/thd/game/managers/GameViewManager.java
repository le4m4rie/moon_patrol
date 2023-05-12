package thd.game.managers;

import thd.game.utilities.GameView;

import java.awt.*;

/**
 * This class manages the visuals of the game and inherits from the {@link GameView} class.
 */
public class GameViewManager extends GameView {
    private GameManager gameManager;

    @Override
    public void initialize() {
        this.gameManager = new GameManager(this);
        setColorForBlockImage('D', Color.GRAY.darker());
        setColorForBlockImage('G', Color.GRAY.brighter());
        setColorForBlockImage('P', new Color(95, 0, 160));
        setColorForBlockImage('R', new Color(180, 103, 167));
        setColorForBlockImage('K', new Color(58, 253, 167));
        setWindowTitle("Moon Patrol");
        setStatusText("Lea Wagner - Java Programmierung SS 2023");
        setWindowIcon("moon-car.png");
    }

    @Override
    public void gameLoop() {
        gameManager.gameLoopUpdate();
        //addBlockImageToCanvas(lives, 1210, 30, 2, 0);
        //addTextToCanvas("3", 1225, 60, 20, true, Color.WHITE, 0);
        //addTextToCanvas("HI 000800", 30, 30, 20, true, Color.WHITE, 0);
        //addTextToCanvas("P1 000000", 30, 60, 20, true, Color.WHITE, 0);
        //addTextToCanvas("POINT A", 450, 30, 20, true, Color.WHITE, 0);
        //addTextToCanvas("TIME 000", 750, 30, 20, true, Color.WHITE, 0);
        //addTextToCanvas("A     E     J     O     T     Z", 450, 60, 20, true, Color.WHITE, 0);
        //addOvalToCanvas(455, 72, 20, 20, 2, false, Color.GREEN);
        //addImageToCanvas("moon-car.png", 20, 600, 0.3, 0);
        //addImageToCanvas("dirt.png", 300, 650, 0.5, 0);

    }
}
