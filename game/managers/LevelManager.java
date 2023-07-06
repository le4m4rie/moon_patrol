package thd.game.managers;

import thd.game.level.*;
import thd.game.utilities.GameView;

import java.util.List;

class LevelManager extends GameWorldManager {

    private List<Level> levels;
    protected static final int LIVES = 3;
    protected int currentLevelIndex;

    protected LevelManager(GameView gameView) {
        super(gameView);
    }

    @Override
    protected void initializeLevel() {
        super.initializeLevel();
        initializeGameObjects();
    }

    protected boolean hasNextLevel() {
        return currentLevelIndex < levels.size() - 1;
    }

    protected void switchToNextLevel() {
        if (hasNextLevel()) {
            currentLevelIndex++;
            progress++;
            level = levels.get(currentLevelIndex);
        } else {
            throw new NoMoreLevelsAvailableException("Level " + currentLevelIndex + " not available.");
        }
    }

    private void initializeGameObjects() {
        livesPanel.setLives(lives);
        score.setScore(points);
        progressDisplay.setProgress(progress);
    }

    protected void initializeGame() {
        levels = List.of(new Level1(), new Level2(), new Level3());
        currentLevelIndex = 0;
        level = levels.get(currentLevelIndex);
        lives = LIVES;
        points = 0;
        progress = 0;
        moonBaggy.revive();
    }


}
