package ro.inf.ucv.ingester;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.inf.ucv.service.RecordService;

@Service
public class RecordIngesterService {

	@Autowired
	private Ingester ingester;

	@Autowired
	private RecordService recordService;

	public void ingestRecords() {
		if (recordService.hasNoRecords()) {
			ingester.ingest(recordService);
		}
	}
}
