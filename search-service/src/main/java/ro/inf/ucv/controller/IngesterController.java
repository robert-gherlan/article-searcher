package ro.inf.ucv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ro.inf.ucv.config.ApiConfig;
import ro.inf.ucv.ingester.RecordIngesterService;

@RestController
@RequestMapping(ApiConfig.PATH + "/ingest")
public class IngesterController {

	@Autowired
	private RecordIngesterService recordsIngesterService;

	@PostMapping
	public ResponseEntity<HttpStatus> ingest() {
		recordsIngesterService.ingestRecords();
		return ResponseEntity.ok(HttpStatus.OK);
	}
}
