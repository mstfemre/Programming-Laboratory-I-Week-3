public class Car {
    String plateNumber;
    String model;
    double mileage;
    double fuelLevel;
    double tankCapacity;

    public Car(String plateNumber, String model, double fuelLevel, double tankCapacity) {
        this.plateNumber = plateNumber;
        this.model = model;
        this.mileage = 0;
        this.fuelLevel = fuelLevel;
        this.tankCapacity = tankCapacity;
    }

    public void drive(double km) {
        System.out.println("Driving " + km + " km...");
        double fuelNeeded = km / 10;

        if (fuelNeeded > fuelLevel) {
            System.out.println("Not enough fuel for this trip!");
        } else {
            mileage = mileage + km;
            fuelLevel = fuelLevel - fuelNeeded;
        }
    }

    public void refuel(double amount) {
        System.out.println("Refueling " + amount + " liters...");
        fuelLevel = fuelLevel + amount;

        if (fuelLevel > tankCapacity) {
            fuelLevel = tankCapacity;
            System.out.println("Tank is full, extra fuel discarded.");
        }
    }

    public void checkStatus() {
        System.out.println("Car: " + model + " (" + plateNumber + ")");
        System.out.println("Mileage: " + mileage + " km");
        System.out.println("Fuel level: " + fuelLevel + " / " + tankCapacity + " liters");

        if (fuelLevel < tankCapacity * 0.10) {
            System.out.println("Low fuel warning!");
        }
    }
}
