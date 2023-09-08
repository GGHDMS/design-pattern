# 03. 제어를 위한 플래그 삭제하기

## 제어 플래그의 문제점

- 플래그 : 상태를 기록하고 처리의 흐름을 제어하기 위한 boolean 타입의 변수

- 플래그를 잘못 사용하면 처리의 흐름을 이해하기 어려움

- 제어 플래그를 삭제하고, break, return등의 제어문으로 교체

- 안좋은 예시

```
for(int i=0; i<data.length; i++) {
			if(data[i] == number) {
				flag = true;
			}
		}

```
  for문의 조건이 끝나도 계속 순환이 일어남

## 리펙토링 단계

- 제어 플래그 삭제

1. 제어 플래그의 적절한 변수 이름을 찾는다

2. 제어 플래그를 삭제하고 break, continue 등을 사용한다.

3. 플래그로 제어되는 반복분을 찾아 반환값으로 하여 return 한다.

## FindInt 예제 리펙토링 전

FindInt.java
```
public class FindInt {
	
	public static boolean find(int[] data, int number) {
		
		boolean flag = false;
		
		for(int i=0; i<data.length; i++) {
			if(data[i] == number) {
				flag = true;
			}
		}	
		
		return flag;
	}
	
}
```
FindIntMain.java
```
package removecontrolflag.before;

public class FindIntMain {

	public static void main(String[] args) {

		int[] data = {1,2,3,4,5,6,7,8,9,10};
		
		if( FindInt.find(data, 10)) {
			System.out.println("found!!!");
		}
		else System.out.println("not found!!");
	}

}
```

## FindInt 예제 리펙토링 후

FindInt.java
```
public class FindInt {
	
	public static boolean find(int[] data, int number) {
		
		boolean found = false;
		
		for(int i=0; i<data.length; i++) {
			if(data[i] == number) {
				found = true;
				//break;
				return found;
			}
		}	
		
		return found;
	}
	
}
```

FindIntMain.java
```
public class FindIntMain {

	public static void main(String[] args) {

		int[] data = {1,2,3,4,5,6,7,8,9,10};
		
		if( FindInt.find(data, 10)) {
			System.out.println("found!!!");
		}
		else System.out.println("not found!!");
	}
}
```

## SimpleDataBase 예제 리펙토링 전

SimpleDataBase.java
```
public class SimpleDataBase {

	private Map<String, String> map = new HashMap<String, String>();
	
	public SimpleDataBase(Reader reader) throws IOException {
		BufferedReader br = new BufferedReader(reader);
		
		boolean flag = false;
		String temp;
		
		while( !flag ) {
			
			temp = br.readLine();
			if(temp == null) {
				flag = true;
				
			}
			else {
				boolean flag2 = true;
				
				StringBuffer sb1 = new StringBuffer();
				StringBuffer sb2 = new StringBuffer();
					
				for(int i = 0; i<temp.length(); i++) {
					char temp2 = temp.charAt(i);
					
					if(flag2) {
						if(temp2 == '=') {
							flag2 = false;
						}
						else {
							sb1.append(temp2);
						}
					}
					else {
						sb2.append(temp2);
					}
			    }
				
				String ss1 = sb1.toString();
				String ss2 = sb2.toString();
				map.put(ss1, ss2);
			}
		}
	}
	
	public Iterator<String> iterator(){
		return map.keySet().iterator();
	}
	
	public String getValue(String key) {
		return map.get(key);
	}
	
	public String toString() {
		return map.toString();
	}
}
```

Main.java
```
public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		SimpleDataBase simpleDatabase = new SimpleDataBase(new FileReader("datafile.txt"));
		
		Iterator<String> ir = simpleDatabase.iterator();
		
		while(ir.hasNext()) {
			String key = ir.next();
			System.out.println("key:" + key);
			System.out.println("value :" + simpleDatabase.getValue(key));
		}

	}

}
```

## SimpleDataBase 예제 리펙토링 1단계 후 (이상한 변수명 바꾸기)
```
public class SimpleDataBase {

	private Map<String, String> map = new HashMap<String, String>();
	
	public SimpleDataBase(Reader reader) throws IOException {
		BufferedReader br = new BufferedReader(reader);
		
		boolean reading = false;
		String line;
		
		while( !reading ) {
			
			line = br.readLine();
			if(line == null) {
				reading = true;
				
			}
			else {
				boolean scanningKey = true;
				
				StringBuffer keyBuffer = new StringBuffer();
				StringBuffer valueBuffer = new StringBuffer();
					
				for(int i = 0; i< line.length(); i++) {
					char c = line.charAt(i);
					
					if(scanningKey) {
						if(c == '=') {
							scanningKey = false;
						}
						else {
							keyBuffer.append(c);
						}
					}
					else {
						valueBuffer.append(c);
					}
			    }
				
				String key = keyBuffer.toString();
				String value = valueBuffer.toString();
				map.put(key, value);
			}
		}
	}
	
	public Iterator<String> iterator(){
		return map.keySet().iterator();
	}
	
	public String getValue(String key) {
		return map.get(key);
	}
	
	public String toString() {
		return map.toString();
	}
}
```

## SimpleDataBase 예제 리펙토링 2단계 후 (라이브러리 활용)
```
public class SimpleDataBase {

	private Map<String, String> map = new HashMap<String, String>();
	
	public SimpleDataBase(Reader reader) throws IOException {
		BufferedReader br = new BufferedReader(reader);
		
		boolean reading = false;
		String line;
		
		while( !reading ) {
			
			line = br.readLine();
			if(line == null) {
				break;
			}
			
			int index = line.indexOf('=');
			
			if(index > 0) {
				String key = line.substring(0, index);
				String value = line.substring(index+1, line.length());
				
				map.put(key, value);
			}
			
		}
	}
	
	public Iterator<String> iterator(){
		return map.keySet().iterator();
	}
	
	public String getValue(String key) {
		return map.get(key);
	}
	
	public String toString() {
		return map.toString();
	}
}
```

## 괜챦은 플래그 이름들

- initialize, debug, error, isExist, interrupted 등
