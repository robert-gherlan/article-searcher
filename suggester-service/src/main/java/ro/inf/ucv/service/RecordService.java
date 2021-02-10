package ro.inf.ucv.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.inf.ucv.bean.Suggest;
import ro.inf.ucv.dto.RecordDto;
import ro.inf.ucv.exception.RecordNotFoundException;
import ro.inf.ucv.mapper.RecordDtoMapper;
import ro.inf.ucv.repository.RecordRepository;

@Service
@Transactional
public class RecordService {

	@Autowired
	private RecordRepository recordRepository;

	public RecordDto save(@Valid RecordDto recordDto) {
		RecordDto result = null;
		if (Objects.nonNull(recordDto)) {
			result = RecordDtoMapper.toRecordDto(recordRepository.save(RecordDtoMapper.toRecord(recordDto)));
		}

		return result;
	}

	public List<RecordDto> saveAll(@Valid Iterable<RecordDto> recordDtos) {
		List<RecordDto> result = null;
		if (Objects.nonNull(recordDtos)) {
			Iterable<Suggest> saved = recordRepository.saveAll(StreamSupport.stream(recordDtos.spliterator(), false)
					.map(RecordDtoMapper::toRecord).collect(Collectors.toList()));
			result = StreamSupport.stream(saved.spliterator(), false).map(RecordDtoMapper::toRecordDto)
					.collect(Collectors.toList());
		}

		return result;
	}

	public List<RecordDto> findAll() {
		Iterable<Suggest> foundUsers = recordRepository.findAll();
		if (!foundUsers.iterator().hasNext()) {
			throw new RecordNotFoundException("No records were found");
		}

		return StreamSupport.stream(foundUsers.spliterator(), false).map(RecordDtoMapper::toRecordDto)
				.collect(Collectors.toList());
	}

	public boolean hasNoRecords() {
		return recordRepository.count() == 0;
	}
}
