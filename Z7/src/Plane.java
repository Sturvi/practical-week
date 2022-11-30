public class Plane extends Transport{
    public Plane(int fuel, int speed) {
        super(fuel, speed);
    }

    @Override
    boolean canMove(int n) {
        if (fuel >= n) {
            fuel -= n;
            return true;
        } else return false;
    }

    @Override
    int getFuelLevel() {
        return fuel;
    }

    void fillFuel(int n){
        fuel += n;
    }
}
