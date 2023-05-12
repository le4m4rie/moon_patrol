package thd.gameobjects.unmovable;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;

/**
 * Represents how many lives the player has left.
 */
public class Life extends GameObject {

    private final LivesBlockImages livesBlockImages;

    /**
     * Creates a new Lives indicator.
     *
     * @param gameView an instance of the GameView object.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public Life(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        this.size = 20;
        this.rotation = 0;
        this.width = 10;
        this.height = 7;
        position.updateCoordinates(40, 10);
        this.livesBlockImages = new LivesBlockImages();
    }

    @Override
    public void addToCanvas() {
        String lives = livesBlockImages.LIVES;
        gameView.addBlockImageToCanvas(lives, position.getX(), position.getY(), 2, this.rotation);
        gameView.addTextToCanvas(String.valueOf(gamePlayManager.getLives()), 90, 10, 20, true, Color.WHITE, 0);
    }

}
