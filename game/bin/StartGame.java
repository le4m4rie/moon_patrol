package thd.game.bin;

import thd.game.managers.GameViewManager;

/**
 * This class starts the game.
 */
class StartGame {

    /**
     * Starts the game.
     *
     * @param args as arguments.
     */
    public static void main(String[] args) {
        GameViewManager gameViewManager = new GameViewManager();
        gameViewManager.startGame();
    }
}
