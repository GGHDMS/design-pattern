# 02. 매직넘버를 상수로 바꾸기

## 매직 넘버를 사용할 때의 문제점

- 의미를 알기 어렵다.

- 수정하기 어렵다.

- 상수로 치환하여 리펙토링 한다.

## 리펙토링 단계

- 매직넘버를 기호 상수로 치환

1. 기호 상수 선언하기
	기호 상수 선언
	매직 넘버를 기호 상수로 치환
	기호 상수에 의존하는 다른 매직 넘저를 찾아 기호 상수로 바꿈

2. 테스트

## 리펙토링 전

Robot.java
```
package magicnumber.before;

public class Robot {
	
	private String name;
	
	public Robot(String name) {
		this.name = name;
	}
	
	public void order(int command) {
		
		if(command == 0) {  // 걷기
			System.out.println("walk");
		}
		else if( command == 1) {  //멈추기 
			System.out.println("stop");
		}
		else if(command == 2) { // 점프 
			System.out.println("jump");
		
		}
		else {
			System.out.println("error");
		}
	}

}
```

RobotMain.java
```
package magicnumber.before;

public class RobotMain {

	public static void main(String[] args) {

		Robot robot = new Robot("Edward");
		robot.order(0);
		robot.order(1);
		robot.order(2);
	}

}
```

## 리펙토링 후

Robot.java
```
package magicnumber.after;

public class Robot {
	
	private String name;
	
	
	//public static final int COMMAND_WALK = 0;
	//public static final int COMMAND_STOP = 1;
	//public static final int COMMAND_JUMP = 2;
	
	public enum Command {
		WALK, 
		STOP,
		JUMP,
	};
	
	public Robot(String name) {
		this.name = name;
	}
	
	public void order(Robot.Command command) {
		
		if( command == Command.WALK ) {  // 걷기
			System.out.println("walk");
		}
		else if( command == Command.STOP) {  //멈추기 
			System.out.println("stop");
		}
		else if(command == Command.JUMP ) { // 점프  
			System.out.println("jump");
		}
		else {
			System.out.println("error");
		}
	}

}
```

RobotMain.java
```
package magicnumber.after;

public class RobotMain {

	public static void main(String[] args) {

		Robot robot = new Robot("Edward");
		robot.order(Robot.Command.WALK);
		robot.order(Robot.Command.STOP);
		robot.order(Robot.Command.JUMP);
	}

}
```
