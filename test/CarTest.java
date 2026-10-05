import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class CarTest {
    private static int passed = 0;

    public static void main(String[] args) {
        Car car = new Car("38 TEST 003", "Test Car", 20, 50);
        equal(0, car.getMileage(), "Initial mileage");
        equal(20, car.getFuelLevel(), "Initial fuel");

        capture(() -> car.drive(120));
        equal(120, car.getMileage(), "Successful trip mileage");
        equal(8, car.getFuelLevel(), "Consumption is 1 liter per 10 km");

        contains(capture(() -> car.drive(100)), "Not enough fuel for this trip!", "Rejected trip message");
        equal(120, car.getMileage(), "Rejected trip keeps mileage");
        equal(8, car.getFuelLevel(), "Rejected trip keeps fuel");

        capture(() -> car.refuel(20));
        equal(28, car.getFuelLevel(), "Normal refueling");
        contains(capture(() -> car.refuel(30)), "Tank is full, extra fuel discarded.", "Overflow message");
        equal(50, car.getFuelLevel(), "Tank capacity enforced");

        capture(() -> car.drive(450));
        equal(5, car.getFuelLevel(), "Exactly 10 percent fuel");
        absent(capture(car::checkStatus), "Low fuel warning!", "No warning at exactly 10 percent");
        capture(() -> car.drive(10));
        String status = capture(car::checkStatus);
        contains(status, "Low fuel warning!", "Warning below 10 percent");
        contains(status, "Mileage: 580.0 km", "Status shows mileage");
        contains(status, "Fuel level: 4.0 / 50.0 liters", "Status shows fuel");

        capture(() -> car.drive(40));
        equal(0, car.getFuelLevel(), "Exactly enough fuel is accepted");
        equal(620, car.getMileage(), "Final mileage");
        contains(capture(() -> car.drive(1)), "Not enough fuel for this trip!", "Empty tank rejects trip");
        capture(() -> car.drive(0));
        capture(() -> car.refuel(0));
        equal(620, car.getMileage(), "Zero distance keeps mileage");
        equal(0, car.getFuelLevel(), "Zero refueling keeps fuel");

        capture(() -> car.refuel(50));
        equal(50, car.getFuelLevel(), "Exact capacity refueling");
        contains(capture(() -> car.refuel(1)), "Tank is full, extra fuel discarded.", "Full tank overflow");
        invalid(() -> car.drive(-1), "Negative distance");
        invalid(() -> car.refuel(-1), "Negative fuel");
        invalid(() -> car.drive(Double.NaN), "NaN distance");
        invalid(() -> car.refuel(Double.POSITIVE_INFINITY), "Infinite fuel");
        invalid(() -> new Car("X", "Y", 51, 50), "Invalid initial fuel");
        invalid(() -> new Car("X", "Y", 0, 0), "Invalid capacity");
        equal(50, car.getFuelLevel(), "Invalid operations preserve fuel");
        equal(620, car.getMileage(), "Invalid operations preserve mileage");
        System.out.println("Passed " + passed + " checks.");
    }

    private static String capture(Runnable action) {
        PrintStream original = System.out;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream captured = new PrintStream(buffer);
        try {
            System.setOut(captured);
            action.run();
        } finally {
            System.setOut(original);
            captured.close();
        }
        return buffer.toString();
    }

    private static void equal(double expected, double actual, String message) {
        check(Math.abs(expected - actual) < 1e-9, message);
    }

    private static void contains(String output, String text, String message) {
        check(output.contains(text), message);
    }

    private static void absent(String output, String text, String message) {
        check(!output.contains(text), message);
    }

    private static void invalid(Runnable action, String message) {
        try {
            action.run();
        } catch (IllegalArgumentException expected) {
            passed++;
            return;
        }
        throw new AssertionError(message);
    }

    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
        passed++;
    }
}
