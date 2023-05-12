package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.movable.MoonBaggy;
import thd.gameobjects.movable.Shot;
import thd.gameobjects.movable.VerticalShot;
import java.awt.event.KeyEvent;

class UserControlledGameObjectPool {

    protected final GameView gameView;
    protected Shot shot;
    protected VerticalShot verticalShot;
    protected MoonBaggy moonBaggy;

    UserControlledGameObjectPool(GameView gameView) {
        this.gameView = gameView;
    }

    protected void gameLoopUpdate() {
        Integer[] pressedKeys = gameView.keyCodesOfCurrentlyPressedKeys(); for (int keyCode : pressedKeys) {
            processKeyCode(keyCode);
        }
    }

    private void processKeyCode(int keyCode) {
        if (keyCode == KeyEvent.VK_D) {
        moonBaggy.right();
    } else if (keyCode == KeyEvent.VK_SPACE) {
        moonBaggy.shoot();
    }
    }
}
