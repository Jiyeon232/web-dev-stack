package vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
// VO(Value Object) : 값만 담는 객체
public class Person {

	private int id;
	private String name;
	private int age;
	private String addr;
	
}
