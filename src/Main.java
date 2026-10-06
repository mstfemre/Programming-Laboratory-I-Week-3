public class Main {
    public static void main(String[] args) {
        Car car = new Car("38 LAB 003", "Toyota Corolla", 20, 50);

        car.checkStatus();
        System.out.println();

        car.drive(120);
        car.checkStatus();
        System.out.println();

        car.drive(100);
        car.checkStatus();
        System.out.println();

        car.refuel(20);
        car.checkStatus();
        System.out.println();

        car.refuel(30);
        car.checkStatus();
        System.out.println();

        car.drive(460);
        car.checkStatus();
    }
}
