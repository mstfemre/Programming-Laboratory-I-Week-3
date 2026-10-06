# Programming Laboratory I - Week 3

A basic Java program using a Car class.

The car has a plate number, model, mileage, fuel level and tank capacity.

- drive(km) uses 1 liter of fuel for every 10 km. If there is not enough fuel, the car does not move.
- refuel(amount) adds fuel without exceeding the tank capacity.
- checkStatus() shows mileage and fuel, with a warning below 10% of tank capacity.

Main.java creates one car and demonstrates normal driving and refueling, insufficient fuel, tank overflow and the low-fuel warning.

The example assumes valid starting values and nonnegative distances and fuel amounts.

## Run

```sh
javac -d build src/Car.java src/Main.java
java -cp build Main
```

Requires JDK 8 or newer.
