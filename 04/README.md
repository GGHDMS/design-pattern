# 04. 널 객체 사용하기

## 널 객체 사용시 문제점

- 객체의 null 여부를 확인하는 코드가 너무 많은 경우

- 아무것도 안하는 객체를 만들어 사용함으로써 null 확인 코드를 줄인다.

- 안좋은 예시

```
    public String toString() {
		
		String result = "[ Person : ";
		
		result += " name =";
		if(name == null) {
			result += "\"(none)\"";
		}
		else {
			result += name;
		}
		
		result += " mail =";
		if(mail == null) {
			result += "\"(none)\"";
		}
		else {
			result += mail;
		}
		
		result += " ]";
		
		return result;
		
		
	}
```

## 리펙토링 단계

- 널 객체 사용하기

1. 널 객체 클래스를 작성한다.
  
	기존 null을 확인하는 클래스의 하위 클래스로 작성

	isNull() 메서드 작성 : 기존 클래스는 false를 널 클래스는 true를 반환한다.

2. null 치환하기

	null 코드 부분을 isNull() 메서드로 치환한다

3. 널 객체 클래스를 재정의하여 조건 판단하기

	isNulll() 메서드를 사용하는 제어문을 찾아서 다음과 같이 수정
   
 
	if(obj.isNull()){

	//null에 해야할 일  doing()
	}
	else{
		obj.doing();
	}
 
	널 클래스에 doing() 메서드를 오버라이딩하여 null에 해당하는 코드 작성


	=> obj.doing(); 

## 리펙토링 전

Label.java
```
public class Label {

	private String label;
	
	public Label(String label) {
		this.label = label;
	}
	
	public void display() {
		System.out.println("display :" + label);
	}
	
	public String toString() {
		return "\"" + label + "\"";
	}
}
```

Person.java
```
public class Person {

	private Label name;
	private Label mail;
	
	public Person(Label name, Label mail) {
		this.name = name;
		this.mail = mail;
	}
	
	public Person(Label name) {
		this(name, null);
	}
	
	public void display() {
		if(name != null) {
			name.display();
		}
		
		if(mail !=null) {
			mail.display();
		}
	}
	
	public String toString() {
		
		String result = "[ Person : ";
		
		result += " name =";
		if(name == null) {
			result += "\"(none)\"";
		}
		else {
			result += name;
		}
		
		result += " mail =";
		if(mail == null) {
			result += "\"(none)\"";
		}
		else {
			result += mail;
		}
		
		result += " ]";
		
		return result;
		
		
	}
}
```

Main.java
```
public class Main {

	public static void main(String[] args) {

		Person people[] = {
				new Person(new Label("Alice"), new Label("alice@aaa.com")),
				new Person(new Label("James"), new Label("james@aaa.com")),
				new Person(new Label("Tomas")) 
		};
		
		for(Person person : people) {
			System.out.println(person.toString());
			person.display();
			System.out.println();
		}
	}

}
```

## 리펙토링 1단계 (널 클래스 만들기)

NullLabel.java
```
public class NullLabel extends Label{

	public NullLabel() {
		super("(none)");
	}

   
	
}
```

## 리펙토링 2단계 ( isNull() 메서드 추가)

Label.java
```
public class Label {

	private String label;
	
	public Label(String label) {
		this.label = label;
	}
	
	public void display() {
		System.out.println("display :" + label);
	}
	
	public String toString() {
		return "\"" + label + "\"";
	}
	
	public boolean isNull() {
		return false;
	}
}
```


NullLabel.java
```
public class NullLabel extends Label{

	public NullLabel() {
		super("(none)");
	}

    @Override
	public boolean isNull() {
		return true;
	}
}
```

## 리펙토링 3단계 ( null  치환)
Person.java
```
public void display() {
		if(!name.isNull()) {
			name.display();
		}
		
		if(!mail.isNull()) {
			mail.display();
		}
	}
}

public String toString() {
		
		String result = "[ Person : ";
		
		result += " name =";
		if(name.isNull()) {
			result += "\"(none)\"";
		}
		else {
			result += name;
		}
		
		result += " mail =";
		if(mail.isNull()) {
			result += "\"(none)\"";
		}
		else {
			result += mail;
		}
		
		result += " ]";
		
		return result;
		
		
	}

}
```

## 리펙토링 4단계 (isNull() 메서드를 사용하는 조건 판단하기)

Label.java
```
public class NullLabel extends Label{

	public NullLabel() {
		super("(none)");
	}

	@Override
	public boolean isNull() {
		return true;
	}

	@Override
	public void display() {

	}
}
```


Person.java
```
public class Person {

	private Label name;
	private Label mail;
	
	public Person(Label name, Label mail) {
		this.name = name;
		this.mail = mail;
	}
	
	public Person(Label name) {
		this(name, new NullLabel());
	}
	
	public void display() {
		name.display();
		mail.display();
	}
	
	public String toString() {
		
		String result = "[ Person : name = " + name + " mail= " + mail +" ]";
		
		return result;
		
	}
}
```
