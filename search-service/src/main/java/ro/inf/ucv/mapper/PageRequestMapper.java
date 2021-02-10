package ro.inf.ucv.mapper;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import ro.inf.ucv.dto.SearchRequestDto;

public class PageRequestMapper {

	private static final int DEFAULT_PAGE_SIZE = 10;

	private PageRequestMapper() {
	}

	public static Pageable toPageRequest(SearchRequestDto searchRequestDto) {
		return getPageRequest(searchRequestDto);
	}

	private static Pageable getPageRequest(SearchRequestDto searchModel) {
		int pageNumber = searchModel.getPageNumber();
		int pageSize = searchModel.getPageSize();

		return getPageRequest(pageNumber, pageSize);
	}

	private static Pageable getPageRequest(int pageNumber, int pageSize) {
		if (pageNumber < 0) {
			pageNumber = 0;
		} else if (pageNumber > 0) {
			pageNumber = pageNumber - 1;
		}
		if (pageSize < 1) {
			pageSize = DEFAULT_PAGE_SIZE;
		}

		return PageRequest.of(pageNumber, pageSize);
	}
}
