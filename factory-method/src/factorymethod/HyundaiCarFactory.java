package factorymethod;

import java.util.HashMap;

public class HyundaiCarFactory extends CarFactory{

    HashMap<String, Car> carMap = new HashMap<>();

    @Override
    public Car createCar(String name) {
        Car car = null;

        if (name == "Sonata") {
            car = new Sonata();
        } else if (name == "Santafe") {
            car = new Santafe();
        }

        return car;
    }

    @Override
    public Car returnCar(String name) {
        Car car = carMap.get(name);

        if (car == null) {
            if (name.equals("Tomas")) {
                car = new Sonata();
            } else if (name.equals("James")) {
                car = new Santafe();
            }
            carMap.put(name, car);
        }

        return car;
    }
}
