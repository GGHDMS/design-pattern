# 07. 생성자를 팩토리 메서드로 바꾸기


## 생성자로 인스턴스를 생성하고 싶지 않을 때

- new 키워드로 객체를 생성하면 클래스의 이름이 노출됨

   Employee employee = new Employee();

- 생성되는 클래스의 이름을 숨거나, 상황에 따라 인스턴스의 생성이 가능하도록 하고자 할때

- 컴파일 시간이 아닌 실행시간에 생성되는 인스턴스가 결정되어야 할 때

## 리펙토링 이전 코드

Customer.java
```
package fatorymethod.before;

public class Customer {

	private int customerType;
	private String customerName;
	private String customerGrade;
	private int bonusPoint;
	
	public static final int BRONZE_CUSTOMER = 0; 
	public static final int SILVER_CUSTOMER = 1;
	public static final int GOLD_CUSTOMER = 2;
	
	
	Customer( int customerType, String customerName){
		this.customerType = customerType;
		this.customerName = customerName;
	}
	
	
	public String getCustomerGrade() {
		
		switch(customerType) {
			case BRONZE_CUSTOMER : return "BRONZE";
			case SILVER_CUSTOMER : return "SILVER";
			case GOLD_CUSTOMER : return "GOLD";
			default : return null;
		}
	}
	
	public int calcPrice(int price) {
		switch(customerType) {
			case BRONZE_CUSTOMER :
				return price;
			case SILVER_CUSTOMER : 
				return price - (int)(price * 0.05);
			case GOLD_CUSTOMER :
				return price - (int)(price * 0.1);
			default : return price;
	
		}
	}
	
	public int calcBonusPoint(int price) {
		switch(customerType) {
			case BRONZE_CUSTOMER :
				return bonusPoint += (price * 0.01);
			case SILVER_CUSTOMER : 
				return bonusPoint += (price * 0.05);
			case GOLD_CUSTOMER :
				return bonusPoint += (price * 0.1);
		default : return price;

		}
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public String toString() {
		return customerName + "님의 멤버십 등급은 " + getCustomerGrade() + "입니다.";
	}
}
```

Main.java
```
package fatorymethod.before;

public class Main {

	public static void main(String[] args) {

		Customer bronzeCustomer = new Customer(Customer.BRONZE_CUSTOMER, "Tomas");
		Customer silverCustomer = new Customer(Customer.SILVER_CUSTOMER, "Alice");
		Customer goldCustomer = new Customer(Customer.GOLD_CUSTOMER, "Edward");
		
		int price = 10000;
		
		System.out.println(bronzeCustomer);
		System.out.println(bronzeCustomer.getCustomerName() + ": price :" + bronzeCustomer.calcPrice(price) 
		           + ": point :" + bronzeCustomer.calcBonusPoint(price));
		System.out.println(silverCustomer);
		System.out.println(silverCustomer.getCustomerName() + ": price :" + silverCustomer.calcPrice(price) 
        + ": point :" + silverCustomer.calcBonusPoint(price));

		System.out.println(goldCustomer);
		System.out.println(goldCustomer.getCustomerName() + ": price :" + goldCustomer.calcPrice(price) 
        + ": point :" + goldCustomer.calcBonusPoint(price));

	}
}
```

## 리펙토링 1단계 - 팩토리 메서드 구현하기

```
public class Customer {

	private int customerType;
	private String customerName;
	private String customerGrade;
	private int bonusPoint;
	
	public static final int BRONZE_CUSTOMER = 0; 
	public static final int SILVER_CUSTOMER = 1;
	public static final int GOLD_CUSTOMER = 2;
	
	private Customer( int customerType, String customerName){
		this.customerType = customerType;
		this.customerName = customerName;
	}
	
	public static Customer create(int customerType, String customerName)
	{
		return new Customer(customerType, customerName);
	}
	
	public String getCustomerGrade() {
		
		switch(customerType) {
			case BRONZE_CUSTOMER : return "BRONZE";
			case SILVER_CUSTOMER : return "SILVER";
			case GOLD_CUSTOMER : return "GOLD";
			default : return null;
		}
	}
	
	public int calcPrice(int price) {
		switch(customerType) {
			case BRONZE_CUSTOMER :
				return price;
			case SILVER_CUSTOMER : 
				return price - (int)(price * 0.05);
			case GOLD_CUSTOMER :
				return price - (int)(price * 0.1);
			default : return price;
	
		}
	}
	
	public int calcBonusPoint(int price) {
		switch(customerType) {
			case BRONZE_CUSTOMER :
				return bonusPoint += (price * 0.01);
			case SILVER_CUSTOMER : 
				return bonusPoint += (price * 0.05);
			case GOLD_CUSTOMER :
				return bonusPoint += (price * 0.1);
		default : return price;

		}
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	public String toString() {
		return customerName + "customerName + "님의 멤버십 등급은 " + getCustomerGrade() + "입니다.";
	}
}
```

Main.java
```
package factorymethod.after1;

public class Main {

	public static void main(String[] args) {

		Customer bronzeCustomer = Customer.create(Customer.BRONZE_CUSTOMER, "Tomas");
		Customer silverCustomer = Customer.create(Customer.SILVER_CUSTOMER, "Alice");
		Customer goldCustomer = Customer.create(Customer.GOLD_CUSTOMER, "Edward");
		
		int price = 10000;
		
		System.out.println(bronzeCustomer);
		System.out.println(bronzeCustomer.getCustomerName() + ": price :" + bronzeCustomer.calcPrice(price) 
		           + ": point :" + bronzeCustomer.calcBonusPoint(price));
		System.out.println(silverCustomer);
		System.out.println(silverCustomer.getCustomerName() + ": price :" + silverCustomer.calcPrice(price) 
        + ": point :" + silverCustomer.calcBonusPoint(price));

		System.out.println(goldCustomer);
		System.out.println(goldCustomer.getCustomerName() + ": price :" + goldCustomer.calcPrice(price) 
        + ": point :" + goldCustomer.calcBonusPoint(price));

	}
}
```

## 리펙토링 2단계 - 하위 클래스로 분리 하기

Cutsomer.java
```
package factorymethod.after2;

public abstract class Customer {

	private String customerName;
	protected int bonusPoint;

	
	protected Customer( String customerName){
		this.customerName = customerName;
	}
	
	public abstract String getCustomerGrade();
	public abstract int calcPrice(int price);
	public abstract int calcBonusPoint(int price);
	
	public String getCustomerName() {
		return customerName;
	}
	
	public String toString() {
		return customerName + "님의 멤버십 등급은 " + getCustomerGrade() + "입니다.";
	}
}
```

BronzeCustomer.java
```
package factorymethod.after2;

public class BronzeCustomer extends Customer{

	
	private BronzeCustomer( String customerName) {
		super( customerName);
	}
	
	public static BronzeCustomer create(String customerName) {
		return new BronzeCustomer(customerName);
	}

	public int calcPrice(int price) {
		return price;
	}

	public String getCustomerGrade() {
		return "BRONZE";
	}
	
	public int calcBonusPoint(int price) {
		return bonusPoint += (price * 0.01);
	}

}
```

SilverCustomer.java
```
public class SilverCustomer extends Customer{

	SilverCustomer(String customerName) {
		super(customerName);
	}

	public static SilverCustomer create(String customerName) {
		return new SilverCustomer(customerName);
	}
	
	@Override
	public String getCustomerGrade() {
		return "SILVER";
	}

	@Override
	public int calcPrice(int price) {
		return price - (int)(price * 0.05);
	}

	@Override
	public int calcBonusPoint(int price) {
		return bonusPoint += (price * 0.05);
	}

}
```

Goldcustomer.java
```
package factorymethod.after2;

public class GoldCustomer extends Customer{

	private GoldCustomer(String customerName) {
		super(customerName);
	}

	public static GoldCustomer create(String customerName) {
		return new GoldCustomer(customerName);
	}
	
	@Override
	public String getCustomerGrade() {
		return "GOLD";
	}

	@Override
	public int calcPrice(int price) {
		return price - (int)(price * 0.1);
	}

	@Override
	public int calcBonusPoint(int price) {
		return bonusPoint += (price * 0.1);
	}

}
```

Main.java
```
package factorymethod.after2;

public class Main {

	public static void main(String[] args) {

		Customer bronzeCustomer = BronzeCustomer.create("Tomas");
		Customer silverCustomer = SilverCustomer.create("Alice");
		Customer goldCustomer = GoldCustomer.create("Edward");
		
		int price = 10000;
		
		System.out.println(bronzeCustomer);
		System.out.println(bronzeCustomer.getCustomerName() + ": price :" + bronzeCustomer.calcPrice(price) 
		           + ": point :" + bronzeCustomer.calcBonusPoint(price));
		System.out.println(silverCustomer);
		System.out.println(silverCustomer.getCustomerName() + ": price :" + silverCustomer.calcPrice(price) 
        + ": point :" + silverCustomer.calcBonusPoint(price));

		System.out.println(goldCustomer);
		System.out.println(goldCustomer.getCustomerName() + ": price :" + goldCustomer.calcPrice(price) 
        + ": point :" + goldCustomer.calcBonusPoint(price));

	}
}
```

## 생각해보기

- 리펙토링은 항상 해야 하는것인가? 

- switch로 분기하는것과 create 메서드를 분리하는것 어느것이 더 유용한가?

- 여러가지 고려 요소가 필요 ( 가령, 앞으로 고객의 등급이 더 늘어날 것인가? 등)









