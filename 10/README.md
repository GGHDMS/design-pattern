# 10. 에러를 예외처리 하기


## 에러를 처리하는 방법

- 정상적인 처리와 에러에 대한 내용이 코드에 같이 혼재함

- 에러 코드 상황이 점점 추가될 수 있음

- 에러 상황을 Exeption으로 만들고 try-catch 블럭을 이용하여 예외 처리 하고 로그도 남김

## 리펙토링 전

Password.java
```
public class Password {

		private String password;
		
		public String getPassword(){
			return password;
		}
		
		public void setPassword(String password) {
			
			if(password == null){
				 System.out.println( "비밀번호는 null 일 수 없습니다");
				 return;
			}
			else if( password.length() < 5){
				System.out.println( "비밀번호는 5자 이상이어야 합니다.");
				return;
			}
			else if (password.matches("[a-zA-Z]+")){
				System.out.println("비밀번호는 숫자나 특수문자를 포함해야 합니다.");
				return;
			}
			else {
				System.out.println("오류 없음");
			}
			
			this.password = password;
		}
}
```

Main.java
```
public class Main {

	public static void main(String[] args) {

		Password test = new Password();
		String password = null;
		test.setPassword(password);
		
		password = "abcd";
		test.setPassword(password);
		
		password = "abcde";
		test.setPassword(password);
		
		password = "abcde#1";
		test.setPassword(password);
	}
}
```

## 리펙토링 후 - 예외 클래스를 만들고 이를 처리함

PassWord.java
```
public class Password {

		private String password;
		
		public String getPassword(){
			return password;
		}
		
		public void setPassword(String password) throws PasswordException{
			
			if(password == null){
				throw new PasswordException("비밀번호는 null 일 수 없습니다");
			}
			else if( password.length() < 5){
				throw new PasswordException("비밀번호는 5자 이상이어야 합니다.");
			}
			else if (password.matches("[a-zA-Z]+")){
				throw new PasswordException("비밀번호는 숫자나 특수문자를 포함해야 합니다.");
			}
			
			this.password = password;
		}
		
}
```

PasswordException.java
```
public class PasswordException extends IllegalArgumentException{
	
	public PasswordException(String message) {
		super(message);
	}
}
```

Main.java
```
public class Main {
	
	public static void main(String[] args) {

		Password test = new Password();
		String password = null;
		try {
			test.setPassword(password);
		} catch (PasswordException e) {
			System.out.println(e.getMessage());
		}
		
		password = "abcd";
		try {
			test.setPassword(password);
		} catch (PasswordException e) {
			System.out.println(e.getMessage());
		}
		
		password = "abcde";
		try {
			test.setPassword(password);
		} catch (PasswordException e) {
			System.out.println(e.getMessage());
		}
		
		password = "abcde#1";
		try {
			test.setPassword(password);
			System.out.println("오류 없음");
		} catch (PasswordException e) {
			System.out.println(e.getMessage());
		}
	}
}
```
