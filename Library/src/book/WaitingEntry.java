package book;

import java.io.Serializable;
import java.time.LocalDate;

import controller.BookController;
import controller.MemberController;

public class WaitingEntry implements Serializable {
	private static final long serialVersionUID = 1L;
	String ISBN;
	int memberId;
	LocalDate startDate;

	public WaitingEntry(String ISBN, int memberId) {
		this.ISBN = ISBN;
		this.memberId = memberId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public String getFirstname() {
		return MemberController.getMember(memberId).getFirstName();
	}

	public String getLastname() {
		return MemberController.getMember(memberId).getLastName();
	}

	public String getBookTitle() {
		return BookController.getBook(ISBN).getTitle();
	}
	
	public String getAvailableBookCopy() {
		return Integer.toString(BookController.getAvailableBookCopy(ISBN));
	}
}
