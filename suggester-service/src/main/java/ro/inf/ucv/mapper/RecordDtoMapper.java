package ro.inf.ucv.mapper;

import ro.inf.ucv.bean.Suggest;
import ro.inf.ucv.dto.RecordDto;

public class RecordDtoMapper {

	private RecordDtoMapper() {
	}

	public static Suggest toRecord(RecordDto recordDto) {
		return new Suggest(recordDto);
	}

	public static RecordDto toRecordDto(Suggest record) {
		return new RecordDto(record);
	}

}
