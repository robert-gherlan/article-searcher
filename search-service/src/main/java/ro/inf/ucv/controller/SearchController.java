package ro.inf.ucv.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.config.ApiConfig;
import ro.inf.ucv.dto.AdvancedSearchRequestDto;
import ro.inf.ucv.dto.RecordDto;
import ro.inf.ucv.dto.SearchRequestDto;
import ro.inf.ucv.exception.BadRequestException;
import ro.inf.ucv.service.RecordService;

@RestController
@CrossOrigin
@RequestMapping(ApiConfig.PATH)
public class SearchController {

	@Autowired
	private RecordService recordService;

	@PostMapping("/search")
	public ResponseEntity<Page<RecordDto>> search(@Valid @RequestBody SearchRequestDto searchRequest,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new BadRequestException(bindingResult);
		}
		Page<RecordDto> result = recordService.search(searchRequest);
		return ResponseEntity.ok(result);
	}

	@PostMapping("/advanced-search")
	public ResponseEntity<Page<RecordDto>> advancedSearch(
			@Valid @RequestBody AdvancedSearchRequestDto advancedSearchRequest, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new BadRequestException(bindingResult);
		}
		Page<RecordDto> result = recordService.advancedSearch(advancedSearchRequest);
		return ResponseEntity.ok(result);
	}
}
