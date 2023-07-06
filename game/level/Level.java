package thd.game.level;

/**
 * Represents the base class of the different levels in the game.
 */
public class Level {

    /**
     * The name of the level, e.g. A.
     */
    public String name;


    /**
     * The game world coded as a String representation.
     */
    public String world;

    /**
     * The world offset in x.
     */
    public int worldOffsetColumns;

    /**
     * The world offset in y.
     */
    public int worldOffsetLines;

    /**
     * Sets the level's difficulty.
     */
    public static Difficulty difficulty = Difficulty.STANDARD;

    /**
     * The Ufo Shots interval.
     */
    public int ufoShotIntervalInMilliseconds;

    /**
     * The Ufo speed.
     */
    public int ufoSpeed;

    /**
     * Builds a new Level.
     */
    public Level() {
    }

    /**
     * The png file for the background mountains.
     */
    public String pngFile;

}
