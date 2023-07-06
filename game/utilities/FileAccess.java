package thd.game.utilities;

import thd.game.level.Difficulty;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


/**
 * This class manages the file access to an external file that determines the difficulty.
 */
public class FileAccess {

    private static final Path WICHTEL_GAME_FILE = Paths.get(System.getProperty("user.home")).resolve("wichtelgame.txt");


    /**
     * Creates a new File Access.
     */
    public FileAccess() {
    }

    /**
     * Changing the difficulty.
     *
     * @param difficulty the difficulty.
     */
    public static void writeDifficultyToDisc(Difficulty difficulty) {
        try {
            //Path filePath = Paths.get(System.getProperty("user.home")).resolve(WICHTEL_GAME_FILE);
            Files.writeString(WICHTEL_GAME_FILE, difficulty.toString());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Reading the difficulty.
     *
     * @return the difficulty.
     */
    public static Difficulty readDifficultyFromDisc() {
        try {
            String difficultyString = Files.readString(WICHTEL_GAME_FILE);
            return Difficulty.valueOf(difficultyString);
        } catch (IllegalArgumentException e) {
            return Difficulty.STANDARD;
        } catch (IOException e) {
            return Difficulty.STANDARD;
        }
    }


}
