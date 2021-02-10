package ro.inf.ucv.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import ro.inf.ucv.entity.User;

@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {

	User findByUsername(String userName);

	long countByEmail(String email);

	long countByUsername(String username);

}
