package thd.game.level;

/**
 * Unchecked exception in case there are no more levels left.
 */
public class NoMoreLevelsAvailableException extends RuntimeException {

    /**
     * Throws an error message.
     *
     * @param message error message.
     */
    public NoMoreLevelsAvailableException(String message) {
        super(message);
    }
}
