package kr.co.domain;

public class BookDTO {
	
	private String bid;
	private String btitle;
	private String bwriter;
	
	public BookDTO() {
		// TODO Auto-generated constructor stub
	}

	public BookDTO(String bid, String btitle, String bwriter) {
		super();
		this.bid = bid;
		this.btitle = btitle;
		this.bwriter = bwriter;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getBtitle() {
		return btitle;
	}

	public void setBtitle(String btitle) {
		this.btitle = btitle;
	}

	public String getBwriter() {
		return bwriter;
	}

	public void setBwriter(String bwriter) {
		this.bwriter = bwriter;
	}
	
	
	
	
}
