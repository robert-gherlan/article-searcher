package ro.inf.ucv.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.bean.Suggest;

@Repository
public interface RecordRepository extends ElasticsearchCrudRepository<Suggest, String> {

}
