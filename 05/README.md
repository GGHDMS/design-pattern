# 05. 분류 코드를 클래스로 만들기

## 분류 코드를 사용할 때의 문제점

- 기본 타입 분류 코드 : 객체의 종류를 특정 값으로 나타냄

- BOOK:0, FOOD:1, CLOTHE:2 등등...

- 예외의 값이 사용될 수 있음

- 다른 분류 코드와 혼란을 일으킴

- 컴파일시에 구별되지 않음

- 안좋은 예시

```
    public static final int BOOK = 0;
	public static final int FOOD = 1;
	public static final int CLOTHE = 2;


    new Catetegory(100);  //사용하지 않느 분류코드 값

```

## 리펙토링 단계

- 분류 코드를 클래스로 치환하기

1. 새로운 분류 코드 클래스를 작성하여 기존 코드에서 사용

2. 새로운 분류 코드 클래스에 새로운 메서드를 추가하고 이를 기존 코드에 적용

3. 기존 코드에서 사용하지 않는 메서드등은 삭제

## 리펙토링 전 

Item.java
```
public class Item {
	public static final int TYPECODE_BOOK = 0;
	public static final int TYPECODE_DVD = 1;
	public static final int TYPECODE_SOFTWARE = 2;
	
	private final int typeCode;
	private final String title;
	private final int price;
	
	
	public Item(int typeCode, String title, int price) {
		this.typeCode = typeCode;
		this.title = title;
		this.price = price;
	}
	
	public int getTypeCode() {
		return typeCode;
	}
	
	public String getTitle() {
		return title;
	} 
	
	public int getPrice() {
		return price;
	}
	
	public String toString() {
		return "[" + getTypeCode() + "," + getTitle() + "," + getPrice() + "]";
	}
}
```

Main.java
```
public class Main {

	public static void main(String[] args) {

		Item book = new Item(Item.TYPECODE_BOOK, "Demian", 20000);
		Item dvd = new Item(Item.TYPECODE_DVD, "King", 50000);
		Item soft = new Item(Item.TYPECODE_SOFTWARE, "Window", 30000);
		
		System.out.println(book);
		System.out.println(dvd);
		System.out.println(soft);
	}

}
```

## 새로운 클래스를 이용한 리펙토링 1단계

ItemType.java
```
public class ItemType {

	private int typeCode;
	
	public static final ItemType BOOK = new ItemType(0);
	public static final ItemType DVD = new ItemType(1);
	public static final ItemType SOFTWARE = new ItemType(2);
	
	private ItemType(int typeCode) {
		this.typeCode = typeCode;
	}
	
	public int getTypeCode() {
		return typeCode;
	}
	
	public static ItemType getTypeItem(int type) {
		
		switch (type) {
			case 0 : return BOOK;
			case 1 : return DVD;
			case 2 : return SOFTWARE;
			default : return null;
		}
			
	}
}
```

Item.java
```
public class Item {
	public static final int TYPECODE_BOOK = ItemType.BOOK.getTypeCode();
	public static final int TYPECODE_DVD = ItemType.DVD.getTypeCode();
	public static final int TYPECODE_SOFTWARE = ItemType.SOFTWARE.getTypeCode();
	
	//private final int typeCode;
	private final ItemType itemType;
	private final String title;
	private final int price;
	
	
	public Item(int typeCode, String title, int price) {
		this.itemType = ItemType.getTypeItem(typeCode);
		this.title = title;
		this.price = price;
	}
	
	public int getTypeCode() {
		return itemType.getTypeCode();
	}
	
	public String getTitle() {
		return title;
	} 
	
	public int getPrice() {
		return price;
	}
	
	public String toString() {
		return "[" + itemType.getTypeCode() + "," + getTitle() + "," + getPrice() + "]";
	}
}
```

Main.java
```
public class Main {

	public static void main(String[] args) {

		Item book = new Item(Item.TYPECODE_BOOK, "Demian", 20000);
		Item dvd = new Item(Item.TYPECODE_DVD, "King", 50000);
		Item soft = new Item(Item.TYPECODE_SOFTWARE, "Window", 30000);
		
		System.out.println(book);
		System.out.println(dvd);
		System.out.println(soft);
	}

}
```

## 기존 코드를 치환하는 리펙토링 2단계

ItemType.java
```
public class ItemType {

	private int typeCode;
	
	public static final ItemType BOOK = new ItemType(0);
	public static final ItemType DVD = new ItemType(1);
	public static final ItemType SOFTWARE = new ItemType(2);
	
	private ItemType(int typeCode) {
		this.typeCode = typeCode;
	}
	
	public int getTypeCode() {
		return typeCode;
	}

}
```

Item.java
```
public class Item {
	public static final int TYPECODE_BOOK = ItemType.BOOK.getTypeCode();
	public static final int TYPECODE_DVD = ItemType.DVD.getTypeCode();
	public static final int TYPECODE_SOFTWARE = ItemType.SOFTWARE.getTypeCode();
	
	private final ItemType itemType;
	private final String title;
	private final int price;
	
	
	public Item(ItemType itemType, String title, int price) {
		this.itemType = itemType;
		this.title = title;
		this.price = price;
	}
	
	public String getTitle() {
		return title;
	} 
	
	public int getPrice() {
		return price;
	}
	
	public String toString() {
		return "[" + itemType.getTypeCode() + "," + getTitle() + "," + getPrice() + "]";
	}
}
```

Main.java
```
public class Main {

	public static void main(String[] args) {

		Item book = new Item(ItemType.BOOK, "Demian", 20000);
		Item dvd = new Item(ItemType.DVD, "King", 50000);
		Item soft = new Item(ItemType.SOFTWARE, "Window", 30000);
		
		System.out.println(book);
		System.out.println(dvd);
		System.out.println(soft);
	}

}
```


