# 06. if-else if, switch 문의 분류 코드를 하위클래스로 만들기


## if - else if, switch 문으로 분류 코드를 사용하는 문제점

- 분류 코드에 따른 반복적인 문장이 발생

- 분류 코드의 내용이 추가되는 경우 여러 메서드를 수정해야 함

- 분류 코드에 따른 기능을 하위 클래스로 이전하여 오버라이딩 할 수 있음

## 리펙토링 이전 코드

Customer.java

```
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

## 리펙토링 1 단계 - 상속으로 하위 클래스를 생성하고 메서드 내리기

Customer.java
```
public abstract class Customer {

	private String customerName;
	protected int bonusPoint;

	Customer( String customerName){
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
public class BronzeCustomer extends Customer{

	
	BronzeCustomer( String customerName) {
		super( customerName);
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
		// TODO Auto-generated constructor stub
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

GoldCustomer.java
```
package typdecodewithsubclass.after;

public class GoldCustomer extends Customer{

	GoldCustomer(String customerName) {
		super(customerName);
		// TODO Auto-generated constructor stub
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
public class Main {

	public static void main(String[] args) {

		Customer bronzeCustomer = new BronzeCustomer("Tomas");
		Customer silverCustomer = new SilverCustomer("Alice");
		Customer goldCustomer = new GoldCustomer("Edward");
		
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


## 리펙토링 3단계 - 불필요한 코드 삭제
```
public abstract class Customer {

	private String customerName;
	protected int bonusPoint;

	Customer( String customerName){
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



##
