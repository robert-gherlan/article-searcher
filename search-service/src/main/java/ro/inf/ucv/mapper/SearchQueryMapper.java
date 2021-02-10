package ro.inf.ucv.mapper;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

import ro.inf.ucv.dto.AdvancedSearchRequestDto;
import ro.inf.ucv.dto.SearchRequestDto;

public class SearchQueryMapper {

	private SearchQueryMapper() {
	}

	public static QueryBuilder toSearchQuery(AdvancedSearchRequestDto advancedSearchRequestDto) {
		return null;
	}

	public static QueryBuilder toSearchQuery(SearchRequestDto searchRequestDto) {
		return QueryBuilders.queryStringQuery(searchRequestDto.getQuery());
	}

}
