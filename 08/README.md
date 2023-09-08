# 08. 상태,전략 패턴 사용하기


## 상태 패턴 (State Pattern)

- 클래스 내의 여러 메서드가 하나의 상태(멤버 변수)에 의존하여 switch 문이 만들어짐

- 하나의 메서드에 각 상태에 따라 수행하는 로직이 다름

- 각 상태를 클래스로 만들고, 수행하는 로직을 각 클래스에 작성한다.

- 여러 상태가 추가되는 경우 확장이 용이하고, 상태의 전환에 대해 인스턴스를 바꾸어주면 되므로 유지보수가 쉽다.

## 전략 패턴 (Strategy Pattern)

- 어떤 로직에 대해 여러 알고리즘 (전략, 정책)이 있을 수 있음

- 상황에 따라 전략을 교체할 수 있고, 전략이 추가될 수도 있음

- 각 전략이 해결해야 하는 동일한 메서드가 존재함

- 메서드에 전략에 따른 다른 수행이 있음

- 전략마다 클래스로 만들고, 각 클래스에서 해당하는 로직을 수행하도록 메서드 구현

## 상태 패턴과 전략 패턴 

- 두 패턴 모두 상속과 오버라이딩을 활용한 패턴

- 상태 패턴을 클래스의 상태(주로 변수)에 관련하여 여러 가지 수행이 있는 경우이고, 전략 패턴은 알고리즘이 여러개 존재하는 경우 임

## 리펙토링 전

Player.java
```


public class Player {
	
	public static final int BEGINNER_LEVEL = 1;
	public static final int ADVANCED_LEVEL = 2;
	public static final int SUPER_LEVEL = 3;
	private int level;
	
	public Player()
	{
		level = BEGINNER_LEVEL;
		showLevelMessage();
	}

	public int getLevel() {
		return level;
	}

	public void upgradeLevel() {
		if (level == BEGINNER_LEVEL) {
			level = ADVANCED_LEVEL;
		}
		else if (level == ADVANCED_LEVEL) {
			level = SUPER_LEVEL;
		}
		else {
			System.out.println("not support level");
		}
		showLevelMessage();
	}
	
	public void play(int count)
	{
		run();
		for(int i=0; i<count; i++){
			jump();
		}
		turn();
	}
	
	public void run() {
		if (level == BEGINNER_LEVEL) {
			System.out.println("천천히 달립니다..");
		}
		else if (level == ADVANCED_LEVEL) {
			System.out.println("빨리 달립니다");
		}
		else if(level == SUPER_LEVEL) {
			System.out.println("엄청 빨리 달립니다.");
		}
		else {
			System.out.println("not support level");
		}
		
	}
	
	public void jump() {
		if (level == BEGINNER_LEVEL) {
			System.out.println("Jump 할 줄 모르지롱.");
		}
		else if (level == ADVANCED_LEVEL) {
			System.out.println("높이 jump 합니다");
		}
		else if(level == SUPER_LEVEL) {
			System.out.println("엄청 높게 jump합니다.");
		}
		else {
			System.out.println("not support level");
		}
		
	}
	
	public void turn() {
		
		if (level == BEGINNER_LEVEL) {
			System.out.println("Turn 할 줄 모르지롱.");	
		}
		else if (level == ADVANCED_LEVEL) {
			System.out.println("Turn 할 줄 모르지롱.");		
		}
		else if(level == SUPER_LEVEL) {
			System.out.println("한 바퀴 돕니다.");		
		}
		else {
			System.out.println("not support level");
		}
	}
	
	
	public void showLevelMessage() {
		
		if (level == BEGINNER_LEVEL) {
			System.out.println("***** 초보자 레벨 입니다. *****");
		}
		else if (level == ADVANCED_LEVEL) {
			System.out.println("***** 중급자 레벨 입니다. *****");	
		}
		else if(level == SUPER_LEVEL) {
			System.out.println("***** 고급자 레벨 입니다. *****");
		}
		else {
			System.out.println("not support level");
		}
	}
}
```

MainBoard.java
```


public class MainBoard {

	public static void main(String[] args) {

		Player player = new Player();
		player.play(1);
		player.upgradeLevel();
		player.play(2);
		player.upgradeLevel();
		player.play(3);
		
	}
}
```

## 리펙토링 1단계 - 상위에 추상 클래스 만들고 하위 클래스에서 구현하기

PlayerLevel.java
```


public abstract class PlayerLevel {

	public abstract void run();
	public abstract void jump();
	public abstract void turn();
	public abstract void showLevelMessage();
	
	final public void go(int count)
	{
		run();
		for(int i=0; i<count; i++){
			jump();
		}
		turn();
	}
}
```

BegginerLevel.java
```


public class BeginnerLevel extends PlayerLevel{

	@Override
	public void run() {
		System.out.println("천천히 달립니다..");
		
	}

	@Override
	public void jump() {
		System.out.println("Jump 할 줄 모르지롱.");
	}

	@Override
	public void turn() {
		System.out.println("Turn 할 줄 모르지롱.");		
	}

	@Override
	public void showLevelMessage() {
		System.out.println("***** 초보자 레벨 입니다. *****");
	}

}
```

AdvancedLevel.java
```


public class AdvancedLevel extends PlayerLevel{
	@Override
	public void run() {
		System.out.println("빨리 달립니다");
		
	}

	@Override
	public void jump() {
		System.out.println("높이 jump 합니다");
	}

	@Override
	public void turn() {
		System.out.println("Turn 할 줄 모르지롱.");		
	}

	@Override
	public void showLevelMessage() {
		System.out.println("***** 중급자 레벨 입니다. *****");
	}
}
```

SuperLevel.java
```


public class SuperLevel extends PlayerLevel{
	@Override
	public void run() {
		System.out.println("엄청 빨리 달립니다.");
		
	}

	@Override
	public void jump() {
		System.out.println("엄청 높게 jump합니다.");
	}

	@Override
	public void turn() {
		System.out.println("한 바퀴 돕니다.");		
	}

	@Override
	public void showLevelMessage() {
		System.out.println("***** 고급자 레벨 입니다. *****");
	}

}
```

Player.java
```


public class Player {
	
	private PlayerLevel level;
	
	public Player()
	{
		level= new BeginnerLevel();
		level.showLevelMessage();
	}

	public PlayerLevel getLevel() {
		return level;
	}

	public void upgradeLevel(PlayerLevel level) {
		this.level = level;
		level.showLevelMessage();
	}
	
	public void play(int count){
		level.go(count);
	}
}
```

MainBoard.java
```


public class MainBoard {

	public static void main(String[] args) {

		Player player = new Player();
		player.play(1);
		AdvancedLevel aLevel = new AdvancedLevel();
		player.upgradeLevel(aLevel);
		player.play(2);
		SuperLevel sLevel = new SuperLevel();
		player.upgradeLevel(sLevel);
		player.play(3);
		
	}
}
```

## 리펙토링 2단계 - 각 레벨 클래스에 싱글톤 패턴 적용하기


BeginnerLevel.java
```
public class BeginnerLevel extends PlayerLevel{

	private static BeginnerLevel instance = new BeginnerLevel();
	
	private BeginnerLevel() {
		
	}
	
	public static BeginnerLevel getInstance() {
		return new BeginnerLevel();
	}
	@Override
	public void run() {
		System.out.println("천천히 달립니다..");
		
	}

	@Override
	public void jump() {
		System.out.println("Jump 할 줄 모르지롱.");
	}

	@Override
	public void turn() {
		System.out.println("Turn 할 줄 모르지롱.");		
	}

	@Override
	public void showLevelMessage() {
		System.out.println("***** 초보자 레벨 입니다. *****");
	}

}
```

MainBoard.java
```


public class MainBoard {

	public static void main(String[] args) {

		Player player = new Player();
		player.play(1);
		AdvancedLevel aLevel = AdvancedLevel.getInstance();
		player.upgradeLevel(aLevel);
		player.play(2);
		SuperLevel sLevel = SuperLevel.getInstance();
		player.upgradeLevel(sLevel);
		player.play(3);
		
	}
}
```
