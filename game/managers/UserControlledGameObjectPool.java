package thd.game.managers;

import thd.game.level.Level;
import thd.game.utilities.GameView;
import thd.gameobjects.movable.MoonBaggy;
import thd.gameobjects.unmovable.*;

import java.awt.event.KeyEvent;

class UserControlledGameObjectPool {

    protected final GameView gameView;
    protected MoonBaggy moonBaggy;
    protected Level level;
    protected Time time;
    protected LivesPanel livesPanel;
    protected Ground ground;
    protected LevelsDisplay levelsDisplay;
    protected Panel panel;
    protected Score score;
    protected Overlay overlay;
    protected Earth earth;
    protected CautionDisplay cautionDisplay;
    protected ProgressDisplay progressDisplay;

    UserControlledGameObjectPool(GameView gameView) {
        this.gameView = gameView;
    }

    protected void gameLoopUpdate() {
        Integer[] pressedKeys = gameView.keyCodesOfCurrentlyPressedKeys();
        for (int keyCode : pressedKeys) {
            processKeyCode(keyCode);
        }
    }

    private void processKeyCode(int keyCode) {
        if (keyCode == KeyEvent.VK_D) {
        moonBaggy.right();
    } else if (keyCode == KeyEvent.VK_SPACE) {
        moonBaggy.shoot();
    } else if (keyCode == KeyEvent.VK_W) {
            moonBaggy.jump();
        }
    }
}
