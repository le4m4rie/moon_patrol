package thd.game.managers;
import thd.gameobjects.base.GameObject;
import java.util.LinkedList;
import java.util.List;


class GameObjectManager extends CollisionManager {

    private final List<GameObject> gameObjects;
    private final List<GameObject> gameObjectsToBeAdded;
    private final List<GameObject> gameObjectsToBeRemoved;

    private static final int MAXIMUM_NUMBER_OF_GAME_OBJECTS = 500;


    GameObjectManager() {
        this.gameObjects = new LinkedList<>();
        this.gameObjectsToBeAdded = new LinkedList<>();
        this.gameObjectsToBeRemoved = new LinkedList<>();
    }

    void add(GameObject gameObject) {
        gameObjectsToBeAdded.add(gameObject);
    }

    void remove(GameObject gameObject) {
        gameObjectsToBeRemoved.add(gameObject);
    }

    void removeAll() {
        gameObjectsToBeAdded.clear();
        gameObjectsToBeRemoved.addAll(gameObjects);
    }

    private void removeFromGameObjects() {
        for (GameObject gameObject : gameObjectsToBeRemoved) {
            gameObjects.remove(gameObject);
            removeFromCollisionManagement(gameObject);
        }
        gameObjectsToBeRemoved.clear();
    }

    private void addToGameObjects() {
        for (GameObject gameObject : gameObjectsToBeAdded) {
            gameObjects.add(gameObject);
            addToCollisionManagement(gameObject);
        }
        gameObjectsToBeAdded.clear();
    }

    void gameLoopUpdate() {
        updateLists();
        for (GameObject gameObject : gameObjects) {
            gameObject.updateStatus();
            gameObject.updatePosition();
            gameObject.addToCanvas();
        }
        manageCollisions(true);
    }
    private void updateLists() {
        removeFromGameObjects();
        addToGameObjects();
        if (gameObjects.size() > MAXIMUM_NUMBER_OF_GAME_OBJECTS) {
            throw new TooManyGameObjectsException("Die Anzahl der Spielobjekte darf höchstens " + MAXIMUM_NUMBER_OF_GAME_OBJECTS + " betragen.");
        }
    }
}
