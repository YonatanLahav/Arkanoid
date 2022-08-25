/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl should be add.
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl need to remove.
     */
    void removeHitListener(HitListener hl);
}