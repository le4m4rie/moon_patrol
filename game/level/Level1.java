package thd.game.level;

/**
 * Level 1 of the game.
 */
public class Level1 extends Level {

    /**
     * Builds a new Level1. B for background, M for mountains, U for ufo.
     */
    public Level1() {
        name = "Level A: Moon Mission";
        //number = 1;
        worldOffsetColumns = 14;
        worldOffsetLines = 4;
        pngFile = "mountains.png";
        if (difficulty == Difficulty.EASY) {
            ufoShotIntervalInMilliseconds = 6000;
            ufoSpeed = 6;
        } else if (difficulty == Difficulty.STANDARD) {
            ufoShotIntervalInMilliseconds = 5000;
            ufoSpeed = 6;
        }
        world = """
                    V         V        V         \s
                                                 \s
                                                 \s
                                                 \s
                         B                       \s
           M            M                        \s
                                                 \s
                                                 \s
                                                 \s
                                                 \s
                           Q                     \s
                                                 \s""";
    }

}
