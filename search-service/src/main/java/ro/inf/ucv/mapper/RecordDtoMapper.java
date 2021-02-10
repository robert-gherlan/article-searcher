package ro.inf.ucv.mapper;

import ro.inf.ucv.document.Record;
import ro.inf.ucv.dto.RecordDto;

public class RecordDtoMapper {

	private RecordDtoMapper() {
	}

	public static Record toRecord(RecordDto recordDto) {
		return new Record(recordDto);
	}

	public static RecordDto toRecordDto(Record record) {
		return new RecordDto(record);
	}

}
