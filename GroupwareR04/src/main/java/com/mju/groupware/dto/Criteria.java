package com.mju.groupware.dto;

import org.springframework.web.util.UriComponentsBuilder;

public class Criteria { // 게시글 조회 쿼리에 전달될 파라미터를 담게 될 클래스
	private int page; // 현재 페이지 번호
    private int perPageNum; // 한 페이지당 보여줄 게시글의 개수
    private String UserLoginID;
    private int UserID;
    private int TeamID;
    private String searchType;
    private String keyword;
    
    
	public int getPageStart() {
        return (this.page-1)*perPageNum;
    }
    
    public Criteria() {
        this.page = 1;
        this.perPageNum = 10;
        this.searchType = null;
        this.keyword = null;
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
	
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String makeQuery() {
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance()
				.queryParam("page", this.page)
				.queryParam("perPageNum", this.perPageNum);
		
		if(searchType != null) {
			uriComponentsBuilder
				.queryParam("searchType", this.searchType)
				.queryParam("keyword", this.keyword)
				;
		}
		return uriComponentsBuilder.build().encode().toString();
	}
	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}

	public int getTeamID() {
		return TeamID;
	}

	public void setTeamID(int teamID) {
		TeamID = teamID;
	}



}
