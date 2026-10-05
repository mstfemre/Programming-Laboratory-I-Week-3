# Programming Laboratory I — Week 3

A Java exercise demonstrating a car's mileage, fuel consumption, refueling, and low-fuel status.

## Assignment

The `Car` class has `plateNumber`, `model`, `mileage`, `fuelLevel`, and `tankCapacity` attributes. Mileage starts at zero.

- `drive(km)` consumes exactly **1 liter per 10 km**. A trip with insufficient fuel is rejected without changing mileage or fuel.
- `refuel(amount)` adds fuel up to tank capacity, discarding any overflow with a message.
- `checkStatus()` prints mileage and fuel. A low-fuel warning appears **strictly below 10%** of tank capacity.

`Main` creates one car and demonstrates successful driving, insufficient fuel, normal refueling, overfilling, low fuel, and a trip that uses exactly the remaining fuel. Negative and non-finite inputs are rejected.

## Run

Requires JDK 8 or newer. No external libraries are needed.

```sh
javac -d build src/Car.java src/Main.java
java -cp build Main
```

## Test

```sh
javac -d build src/Car.java src/Main.java test/CarTest.java
java -cp build CarTest
```

The test program checks fuel and mileage changes, both required edge-case messages, the 10% warning boundary, an empty tank, exact-capacity refueling, and invalid inputs. It throws an `AssertionError` if a check fails.

## Files

- `src/Car.java`: car state and operations.
- `src/Main.java`: assignment demonstration.
- `test/CarTest.java`: standalone automated checks.

## Türkçe açıklama

Bu haftanın çalışması, araç sınıfı üzerinden nesne yönelimli programlamayı gösterir. Araç her 10 km'de 1 litre yakıt tüketir. Yakıt yetersizse yolculuk yapılmaz; fazla yakıt eklenirse depo kapasitesi aşılmaz. Yakıt depo kapasitesinin %10'unun altındayken uyarı verilir.
