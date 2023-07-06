package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

import java.awt.*;


/**
 * Shows the current level progess.
 */
class ProgressDisplay extends GameObject {

    private int progress;

    /**
     * Creates a new Progress Display.
     *
     * @param gameView GameView.
     *
     * @param gamePlayManager GamePlayManager.
     */
    protected ProgressDisplay(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        position.updateCoordinates(360, 50);
        distanceToBackground = 6;
        progress = 0;
    }

    @Override
    public void addToCanvas() {
        gameView.addRectangleToCanvas(position.getX(), position.getY(), 530, 20, 2, false, Color.BLUE);
        switch (progress) {
            case 0 -> {
                gameView.addOvalToCanvas(375, 60, 10, 10, 2, true, Color.BLUE);
            }
            case 1 -> {
                gameView.addOvalToCanvas(475, 60, 10, 10, 2, true, Color.BLUE);
            }
            case 2 -> {
                gameView.addOvalToCanvas(575, 60, 10, 10, 2, true, Color.BLUE);
            }
        }
    }

    /**
     * Sets the current progress.
     *
     * @param progress The current progress.
     */
    void setProgress(int progress) {
        this.progress = progress;
    }
}

