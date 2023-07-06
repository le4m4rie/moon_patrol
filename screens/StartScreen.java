package thd.screens;

import thd.game.level.Difficulty;
import thd.game.utilities.GameView;

/**
 * Implements the Start Screen.
 */
public class StartScreen {

    private final GameView gameView;

    private Difficulty selectedDifficulty;

    /**
     * Creates a new Start Screen.
     *
     * @param gameView GameView.
     */
    public StartScreen(GameView gameView) {
        this.gameView = gameView;
        selectedDifficulty = Difficulty.EASY;
    }

    /**
     * Shows a start screen.
     *
     * @param preselectedDifficulty the preselected difficulty.
     */
    public void showStartScreenWithPreselectedDifficulty(Difficulty preselectedDifficulty) {
        boolean isEasyDifficulty = preselectedDifficulty.equals(Difficulty.EASY);

        String title = "\n"
                + "___  ___                   ______     _             _ \n"
                + "|  \\/  |                   | ___ \\   | |           | |\n"
                + "| .  . | ___   ___  _ __   | |_/ /_ _| |_ _ __ ___ | |\n"
                + "| |\\/| |/ _ \\ / _ \\| '_ \\  |  __/ _` | __| '__/ _ \\| |\n"
                + "| |  | | (_) | (_) | | | | | | | (_| | |_| | | (_) | |\n"
                + "\\_|  |_/\\___/ \\___/|_| |_| \\_|  \\__,_|\\__|_|  \\___/|_|\n"
                + "                                                      ";

        String description =
                "                                                                                                          \n"
                + "                                                                                                          \n"
                + "                                                                                                          \n"
                + "                                                                                                          \n"
                + "                  Hop into your moon baggy and embark on an epic lunar odyssey as you battle against UFOs!\n"
                              + "                            Use RIGHT to move the moon baggy, UP to jump and SPACE to shoot!";
        gameView.showSimpleStartScreen(title, description, isEasyDifficulty);
    }

    /**
     * The selected difficulty.
     *
     * @return the selected difficulty.
     */
    public Difficulty getSelectedDifficulty() {
        return selectedDifficulty;
    }
}
