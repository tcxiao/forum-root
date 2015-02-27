package com.forum.util;

import java.util.List;

public class PageUtil{
	
	public final static int PAGESIZE = 30;

	 private int pageSize = PAGESIZE;
	 
	 private int totalCount;

	 private int currentPage;

	 private int startIndex;
	 
	 private int[] indexes = new int[0];
	 
	 private int nextIndex;

	 private int previousIndex;

	 private int pageCount;

	 private List items;
	 
	 private int lastIndex;
	 
	 public  PageUtil(int pageSize,
	   int startIndex) {
	  setPageSize(pageSize);
	  setStartIndex(startIndex);
	  
	 }
	 
	 public  PageUtil(int pageSize,int currentPage,int totalCount) {
		 setPageSize(pageSize);
		 setCurrentPage(currentPage);
		 setTotalCount(totalCount);
	 }

	 public  PageUtil(List items, int totalCount) {
	  setPageSize(PAGESIZE);
	  setTotalCount(totalCount);
	  setItems(items);
	  setStartIndex(0);
	 
	 }

	 public  PageUtil(List items, int totalCount, int pageSize,
	   int startIndex) {
	  setPageSize(pageSize);
	  setTotalCount(totalCount);
	  setItems(items);
	  setStartIndex(startIndex);
	  
	 }

	 
	 public void setTotalCount(int totalCount) {
	  if (totalCount > 0) {
	   this.totalCount = totalCount;
	   int count = totalCount / pageSize;
	   if (totalCount % pageSize > 0)
	    count++;
	   indexes = new int[count];
	   for (int i = 0; i < count; i++) {
	    indexes[i] = pageSize * i;
	   }
	    } else {
	   this.totalCount = 0;
	  }
	 }
	 public int getTotalCount() {
	  return totalCount;
	 }
	 public void setIndexes(int[] indexes) {
	  this.indexes = indexes;
	 }
	 public int[] getIndexes() {
	  return indexes;
	 }

	 
	 public void setStartIndex(int startIndex) {
	  if (totalCount <= 0)
	   this.startIndex = 0;
	  else if (startIndex >= totalCount)
	   this.startIndex = indexes[indexes.length - 1];
	  else if (startIndex < 0)
	   this.startIndex = 0;
	  else {
	   this.startIndex = indexes[startIndex / pageSize];
	  }
	   }
	 public int getStartIndex() {
	  return startIndex;
	 }

	 
	 public void setNextIndex(int nextIndex) {
	  this.nextIndex = nextIndex;
	 }
	 public int getNextIndex() {
	  int nextIndex = getStartIndex() + pageSize;
	  if (nextIndex >= totalCount)
	   return getStartIndex();
	  else
	   return nextIndex;
	 }
	 public void setPreviousIndex(int previousIndex) {
	  this.previousIndex = previousIndex;
	 }
	 
	 public int getPreviousIndex() {
	  int previousIndex = getStartIndex() - pageSize;
	  if (previousIndex < 0)
	   return 0;
	  else
	   return previousIndex;
	 }
	 public void setPageCount(int pageCount) {
	  this.pageCount = pageCount;
	 }
	 public int getPageCount() {
	  int count = totalCount / pageSize;
	  if (totalCount % pageSize > 0)
	   count++;
	  return count;
	 }
	 

	 public int getCurrentPage() {
	  return getStartIndex() / pageSize + 1;
	 }

	 public void setCurrentPage(int currentPage) {
	  this.currentPage = currentPage;
	 }

	 public void setLastIndex(int lastIndex) {
	  this.lastIndex =lastIndex ;
	 }
	 public int getLastIndex() {
	  return indexes[indexes.length-1];
	 }

	 
	 public int getPageSize() {
	  return pageSize;
	 }

	 public void setPageSize(int pageSize) {
	  this.pageSize = pageSize;
	 }

	 

	 public List getItems() {
	  return items;
	 }

	 public void setItems(List items) {
	  this.items = items;
	 }
	
	

}
