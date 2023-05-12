package thd.gameobjects.base;

import java.util.Random;

/**
 * This class represents the parent class of all possible movement patterns of game objects.
 */
public class MovementPattern {

    protected final Random random;
    protected int currentIndex;

    /**
     * Creates a new movement pattern.
     */
    public MovementPattern() {
        random = new Random();
    }

    /**
     * Defines the new target position once the game object has reached the target.
     *
     * @return a new position.
     */
    protected Position nextTargetPosition() {
        return new Position(0, 0);
    }

    /**
     * Defines the start position for the game object.
     *
     * @return a new position.
     */
    protected Position startPosition() {
        return new Position(0, 0);
    }
}
