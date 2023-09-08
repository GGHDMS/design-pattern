# 09. 상속을 위임으로 바꾸기

## IS-A 관계와 HAS-A 관계

- IS-A 관계 

    일반적인 클래스와 구체적인 클래스의 관계

    클래스간의 상속은 종속성이 강하게 되므로, 꼭 필요한 경우 사용하는 것이 좋음

    상속은 코드의 재사용을 위한 방법이 아님

- HAS-A 관계

    이미 잘 만들어진 라이브러리나 클래스를 재사용하는 방법

    사용할 클래스를 포함하여 그 기능을 해당 클래스로 위임한다.

    클래스간의 종속성이 약함

이미 잘 만들어진 클래스를 재사용하기 위해서는 상속이 아닌 위임을 사용한다. 

IS-A 관계가 아닌 클래스간의 상속은 HAS-A로 리펙토링 한다.



## 리펙토링 전 - 학생 클래스가 과목 클래스를 상속한 경우 

- 처음엔 전공과목 1개만 있었지만, 점점 과목이 추가 됨

Subject.java
```


public class Subject {

	String subjectName;
	int subjectCode;
	int score;
	
	public Subject() {}
	
	public Subject(String subjectName, int subjectCode) {
		this.subjectName = subjectName;
		this.subjectCode = subjectCode;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public String getSubjectName() {
		return subjectName;
	}

	public int getSubjectCode() {
		return subjectCode;
	}
	
	public String toString() {
		return subjectCode + ":" + subjectName; 
	}
}
```

Student.java
```


import java.util.ArrayList;

public class Student extends Subject{

	String studentName;
	String studentId;
	Subject majorSubject;
	
	ArrayList<Subject> subjectList = new ArrayList<Subject>();
	
	public Student(String studentName, String studentId, String subjectName, int subjectCode) {
		
		majorSubject = new Subject(subjectName, subjectCode);
		
		this.studentName = studentName;
		this.studentId = studentId;

	}
	
	public void addSubject(String subjectName, int subjectCode) {
	
		Subject subject = new Subject(subjectName, subjectCode);
		subjectList.add(subject);
	}
	
	
	public void setSubjectScore(int subjectCode, int score) {
		
		for(Subject subject : subjectList) {
			if(subject.getSubjectCode() == subjectCode) {
				subject.setScore(score);
				return;
			}
		}
		System.out.println("no subject code error");
	}

	public void getReport() {
		
		int total = majorSubject.score;
		
		System.out.println(studentName + "님의 전공과목은 " + majorSubject.getSubjectName() + "입니다." );
		System.out.println( majorSubject.getSubjectName() +  ":" + majorSubject.getScore());
		
		for(Subject subject : subjectList) {
			
			System.out.println( subject.getSubjectName() +  ":" + subject.getScore());
			total += subject.getScore();
		}
		
		System.out.println("Total Score : " + total);
	}
}
```

Main.java
```


public class Main {

	public static void main(String[] args) {

		Student studentKim = new Student("Kim", "12345", "국어국문", 98765);
		Student studentLee = new Student("Lee", "12346", "영어영문", 54321);
		
		studentKim.majorSubject.setScore(100);
		studentLee.majorSubject.setScore(90);
		//studentKim.getReport();
		//studentLee.getReport();
		
		
		// 과목이 점점 추가 됨
		studentKim.addSubject("수학", 12345);
		studentLee.addSubject("수학", 12345);
		
		studentKim.setSubjectScore(12345, 100);
		studentLee.setSubjectScore(12345, 100);
		
		studentKim.getReport();
		studentLee.getReport();
	}

}
```

## 리펙토링 후 - 상속 관계를 제거함
