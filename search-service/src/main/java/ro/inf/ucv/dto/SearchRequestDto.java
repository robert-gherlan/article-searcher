package ro.inf.ucv.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchRequestDto {

	private static final int DEFAULT_PAGE_SIZE = 10;

	@NotEmpty
	private String query;

	@Min(0)
	private int pageNumber;

	@Min(1)
	private int pageSize = DEFAULT_PAGE_SIZE;
}
