package kr.co.domain;

import java.util.List;

public class PageTO {
//독립변수
	private int curPage = 1;
	private int perPage = 10;
	private int pageLine = 10;	
	private int amount;
	
//종속변수
	private int totalPage; // amount/perPage
	private int startNum; //rownum  //starNum endNum ->spring에선 필요없긴함(rowbound 클레스를 제공해줌)
	private int endNum;  //rownum
	
	private int beginPageNum;   //눈에보이는 페이징번호, foreach문의 begin
	private int stopPagaeNum;
	
	private List<BoardVO> list; 
	
	public PageTO() {
		executeAll();
	}
	
	public PageTO(int perPage) { //perpage 외부에서 변경시켜줌 ->startNum~stopPageNum잉 바뀜
		super();
		this.perPage = perPage;
		executeAll();
	}
	
//getset  ->독립변수는 바꿔줌 setmethod에 executeall method호출  //////
	public int getCurPage() {
		return curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
		executeAll();
	}

	public int getPerPage() {
		return perPage;
	}

	public void setPerPage(int perPage) {
		this.perPage = perPage;
		executeAll();
	}

	public int getPageLine() {
		return pageLine;
	}

	public void setPageLine(int pageLine) {
		this.pageLine = pageLine;
		executeAll();
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
		executeAll();
	}
//위에까지만 executeAll()해줌. 나머지 종속변수들은 그냥 메서드 호출해서~ 
	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartNum() {
		return startNum;
	}

	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	public int getBeginPageNum() {
		return beginPageNum;
	}

	public void setBeginPageNum(int beginPageNum) {
		this.beginPageNum = beginPageNum;
	}

	public int getStopPagaeNum() {
		return stopPagaeNum;
	}

	public void setStopPagaeNum(int stopPagaeNum) {
		this.stopPagaeNum = stopPagaeNum;
	}

	public List<BoardVO> getList() {
		return list;
	}

	public void setList(List<BoardVO> list) {
		this.list = list;
	}

	private void executeAll() { //독립변수 바뀔때마다 이 메서드 부르기
		totalPage = (amount-1)/perPage + 1;  //계산은 인트형  //-1하는이유는 10,20으로 떨어지는것 정확하게 맞추기위해		
//rownum
		startNum = (curPage-1)*perPage + 1;
		endNum = curPage*perPage;
		if(endNum>amount) endNum = amount;		
//하단		
		beginPageNum = ((curPage-1)/pageLine)*pageLine + 1;  //값이 정수로만 나온다
		stopPagaeNum = beginPageNum + (pageLine -1);
		if(stopPagaeNum>totalPage) stopPagaeNum = totalPage;
		
		
		
		
		
		
	}
	
	
	
}
