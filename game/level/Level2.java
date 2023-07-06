package thd.game.level;

/**
 * Level 2 of the game.
 */
public class Level2 extends Level {

    /**
     * Builds a new Level1. P for background, N for mountains, V for ufo2.
     */
    public Level2() {
        name = "Level E: Flying Enemies";
        //number = 2;
        worldOffsetColumns = 14;
        worldOffsetLines = 4;
        pngFile = "mountains2.png";
        if (difficulty == Difficulty.EASY) {
            ufoShotIntervalInMilliseconds = 4000;
            ufoSpeed = 7;
        } else if (difficulty == Difficulty.STANDARD) {
            ufoShotIntervalInMilliseconds = 3000;
            ufoSpeed = 7;
        }
        world = """
                    V         V         V        \s
                                                 \s
                                                 \s
                                                 \s
                V        P                  V    \s
            M             M                      \s
                                                 \s
                                                 \s
                                                 \s
                                                 \s
                            Q                    \s
                                                 \s""";
    }
}
