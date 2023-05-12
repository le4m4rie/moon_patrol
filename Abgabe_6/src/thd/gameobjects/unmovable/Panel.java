package thd.gameobjects.unmovable;


import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;

/**
 * Represents the panel where all the important stats such as lives left and the current level are displayed.
 */
public class Panel extends GameObject {

    private final PanelBlockImages panelBlockImages;

    /**
     * Creates a new Panel.
     *
     * @param gameView an instance of the GameView class.
     *
     * @param gamePlayManager an instance of the GamePlayManager class.
     */
    public Panel(GameView gameView, GamePlayManager gamePlayManager) {
        super(gameView, gamePlayManager);
        this.size = 10;
        this.rotation = 0;
        this.width = 1200;
        this.height = 100;
        position.updateCoordinates(0, 0);
        this.panelBlockImages = new PanelBlockImages();
    }

    @Override
    public void addToCanvas() {
        String panel = panelBlockImages.PANEL;
        gameView.addBlockImageToCanvas(panel, position.getX(), position.getY(), 20, this.rotation);
    }
}