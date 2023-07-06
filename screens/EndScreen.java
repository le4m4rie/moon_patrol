package thd.screens;

import thd.game.utilities.GameView;

/**
 * Implements an End Screen.
 */
public class EndScreen {

    private final GameView gameView;

    /**
     * Creates a new End Screen.
     *
     * @param gameView GameView.
     */
    public EndScreen(GameView gameView) {
        this.gameView = gameView;
    }

    /**
     * Shows the end screen.
     *
     * @param score shows the score of the player.
     */
    public void showEndScreen(int score) {
       gameView.showEndScreen("Your score: " + score);
    }
}
