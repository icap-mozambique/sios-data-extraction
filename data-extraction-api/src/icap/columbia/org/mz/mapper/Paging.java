package icap.columbia.org.mz.mapper;

/**
 * The paging struture
 * 
 * @author Simone Fernando, 21/01/2021
 *
 */
public class Paging {

	// return the pageNumber
	private long page;

	// return the number of pages
	private long pageCount;

	// return the total number of records
	private long total;

	// return the number of records per page
	private long pageSize;

	// the next page request URI
	private String nextPage;

	/**
	 * 
	 */
	public Paging() {
	}

	/**
	 * @param pageCount
	 * @param total
	 */
	public Paging(long pageCount, long total, long pageSize) {
		this.pageCount = pageCount;
		this.total = total;
		this.pageSize = pageSize;
	}

	/**
	 * 
	 * @return page
	 */
	public long getPage() {
		return page;
	}

	/**
	 * 
	 * @param page
	 */
	public void setPage(long page) {
		this.page = page;
	}

	/**
	 * 
	 * @return pageCount
	 */
	public long getPageCount() {
		return pageCount;
	}

	/**
	 * 
	 * @param pageCount
	 */
	public void setPageCount(long pageCount) {
		this.pageCount = pageCount;
	}

	/**
	 * 
	 * @return total
	 */
	public long getTotal() {
		return total;
	}

	/**
	 * 
	 * @param total
	 */
	public void setTotal(long total) {
		this.total = total;
	}

	/**
	 * 
	 * @return pageSize
	 */
	public long getPageSize() {
		return pageSize;
	}

	/**
	 * 
	 * @param pageSize
	 */
	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 
	 * @return nextPage
	 */
	public String getNextPage() {
		return nextPage;
	}

	/**
	 * 
	 * @param nextPage
	 */
	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

}
