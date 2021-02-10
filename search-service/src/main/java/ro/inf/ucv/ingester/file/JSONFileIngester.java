package ro.inf.ucv.ingester.file;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.jooq.lambda.Unchecked;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Iterators;

import ro.inf.ucv.dto.RecordDto;
import ro.inf.ucv.ingester.Ingester;
import ro.inf.ucv.service.RecordService;

@Component
public class JSONFileIngester implements Ingester {

	private static final int BATCH_SIZE = 10_000;

	@Autowired
	private JSONFileSelector jsonFileSelector;

	@Override
	public void ingest(RecordService recordService) {
		long start = System.currentTimeMillis();
		// Get resulted records which will be ingested.
		List<RecordDto> recordsToBeIngested = jsonFileSelector.selectFiles()
				.map(Unchecked.function(file -> Files.lines(file.toPath(), StandardCharsets.UTF_8)))
				.flatMap(Function.identity()).map(JSONObject::new).map(JSONObjectMapper::toRecordDto)
				.filter(Optional::isPresent).map(Optional::get).collect(Collectors.toList());

		// Ingest obtained records in batch
		Iterators.partition(recordsToBeIngested.iterator(), BATCH_SIZE).forEachRemaining(recordService::saveAll);
		System.out.println("The parsing and saving took: " + (System.currentTimeMillis() - start) / 1000.0
				+ " seconds for " + recordsToBeIngested.size() + " records.");
	}
}
