public class Car {
    private final String plateNumber;
    private final String model;
    private double mileage;
    private double fuelLevel;
    private final double tankCapacity;

    public Car(String plateNumber, String model, double fuelLevel, double tankCapacity) {
        if (plateNumber == null || plateNumber.trim().isEmpty()
                || model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Plate number and model must not be empty.");
        }
        if (!Double.isFinite(tankCapacity) || tankCapacity <= 0
                || !Double.isFinite(fuelLevel) || fuelLevel < 0 || fuelLevel > tankCapacity) {
            throw new IllegalArgumentException("Fuel must be between zero and a positive tank capacity.");
        }
        this.plateNumber = plateNumber;
        this.model = model;
        this.mileage = 0;
        this.fuelLevel = fuelLevel;
        this.tankCapacity = tankCapacity;
    }

    public void drive(double km) {
        requireNonnegativeFinite(km, "Distance");
        System.out.println("Driving " + km + " km...");
        double fuelNeeded = km / 10.0;
        if (fuelNeeded > fuelLevel) {
            System.out.println("Not enough fuel for this trip!");
            return;
        }
        mileage += km;
        fuelLevel -= fuelNeeded;
    }

    public void refuel(double amount) {
        requireNonnegativeFinite(amount, "Fuel amount");
        System.out.println("Refueling " + amount + " liters...");
        double availableSpace = tankCapacity - fuelLevel;
        if (amount > availableSpace) {
            fuelLevel = tankCapacity;
            System.out.println("Tank is full, extra fuel discarded.");
        } else {
            fuelLevel += amount;
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

    private static void requireNonnegativeFinite(double value, String name) {
        if (!Double.isFinite(value) || value < 0) {
            throw new IllegalArgumentException(name + " must be a finite, nonnegative number.");
        }
    }

    public String getPlateNumber() { return plateNumber; }
    public String getModel() { return model; }
    public double getMileage() { return mileage; }
    public double getFuelLevel() { return fuelLevel; }
    public double getTankCapacity() { return tankCapacity; }
}
