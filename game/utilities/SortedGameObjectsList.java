package thd.game.utilities;

import thd.gameobjects.base.GameObject;

import java.util.LinkedList;

/**
 * A new Linked List which sorts new Game Objects into their right position in relation to the background.
 */
public class SortedGameObjectsList extends LinkedList<GameObject> {

    @Override
    public boolean add(GameObject toAdd) {
        int indexToSortIn = 0;
        for (GameObject gameObject : this) {
            if (gameObject.getDistanceToBackground() >= toAdd.getDistanceToBackground()) {
                break;
            }
            indexToSortIn++;
        }
        this.add(indexToSortIn, toAdd);
        return true;
    }

}
