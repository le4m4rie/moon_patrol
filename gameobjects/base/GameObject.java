package thd.gameobjects.base;

import thd.game.managers.GamePlayManager;
import thd.game.utilities.GameView;

import java.util.Objects;

/**
 * Represents an object in the game.
 */
public abstract class GameObject {
    protected final GameView gameView;
    protected final Position position;
    protected final Position targetPosition;
    protected double speedInPixel;
    protected double rotation;
    protected double size;
    protected double width;
    protected double height;
    protected final GamePlayManager gamePlayManager;
    protected char distanceToBackground;

    /**
     * Crates a new GameObject.
     *
     * @param gameView GameView to show the game object.
     * @param gamePlayManager GamePlayManager to manage the game objects occurences.
     */
    public GameObject(GameView gameView, GamePlayManager gamePlayManager) {
        this.gameView = gameView;
        position = new Position();
        targetPosition = new Position();
        this.gamePlayManager = gamePlayManager;
    }

    /**
     * Updates the position of the game object.
     */
    public void updatePosition() {
    }

    /**
     * Draws the game object to the canvas.
     */
    public abstract void addToCanvas();

    /**
     * Returns the current position of the game object.
     *
     * @return position of the game object.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Returns width of game object.
     *
     * @return Width of game object
     */
    public double getWidth() {
        return width;
    }

    /**
     * Returns height of game object.
     *
     * @return Height of game object
     */
    public double getHeight() {
        return height;
    }

    /**
     * Updates the current status of the game object.
     */
    public void updateStatus() {
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameObject other = (GameObject) o;
        return Double.compare(speedInPixel, other.speedInPixel) == 0 && rotation == other.rotation && size == other.size && width == other.width && height == other.height && Objects.equals(position, other.position) && Objects.equals(targetPosition, other.targetPosition) && Objects.equals(distanceToBackground, other.distanceToBackground);
    }

    @Override
    public int hashCode() {
       return Objects.hash(speedInPixel, rotation, size, width, height, position, targetPosition, distanceToBackground);
    }

    /**
     * Returns the distance to the background.
     *
     * @return distance to background.
     */
    public char getDistanceToBackground() {
        return distanceToBackground;
    }
}