/**
 * @author Yonatan Lahav
 * ID 316099548
 */
public class Counter {
    //the value in the counter.
    private int value;

    /**
     * constructor for Counter.
     *
     * @param amount the amount that the counter start with.
     */
    public Counter(int amount) {
        this.value = amount;
    }

    /**
     * add number to current counter.
     *
     * @param number add this num to the counter.
     */
    public void increase(int number) {
        this.value = this.value + number;
    }

    /**
     * subtract number from current count.
     *
     * @param number the number that need to substract.
     */
    public void decrease(int number) {
        this.value = this.value - number;
    }

    /**
     * Getter for the value of the counter.
     *
     * @return the value field of the counter.
     */
    public int getValue() {
        return this.value;
    }
}