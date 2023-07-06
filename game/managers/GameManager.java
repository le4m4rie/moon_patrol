package thd.game.managers;
import thd.game.level.Difficulty;
import thd.game.level.Level;
import thd.game.utilities.FileAccess;
import thd.game.utilities.GameView;
import thd.screens.EndScreen;
import thd.screens.StartScreen;


class GameManager extends LevelManager {


    protected GameManager(GameView gameView) {
        super(gameView);
    }

    private void gameManagement() {
        if (endOfGame()) {
            if (!overlay.isMessageShown()) {
                overlay.showMessage("GAME OVER!");
                int id = gameView.playSound("gameover.wav", false);
                gameView.stopSound(id);
            }
            if (gameView.timer(2000, this)) {
                overlay.stopShowing();
                EndScreen endScreen = new EndScreen(gameView);
                endScreen.showEndScreen(points);
                startNewGame();
            }
        } else if (endOfLevel()) {
            if (!overlay.isMessageShown()) {
                moonBaggy.hide = true;
                overlay.showMessage("GREAT JOB!");
                int id = gameView.playSound("greatjob.wav", false);
                gameView.stopSound(id);
            }
            if (gameView.timer(2000, this)) {
                overlay.stopShowing();
                switchToNextLevel();
                moonBaggy.hide = false;
                initializeLevel();
            }
        }
    }

    private boolean endOfLevel() {
        if (currentLevelIndex == 0 && points > 2000 || currentLevelIndex == 1 && points > 5000 || currentLevelIndex == 2 && points > 9000) {
            return true;
        }
        return false;
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameManagement();
    }

    @Override
    protected void initializeLevel() {
        super.initializeLevel();
        overlay.showMessage(level.name, 2);
    }

    @Override
    protected void initializeGame() {
        super.initializeGame();
        initializeLevel();
    }

    private boolean endOfGame() {
        return (moonBaggy.isDead || (!hasNextLevel() && endOfLevel()));
    }

    void startNewGame() {
        Difficulty difficulty = FileAccess.readDifficultyFromDisc();
        StartScreen startScreen = new StartScreen(gameView);
        startScreen.showStartScreenWithPreselectedDifficulty(difficulty);
        difficulty = startScreen.getSelectedDifficulty();
        FileAccess.writeDifficultyToDisc(difficulty);
        Level.difficulty = difficulty;
        initializeGame();
    }
}
