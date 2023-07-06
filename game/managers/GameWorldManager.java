package thd.game.managers;

import thd.game.utilities.GameView;
import thd.gameobjects.base.GameObject;
import thd.gameobjects.movable.MoonBaggy;
import thd.gameobjects.movable.Ufo2;
import thd.gameobjects.unmovable.*;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

class GameWorldManager extends GamePlayManager {

    private final List<GameObject> activatableGameObjects;

    protected GameWorldManager(GameView gameView) {
        super(gameView);
        activatableGameObjects = new LinkedList<>();
        livesPanel = new LivesPanel(gameView, this);
        ground = new Ground(gameView, this);
        levelsDisplay = new LevelsDisplay(gameView, this);
        panel = new Panel(gameView, this);
        time = new Time(gameView, this);
        moonBaggy = new MoonBaggy(gameView, this);
        score = new Score(gameView, this);
        overlay = new Overlay(gameView, this);
        earth = new Earth(gameView, this);
        progressDisplay = new ProgressDisplay(gameView, this);
        cautionDisplay = new CautionDisplay(gameView, this);
    }

    private void spawnGameObjects() {
        spawnGameObject(panel);
        spawnGameObject(time);
        spawnGameObject(livesPanel);
        spawnGameObject(ground);
        spawnGameObject(levelsDisplay);
        spawnGameObject(score);
        spawnGameObject(overlay);
        spawnGameObject(earth);
        spawnGameObject(progressDisplay);
        spawnGameObject(cautionDisplay);
    }

    private void spawnGameObjectsFromWorldString() {
        String[] lines = level.world.split("\\R");
        for (int lineIndex = 0; lineIndex < lines.length; lineIndex++) {
            for (int columnIndex = 0; columnIndex < lines[lineIndex].length(); columnIndex++) {
                double x = (columnIndex - level.worldOffsetColumns) * 100;
                double y = (lineIndex - level.worldOffsetLines) * 100;
                char character = lines[lineIndex].charAt(columnIndex);
                if (character == 'B') {
                    Background background = new Background(gameView, this);
                    background.getPosition().updateCoordinates(x, y);
                    spawnGameObject(background);
                } else if (character == 'E') {
                    Earth earth = new Earth(gameView, this);
                    earth.getPosition().updateCoordinates(x, y + 30);
                    spawnGameObject(earth);
                } else if (character == 'M') {
                    Mount mount = new Mount(gameView, this, level.pngFile);
                    mount.getPosition().updateCoordinates(x, y);
                    spawnGameObject(mount);
                } else if (character == 'N') {
                    Mountains2 mountains2 = new Mountains2(gameView, this);
                    mountains2.getPosition().updateCoordinates(x, y);
                    spawnGameObject(mountains2);
                } else if (character == 'P') {
                    Background2 background2 = new Background2(gameView, this);
                    background2.getPosition().updateCoordinates(x, y);
                    spawnGameObject(background2);
                } else if (character == 'R') {
                    Background3 background3 = new Background3(gameView, this);
                    background3.getPosition().updateCoordinates(x, y);
                    spawnGameObject(background3);
                } else if (character == 'W') {
                    Mountains3 mountains3 = new Mountains3(gameView, this);
                    mountains3.getPosition().updateCoordinates(x, y);
                    spawnGameObject(mountains3);
                } else if (character == 'V') {
                    Ufo2 ufo2 = new Ufo2(gameView, this, level.ufoSpeed, level.ufoShotIntervalInMilliseconds);
                    ufo2.getPosition().updateCoordinates(x, y);
                    spawnGameObject(ufo2);
                } else if (character == 'Q') {
                    moonBaggy.getPosition().updateCoordinates(x, y);
                    spawnGameObject(moonBaggy);
                }
            }
        }
    }

    private void addActivatableGameObject(GameObject gameObject) {
        activatableGameObjects.add(gameObject);
        addToShiftableGameObjectsIfShiftable(gameObject);
    }

    @Override
    protected void gameLoopUpdate() {
        super.gameLoopUpdate();
        activateGameObjects();
    }

    private void activateGameObjects() {
        ListIterator<GameObject> iterator = activatableGameObjects.listIterator();
        while (iterator.hasNext()) {
            GameObject gameObject = iterator.next();
            if (gameObject instanceof DirtObstacle dirtObstacle) {
                if (dirtObstacle.tryToActivate(moonBaggy)) {
                    spawnGameObject(dirtObstacle);
                    iterator.remove();
                }
            }
        }
    }

    protected void initializeLevel() {
        activatableGameObjects.clear();
        destroyAllGameObjects();
        spawnGameObjects();
        spawnGameObjectsFromWorldString();
    }
}
