package ro.inf.ucv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.dto.RecordDto;
import ro.inf.ucv.service.RecordService;

@RestController
@RequestMapping("/suggester")
public class SuggesterController {

	@Autowired
	private RecordService suggestService;

	@GetMapping
	public ResponseEntity<List<RecordDto>> suggest() {
		List<RecordDto> result = suggestService.findAll();
		return ResponseEntity.ok(result);
	}

}
