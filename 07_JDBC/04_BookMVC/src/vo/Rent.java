package vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor @AllArgsConstructor @Data
public class Rent { // --> rent

	private int rentNo; // --> rent_no
	private String id; // foreign key (Client)
	private int bookNo; // foreign key (Book) --> book_no
	private LocalDate rentDate; // rent_date
	
	// ---------------------
	//private Member member;
	private Book book;
}
