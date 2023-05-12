package thd.game.managers;
import thd.game.utilities.GameView;
import thd.gameobjects.movable.*;
import thd.gameobjects.unmovable.*;

class GameManager extends GamePlayManager {
    private final Time time;
    private final Life life;
    private final Ground ground;
    private final Levels levels;
    private final Panel panel;
    private final Score score;

    GameManager(GameView gameView) {
        super(gameView);
        this.life = new Life(gameView, this);
        this.ground = new Ground(gameView, this);
        this.levels = new Levels(gameView, this);
        this.panel = new Panel(gameView, this);
        this.time = new Time(gameView, this);
        this.moonBaggy = new MoonBaggy(gameView, this);
        this.shot = new Shot(gameView, this, moonBaggy);
        this.verticalShot = new VerticalShot(gameView, this, moonBaggy);
        this.score = new Score(gameView, this);
        spawnGameObject(panel);
        spawnGameObject(time);
        spawnGameObject(life);
        spawnGameObject(ground);
        spawnGameObject(levels);
        spawnGameObject(shot);
        spawnGameObject(verticalShot);
        spawnGameObject(moonBaggy);
        spawnGameObject(score);
    }

    private void gameManagement() {
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        gameManagement();
    }
}
