package com.mju.groupware.dto;

public class Criteria { // 게시글 조회 쿼리에 전달될 파라미터를 담게 될 클래스
	private int page; // 현재 페이지 번호
    private int perPageNum; // 한 페이지당 보여줄 게시글의 개수
    private String UserLoginID;
    private int UserID;
    
	public int getPageStart() {
        return (this.page-1)*perPageNum;
    }
    
    public Criteria() {
        this.page = 1;
        this.perPageNum = 10;
    }
    
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        if(page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }
    public int getPerPageNum() {
        return perPageNum;
    }
    public void setPerPageNum(int pageCount) {
        int cnt = this.perPageNum;
        if(pageCount != cnt) {
            this.perPageNum = cnt;
        } else {
            this.perPageNum = pageCount;
        }
    }

	public int getUserID() {
		return UserID;
	}

	public void setUserID(int userID) {
		UserID = userID;
	}

	public String getUserLoginID() {
		return UserLoginID;
	}

	public void setUserLoginID(String userLoginID) {
		UserLoginID = userLoginID;
	}

}
