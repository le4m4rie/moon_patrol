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
        setColorForBlockImage('G', Color.GRAY);
        setColorForBlockImage('B', new Color(0, 0, 255));
        setColorForBlockImage('L', new Color(65, 105, 225));
        setColorForBlockImage('P', new Color(95, 0, 160));
        setColorForBlockImage('R', new Color(180, 103, 167));
        setColorForBlockImage('K', new Color(58, 253, 167));
        setColorForBlockImage('M', new Color(254, 1, 154));
        setWindowTitle("Moon Patrol");
        setStatusText("Lea Wagner - Java Programmierung SS 2023");
        setWindowIcon("moon-car.png");
        gameManager.startNewGame();
        //showStatistic(true);
    }

    @Override
    public void gameLoop() {
        gameManager.gameLoopUpdate();
    }
}
