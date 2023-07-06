package thd.game.level;

/**
 * Level 3 of the game.
 */
public class Level3 extends Level {

    /**
     * Builds a new Level1. R for background, W for mountains.
     */
    public Level3() {
        name = "Level J: Shooting Stars";
        //number = 3;
        worldOffsetColumns = 14;
        worldOffsetLines = 4;
        pngFile = "mountains3.png";
        if (difficulty == Difficulty.EASY) {
            ufoShotIntervalInMilliseconds = 2000;
            ufoSpeed = 8;
        } else if (difficulty == Difficulty.STANDARD) {
            ufoShotIntervalInMilliseconds = 1000;
            ufoSpeed = 8;
        }
        world = """
                    V         V         V        \s
                                                 \s
                                                 \s
                                                 \s
                V        R                  V    \s
            M                M                   \s
                                                 \s
                                                 \s
                                                 \s
                                                 \s
                            Q                    \s
                                                 \s""";
    }

}