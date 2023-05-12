package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.Ufo;
import thd.gameobjects.movable.Ufo2;
import thd.gameobjects.movable.UfoShot;
import thd.gameobjects.unmovable.DirtObstacle;

/**
 * This class is responsible for the game occurences such as spawning and deleting objects.
 */
public class GamePlayManager extends UserControlledGameObjectPool {

    private final GameObjectManager gameObjectManager;
    protected int points;
    protected int lives;
    private static final int LIVES = 3;


    protected GamePlayManager(GameView gameView) {
        super(gameView);
        gameObjectManager = new GameObjectManager();
        lives = LIVES;
    }

    /**
     * Delegates {@link GameObjectManager} to spawn new objects.
     *
     * @param gameObject GameObject to be spawned.
     */
    public void spawnGameObject(GameObject gameObject) {
        gameObjectManager.add(gameObject);
    }

    /**
     * Delegates {@link GameObjectManager} to delete the objects.
     *
     * @param gameObject GameObject to be deleted.
     */
    public void destroyGameObject(GameObject gameObject) {
        gameObjectManager.remove(gameObject);
    }

    protected void destroyAllGameObjects() {
        gameObjectManager.removeAll();
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameObjectManager.gameLoopUpdate();
        gamePlayManagement();
    }

    private void gamePlayManagement() {
        Ufo2 ufo2 = new Ufo2(gameView, this);
        if (gameView.timer(6000, this)) {
            Ufo ufo = new Ufo(gameView, this);
            spawnGameObject(ufo);
        }
        if (gameView.timer(6000, this)) {
            spawnGameObject(ufo2);

        }
        if (gameView.timer(5000, this)) {
            spawnGameObject(new UfoShot(gameView, this, ufo2));
        }
        if (gameView.timer(10000, this)) {
            spawnGameObject(new DirtObstacle(gameView, this));
        }
    }

    /**
     * Getter for the lives variable.
     *
     * @return the current lives.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Getter for the points variable.
     *
     * @return the current points.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Gets called when the MoonBaggy collides with an obstacle or gets shot.
     */
    public void lifeLost() {
        this.lives = lives - 1;
        if (lives == 0) {
            destroyGameObject(moonBaggy);
        }
    }

    /**
     * Adds the according number of points to the points counter.
     *
     * @param numberOfPoints the number of points when destroying an object are different
     *                       for each of the objects.
     */
    public void addPoints(int numberOfPoints) {
        this.points += numberOfPoints;
    }

}

