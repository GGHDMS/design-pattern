# Factory Method Pattern

## 1. Factory Method Pattern 이란?

- 인스턴스 작성을 하위 클래스에게 위임.

- Template Method 패턴을 인스턴스 생성에 적용

![factory1](./img/factory1.png)

## 2. 의도 (Intent)와 동기(Motivation)

- 객체를 생성하기 위한 인터페이스를 정의하지만, 어떤 클래스의 인스턴스를 생성할지에 대한 결정은 서브클래스에서 결정하게 함.

- 여러 상황에 따라 각각 생성될 수 있는 객체에 대한 생성을 하위 클래스에 위임

- 생성과 관련된 동일한 메서드는 상위 클래스에서 처리
    
## 3. Class diagram

![factory2](./img/factory2.png)

## 4. 객체 협력 (collaborations)

- Product

 팩토리 메소드가 생성하는 객체의 인터페이스를 정의한다.

- ConcreteProduct

 Product 클래스에 정의된 인터페이스를 실제로 구현한다.

- Creator

 Product 타입의 객체를 반환하는 팩토리 메소드를 선언한다. Creator 클래스는 팩토리 메소드를 기본적으로 구현하는데, 이 구현에서는 ConcreateProduct 객체를 반환한다. 

 Product 객체의 생성을 위해 팩토리 메소드를 호출한다.

- ConcreteCreator

ConcreteProduct 의 인스턴스를 반환하기 위해 팩토리 메소드를 재정의 한다.


## 5. 중요한 결론 (consequence)

- 상황에 따라 다양한 인스턴스 생성을 할 수 있음



## 6. 예제 

Car.java
```
public abstract class Car {
	
	String carType;
	
	public String toString() {
		return carType;
	}

}
```

CarFactory.java
```
public abstract class CarFactory {

	
	public abstract Car createCar(String name);  // add - on

	public abstract Car returnMyCar(String name);
}
```

```
public class HyundaiFactory extends CarFactory{

	HashMap<String, Car> carMap = new HashMap<String, Car>();
	
	@Override
	public Car createCar(String name) {

		Car car = null;
		
		if (name == "sonata") {
			car = new Sonata();
		}
		else if( name == "santafe") {
			car =  new Santafe();
		}
		
		return car;
	}
	
	@Override
	public Car returnMyCar(String name) {
		
	// Jame는 Sonata, Tomas는 Santafe 인 경우
		
		Car myCar = carMap.get(name);
		if(myCar == null) {
			
			if(name.equals("James")){
				myCar = new Sonata();
			}
			else if(name.equals("Tomas") ){
				myCar = new Santafe();
			}
			carMap.put(name, myCar);
		}
		
		return myCar;
		
	}

}
```

Sonata.java
```
public class Sonata extends Car{

	
	Sonata(){
		carType = "Sonata";
	}

}
```

Santafe.java
```
public class Santafe extends Car{

	Santafe(){
		carType = "Santafe";
	}
}
```

CarTest.java
```
public class CarTest {

	public static void main(String[] args) {

		CarFactory factory = new HyundaiFactory();
		Car newCar = factory.createCar("sonata");
		
		System.out.println(newCar);
		
		
		Car myCar = factory.returnMyCar("Tomas");
		Car hisCar = factory.returnMyCar("Tomas");
		System.out.println(myCar == hisCar);
				
	}

}
```
