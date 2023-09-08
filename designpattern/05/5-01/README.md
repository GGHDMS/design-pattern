# Decorator Pattern

## 1. Decorator Pattern 이란?

- 장식과 실제 내용물을 동일시 

- 객체에 동적으로 책임을 추가

![decorator](./img/decorator.png)

## 2. 의도 (Intent)와 동기(Motivation)

- 상속을 사용하지 않고 기능의 유연한 확장이 가능한 패턴

- 객체에 동적으로 새로운 서비스를 추가 할 수 있음

- 전체가 아닌 개별적인 객체에 새로운 기능을 추가 할 수 있음

## 3. Class diagram

![decorator2](./img/decorator2.png)


## 4. 객체 협력 (collaborations)

- **Component** : 동적으로 추가할 서비스를 가질 수 있는 객체 정의

- **ConcreteComponent** : 추가적인 서비스가 필요한 실제 객체

- **Decorator** : Component의 참조자를 관리하면서 Component에 정의된 인터페이스를 만족하도록 정의

- **ConcreteDecorator** : 새롭게 추가되는 서비스를 실제 구현한 클래스로 addBehavior()를 구현한다.


## 5. 중요한 결론 (consequence)

- 단순한 상속보다 설계의 융통성을 증대

- Decorator의 조합을 통해 새로운 서비스를 지속적으로 추가할 수 있음

- 필요없는 경우 Decorator를 삭제할 수 있음

- Decorator와 실제 컴포넌트는 동일한 것이 아님

- 작은 규모의 객체들이 많이 생성될 수 있음

- 자바의 I/O 스트림 클래스는 Decorator 패턴임

## 6. 예제 

Coffee.java
```
public abstract class Coffee {
	
	public abstract void brewing();
}
```

EtiopiaAmericano.java
```
public class EtiopiaAmericano extends Coffee{

	@Override
	public void brewing() {
		System.out.print("EtiopiaAmericano ");
	}

}
```

KenyaAmericano.java
```
public class KenyaAmericano extends Coffee{

	@Override
	public void brewing() {
		System.out.print("KenyaAmericano ");
	}

}
```

Decorator.java
```
public abstract class Decorator extends Coffee{

	Coffee coffee;
	public Decorator(Coffee coffee){
		this.coffee = coffee;
	}
	
	@Override
	public void brewing() {
		coffee.brewing();
	}

}
```

Latte.java
```
public class Latte extends Decorator{

	public Latte(Coffee coffee) {
		super(coffee);
	}
	
	public void brewing() {
		super.brewing();
		System.out.print("Adding Milk ");
	}
}
```

Mocha.java
```
public class Mocha extends Decorator{

	public Mocha(Coffee coffee) {
		super(coffee);
		// TODO Auto-generated constructor stub
	}

	public void brewing() {
		super.brewing();
		System.out.print("Adding Mocha Syrup ");
	}
}
```

WhippedCream.java
```
public class WhippedCream extends Decorator{

	public WhippedCream(Coffee coffee) {
		super(coffee);
	}

	public void brewing() {
		super.brewing();
		System.out.print("Adding WhippedCream ");
	}
}
```

CoffeeTest.java
```
public class CoffeeTest {

	public static void main(String[] args) {

		Coffee kenyaAmericano = new KenyaAmericano();
		kenyaAmericano.brewing();
		System.out.println();
		
		Coffee kenyaLatte = new Latte(kenyaAmericano);
		kenyaLatte.brewing();
		System.out.println();
		
		Mocha kenyaMocha = new Mocha(new Latte(new KenyaAmericano()));
		kenyaMocha.brewing();
		System.out.println();
		
		WhippedCream etiopiaWhippedMocha = 
				new WhippedCream(new Mocha(new Latte( new EtiopiaAmericano())));
		etiopiaWhippedMocha.brewing();
		System.out.println();
		
	}

}
```
