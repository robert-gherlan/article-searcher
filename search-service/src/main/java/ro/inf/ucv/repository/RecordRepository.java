package ro.inf.ucv.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.document.Record;

@Repository
public interface RecordRepository extends ElasticsearchRepository<Record, String> {

}
