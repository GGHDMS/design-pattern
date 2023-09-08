# Proxy Pattern

## 1. Proxy Pattern 이란?

- 객체에 대한 접근을 제어하기 위해 대리자를 둔다


## 2. 의도 (Intent)와 동기(Motivation)

- 어떤 객체가 생성에 초기화 비용이 많이 들거나 복잡한 경우 간단한 처리는 대리자를 통해 하게한다.

- 실제적으로 비용이 많이 들어가는 처리일 때 객체를 만들도록 한다.


## 3. 객체 종류

- 원격지 프록시 (remote proxy) : 서로 다른 주소 공간에 존재하는 객체를 대리하는 로컬 객체

- 가상 프록시 ( virtual proxy) : 고비용의 객체는 필요한 경우만 생성

- 보호용 프록시 (protection proxy) : 실제 객체에 대한 접근 권한을 제어하기 위한 경우 사용

## 4. 객체 협력 (collaborations)

- **Proxy**

실제 참조할 대상을 관리

실제 객체와 동일한 메서드 제공

클라이언트에게 먼저 노출되는 객체

- **RealSubject**

프록시가 대리하는 실제 객체

- **Subject**

Proxy와 Real의 공통 메서드 선언

## 5. 중요한 결론 (consequence)

- 프록시 패턴은 객체에 접근하는데 보호, 가상등의 역할을 한다.

## 6. 예제 

Printable.java
```
public interface Printable {
    public abstract void setPrinterName(String name);   // 이름의 설정
    public abstract String getPrinterName();            // 이름의 취득
    public abstract void print(String string);          // 문자열 표시(프린트아웃)
}
```

PrinterProxy.java
```
public class PrinterProxy implements Printable {
    private String name;            // 이름
    private Printer real;           // "본인"
    public PrinterProxy() {
    }
    public PrinterProxy(String name) {      // 생성자
        this.name = name;
    }
    public synchronized void setPrinterName(String name) {  // 이름의 설정
        if (real != null) {
            real.setPrinterName(name);  // "본인"에게도 설정한다.
        }
        System.out.println("proxy : setPrinterName()");
        this.name = name;
    }
    public String getPrinterName() {    // 이름의 취득
    	System.out.println("proxy : getPrinterName()");
    	return name;
    }
    public void print(String string) {  // 표시
        realize();
        real.print(string);
    }
    private synchronized void realize() {   // "본인"을 생성
        if (real == null) {
            real = new Printer(name);
        }
    }
}
```

Printer.java
```
public class Printer implements Printable {
    private String name;
    public Printer() {
        heavyJob("Printer의 인스턴스를 생성중");
    }
    public Printer(String name) {                   // 생성자
        this.name = name;
        heavyJob("Printer의 인스턴스(" + name + ")를 생성중");
    }
    public void setPrinterName(String name) {       // 이름의 설정
    	System.out.println("real : setPrinterName()");
        this.name = name;
    }
    public String getPrinterName() {                // 이름의 취득
    	System.out.println("real : getPrinterName()");
        return name;
    }
    public void print(String string) {              // 이름을 붙여서 표시
        System.out.println("=== " + name + " ===");
        System.out.println(string);
    }
    private void heavyJob(String msg) {             // 무거운 작업
        System.out.print(msg);
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.print(".");
        }
        System.out.println("완료");
    }
}
```

Main.java
```
public class Main {
    public static void main(String[] args) {
        Printable p = new PrinterProxy("Alice");
        System.out.println("현재의 이름은" + p.getPrinterName() + "입니다.");
        p.setPrinterName("Bob");
        System.out.println("현재의 이름은" + p.getPrinterName() + "입니다.");
        p.print("Hello, world.");
        
        p.print("test");
        p.setPrinterName("Tomas");
        System.out.println("현재의 이름은" + p.getPrinterName() + "입니다.");
        
    }
}
```
