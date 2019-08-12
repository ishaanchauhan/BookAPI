package com.bcsutilities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class CustomizedPager implements Pageable{
	
	private int limit = 0;
	private int offset = 0;
	
	public CustomizedPager(int limit, int offset) {
	    this.limit = limit;
	    this.offset = offset;
	}

	@Override
	public int getPageNumber() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPageSize() {
		// TODO Auto-generated method stub
		return limit;
	}

	@Override
	public int getOffset() {
		// TODO Auto-generated method stub
		return offset;
	}

	@Override
	public Sort getSort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable previousOrFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pageable first() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}
}
