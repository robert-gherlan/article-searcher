package ro.inf.ucv.service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.inf.ucv.document.Record;
import ro.inf.ucv.dto.AdvancedSearchRequestDto;
import ro.inf.ucv.dto.RecordDto;
import ro.inf.ucv.dto.SearchRequestDto;
import ro.inf.ucv.exception.RecordNotFoundException;
import ro.inf.ucv.mapper.PageRequestMapper;
import ro.inf.ucv.mapper.RecordDtoMapper;
import ro.inf.ucv.mapper.SearchQueryMapper;
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
		List<RecordDto> savedRecords = Collections.emptyList();
		if (Objects.nonNull(recordDtos)) {
			Iterable<Record> saved = recordRepository.saveAll(StreamSupport.stream(recordDtos.spliterator(), false)
					.map(RecordDtoMapper::toRecord).collect(Collectors.toList()));
			savedRecords = StreamSupport.stream(saved.spliterator(), false).map(RecordDtoMapper::toRecordDto)
					.collect(Collectors.toList());
		}

		return savedRecords;
	}

	public Page<RecordDto> search(@Valid SearchRequestDto searchRequestDto) {
		Pageable pageRequest = PageRequestMapper.toPageRequest(searchRequestDto);
		QueryBuilder searchQuery = SearchQueryMapper.toSearchQuery(searchRequestDto);
		Page<Record> foundRecords = recordRepository.search(searchQuery, pageRequest);
		if (!foundRecords.hasContent()) {
			throw new RecordNotFoundException("No records were found");
		}

		return foundRecords.map(RecordDtoMapper::toRecordDto);
	}

	public Page<RecordDto> advancedSearch(@Valid AdvancedSearchRequestDto searchRequestDto) {
		Pageable pageable = PageRequestMapper.toPageRequest(searchRequestDto);
		QueryBuilder query = SearchQueryMapper.toSearchQuery(searchRequestDto);
		Page<Record> foundRecords = recordRepository.search(query, pageable);
		if (!foundRecords.hasContent()) {
			throw new RecordNotFoundException("No records were found");
		}

		return foundRecords.map(RecordDtoMapper::toRecordDto);
	}

	public boolean hasNoRecords() {
		return recordRepository.count() == 0;
	}
}
