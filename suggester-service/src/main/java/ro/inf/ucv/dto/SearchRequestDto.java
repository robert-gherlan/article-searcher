package ro.inf.ucv.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchRequestDto {

	private static final int DEFAULT_PAGE_SIZE = 10;

	private String query;

	private int pageNumber;

	private int pageSize = DEFAULT_PAGE_SIZE;
}
