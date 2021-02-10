package ro.inf.ucv.ingester;

import ro.inf.ucv.service.RecordService;

public interface Ingester {

	public void ingest(RecordService recordService);
}
