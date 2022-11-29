public class Car {
    String carModel;

    public Car(String carModel) {
        this.carModel = carModel;
    }
}


public class Engine {
    boolean isWorking = false;
    int horsepower;


    public Engine(int horsepower) {
        this.horsepower = horsepower;
    }

    String startStopEngine(){
        if (isWorking) {
            isWorking = false;
            return "engine was stopped";
        }
        isWorking = true;
        return "engine was started";
    }
}