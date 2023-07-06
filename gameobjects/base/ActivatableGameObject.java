package thd.gameobjects.base;

/**
 * A new Activatable Game Object Interface.
 *
 * @param <G> a GameObject.
 */
public interface ActivatableGameObject<G> {

    /**
     * Decides when a Game Object should be activated in the game.
     *
     * @param info another Game Object.
     * @return true if Game Object should be activated.
     */
    boolean tryToActivate(G info);
}
