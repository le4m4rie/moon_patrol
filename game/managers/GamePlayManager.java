package thd.game.managers;
import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.Drone;
import thd.gameobjects.movable.SpinningUfo;
import thd.gameobjects.movable.Ufo2;
import thd.gameobjects.unmovable.*;

/**
 * This class is responsible for the game occurences such as spawning and deleting objects.
 */
public class GamePlayManager extends WorldShiftManager {

    private final GameObjectManager gameObjectManager;
    protected int points;
    protected int lives;
    protected int progress;


    protected GamePlayManager(GameView gameView) {
        super(gameView);
        gameObjectManager = new GameObjectManager();
    }

    @Override
    public void spawnGameObject(GameObject gameObject) {
        super.spawnGameObject(gameObject);
        gameObjectManager.add(gameObject);
    }

    @Override
    public void destroyGameObject(GameObject gameObject) {
        super.destroyGameObject(gameObject);
        gameObjectManager.remove(gameObject);
    }

    @Override
    protected void destroyAllGameObjects() {
        super.destroyAllGameObjects();
        gameObjectManager.removeAll();
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameObjectManager.gameLoopUpdate();
        gamePlayManagement();
    }

    private void gamePlayManagement() {
        if (gameView.timer(6000, this)) {
            spawnGameObject(new DirtObstacle(gameView, this));
            spawnGameObject(new Mine(gameView, this));
            spawnGameObject(new Ufo2(gameView, this, level.ufoSpeed, level.ufoShotIntervalInMilliseconds));
        }
        if (gameView.timer(7000, this)) {
            spawnGameObject(new SpinningUfo(gameView, this, level.ufoSpeed, level.ufoShotIntervalInMilliseconds));
            gameView.playSound("ufoappears.wav", false);
        }
        if (gameView.timer(10000, this)) {
            spawnGameObject(new Drone(gameView, this, 3));
            spawnGameObject(new Crater(gameView, this));
        }
        if (gameView.timer(4000, this)) {
            spawnGameObject(new Mount(gameView, this, level.pngFile));
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
     * Gets called when the MoonBaggy collides with an obstacle or gets shot.
     */
    public void lifeLost() {
        lives--;
        livesPanel.setLives(lives);
    }

    /**
     * Adds the according number of points to the points counter.
     *
     * @param numberOfPoints the number of points when destroying an object are different
     *                       for each of the objects.
     */
    public void addPoints(int numberOfPoints) {
        points += numberOfPoints;
        score.setScore(points);
    }

}

