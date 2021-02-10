package ro.inf.ucv.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.bean.Suggest;

@Repository
public interface SearchRepository extends ElasticsearchRepository<Suggest, String> {

}
