package com.spring5.code02.vo;

import org.springframework.stereotype.Component;

@Component
public class SearchVO {

	private String searchType;
	private String searchValue;
	
	public SearchVO() {
		super();
	}
	
	public SearchVO(String searchType, String searchValue) {
		super();
		this.searchType = searchType;
		this.searchValue = searchValue;
	}
	
	public String getSearchType() {
		return searchType;
	}
	
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	public String getSearchValue() {
		return searchValue;
	}
	
	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((searchType == null) ? 0 : searchType.hashCode());
		result = prime * result + ((searchValue == null) ? 0 : searchValue.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SearchVO other = (SearchVO) obj;
		if (searchType == null) {
			if (other.searchType != null)
				return false;
		} else if (!searchType.equals(other.searchType))
			return false;
		if (searchValue == null) {
			if (other.searchValue != null)
				return false;
		} else if (!searchValue.equals(other.searchValue))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "SearchVO [searchType=" + searchType + ", searchValue=" + searchValue + "]";
	}
	
}
