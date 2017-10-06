package ch.robinglauser.bfhexercise.exercises;

public class Battery {

    /**
     * Current capacity
     */
    private double curCapacity;

    /**
     * Maximum capacity
     */
    private double maxCapacity;

    /**
     * @param maxCapacity Initial capacity
     */
    public Battery(double maxCapacity) {
        this.curCapacity = maxCapacity;
        this.maxCapacity = maxCapacity;
    }

    /**
     * Charge battery to full capactiy
     */
    public void charge(){
        this.curCapacity = this.maxCapacity;
    }

    /**
     * Drain batter by a charge
     *
     * @param charge
     */
    public void drain(double charge){
        if (this.curCapacity < charge){
            this.curCapacity = 0;
        }
        this.curCapacity -= charge;
    }

    /**
     * Get current capacity
     *
     * @return current capacity
     */
    public double getCurCapacity(){
        return curCapacity;
    }

}