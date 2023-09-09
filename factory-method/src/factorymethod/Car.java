package factorymethod;

public abstract class Car {

    String carType;

    @Override
    public String toString() {
        return "Car{" +
                "carType='" + carType + '\'' +
                '}';
    }
}
