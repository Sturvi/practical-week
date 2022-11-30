public abstract class Transport {
    int fuel;
    int speed;

    public Transport(int fuel, int speed) {
        this.fuel = fuel;
        this.speed = speed;
    }

    abstract boolean canMove(int n);

    abstract int getFuelLevel ();
}
