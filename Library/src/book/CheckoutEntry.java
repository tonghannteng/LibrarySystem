package book;

import java.io.Serializable;
import java.time.LocalDate;

import controller.BookController;
import controller.MemberController;

public class CheckoutEntry implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6599229911684097782L;
	private String ISBN;
	private int copyId;
	private LocalDate dateOfCheckout, dueDate;
	private int memberId;

	public CheckoutEntry(String ISBN, int copyId, int memberId){
		this.ISBN = ISBN;
		this.copyId = copyId;
		this.memberId = memberId;
		this.dateOfCheckout = LocalDate.now();
		this.dueDate = dateOfCheckout.plusDays(BookController.getBook(ISBN).getMaxCheckoutLength());
	}
	
	public String getFirstName(){
		return MemberController.getMember(memberId).getFirstName();
	}
	public String getLastName(){
		return MemberController.getMember(memberId).getLastName();
	}
	public String getBookTitle(){
		return BookController.getBook(ISBN).getTitle();
	}
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public int getCopyId() {
		return copyId;
	}

	public void setCopyId(int copyId) {
		this.copyId = copyId;
	}

	public LocalDate getDateOfCheckout() {
		return dateOfCheckout;
	}

	public void setDateOfCheckout(LocalDate dateOfCheckout) {
		this.dateOfCheckout = dateOfCheckout;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public LocalDate calculateDueDate(LocalDate dateOfCheckout, long maxCheckoutLength){		
		return dateOfCheckout.plusDays(maxCheckoutLength);
	}
}
