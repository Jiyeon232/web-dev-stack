package vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class Member { // --> member
	
	private String id; // primary key
	private String name;
	private String pwd;
	private int age;
	
}
