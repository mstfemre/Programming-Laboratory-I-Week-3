public class Main {
    public static void main(String[] args) {
        Car car = new Car("38 LAB 003", "Toyota Corolla", 20, 50);

        System.out.println("=== Initial status ===");
        car.checkStatus();

        System.out.println("\n=== Successful trip ===");
        car.drive(120);
        car.checkStatus();

        System.out.println("\n=== Not enough fuel: trip rejected ===");
        car.drive(100);
        car.checkStatus();

        System.out.println("\n=== Normal refueling ===");
        car.refuel(20);
        car.checkStatus();

        System.out.println("\n=== Overfilling: tank capped at capacity ===");
        car.refuel(30);
        car.checkStatus();

        System.out.println("\n=== Low fuel warning ===");
        car.drive(460);
        car.checkStatus();

        System.out.println("\n=== Using exactly the remaining fuel ===");
        car.drive(40);
        car.checkStatus();
    }
}
