package thd.game.managers;
import thd.game.utilities.SortedGameObjectsList;
import thd.gameobjects.base.GameObject;
import java.util.LinkedList;
import java.util.List;


class GameObjectManager extends CollisionManager {

    private final List<GameObject> gameObjects;
    private final List<GameObject> gameObjectsToBeAdded;
    private final List<GameObject> gameObjectsToBeRemoved;

    private static final int MAXIMUM_NUMBER_OF_GAME_OBJECTS = 500;


    GameObjectManager() {
        gameObjects = new SortedGameObjectsList();
        gameObjectsToBeAdded = new LinkedList<>();
        gameObjectsToBeRemoved = new LinkedList<>();
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
        for (GameObject toRemove : gameObjectsToBeRemoved) {
            gameObjects.remove(toRemove);
            removeFromCollisionManagement(toRemove);
        }
        gameObjectsToBeRemoved.clear();
    }

    private void addToGameObjects() {
        for (GameObject toAdd : gameObjectsToBeAdded) {
            gameObjects.add(toAdd);
            addToCollisionManagement(toAdd);
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
        manageCollisions(false);
    }
    private void updateLists() {
        removeFromGameObjects();
        addToGameObjects();
        if (gameObjects.size() > MAXIMUM_NUMBER_OF_GAME_OBJECTS) {
            throw new TooManyGameObjectsException("Die Anzahl der Spielobjekte darf höchstens " + MAXIMUM_NUMBER_OF_GAME_OBJECTS + " betragen.");
        }
    }
}
