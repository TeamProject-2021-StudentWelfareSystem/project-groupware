package com.mju.groupware.util;

import com.mju.groupware.dto.Criteria;

public class PageMaker {
	
	private Criteria cri;
    private int totalCount;
    private int startPage;
    private int endPage;
    private boolean prev;
    private boolean next;
    private int displayPageNum = 5;
	public Criteria getCri() {
        return cri;
    }
    public void setCri(Criteria cri) {
        this.cri = cri;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        calcData();
    }
    
    private void calcData() {
    	int page = this.cri.getPage();
		int perPageNum = this.cri.getPerPageNum();
		
        this.endPage = (int) (Math.ceil(page / (double) displayPageNum) * displayPageNum);
 
        this.startPage = (this.endPage - displayPageNum) + 1;
        if(this.startPage <= 0) this.startPage = 1;
        
        // 전체 게시물 수 통한 endPage. 게시글 89개면 9페이지까지 출력
        int tempEndPage = (int) (Math.ceil(totalCount / (double) perPageNum));
        if (this.endPage > tempEndPage) {
        	this.endPage = tempEndPage;
        }
        this.prev = startPage == 1? false : true;
		this.next = (endPage * perPageNum) <= totalCount ? true : false;
    }
    
    public int getStartPage() {
        return startPage;
    }
    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
    public int getEndPage() {
        return endPage;
    }
    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }
    public boolean isPrev() {
        return prev;
    }
    public void setPrev(boolean prev) {
        this.prev = prev;
    }
    public boolean isNext() {
        return next;
    }
    public void setNext(boolean next) {
        this.next = next;
    }
    public int getDisplayPageNum() {
        return displayPageNum;
    }
    public void setDisplayPageNum(int displayPageNum) {
        this.displayPageNum = displayPageNum;
    }

}
