package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

/**
 * Represents how many lives the player has left.
 */
public class LivesPanel extends GameObject {

    private final LivesBlockImages livesBlockImages;
    private int lives;

    /**
     * Creates a new Lives indicator.
     *
     * @param gameView an instance of the GameView object.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public LivesPanel(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        size = 20;
        rotation = 0;
        width = 10;
        height = 7;
        position.updateCoordinates(40, 10);
        livesBlockImages = new LivesBlockImages();
        distanceToBackground = 6;
        lives = 3;
    }

    @Override
    public void addToCanvas() {
        String livesImage = livesBlockImages.LIVES;
        gameView.addBlockImageToCanvas(livesImage, position.getX(), position.getY(), 2, this.rotation);
        gameView.addTextToCanvas(String.valueOf(lives), 90, 10, 20, true, Color.WHITE, 0, "font.ttf");
        if (gamePlayManager.getLives() == 1) {
            gameView.addTextToCanvas(String.valueOf(lives), 90, 10, 20, true, Color.RED, 0, "font.ttf");
        }
    }

    /**
     * Sets the current number of lives.
     *
     * @param lives current number of lives.
     */
    public void setLives(int lives) {
        this.lives = lives;
    }

}
