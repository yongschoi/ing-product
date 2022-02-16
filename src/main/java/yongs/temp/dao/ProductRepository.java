package yongs.temp.dao;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;
import yongs.temp.model.Product;

public interface ProductRepository extends ReactiveMongoRepository<Product, String> {
	@Query("{ 'name' : { $regex: ?0 } }")
	public Flux<Product> findByRegexpName(String regexp);
	public Flux<Product> findByCategory(String category);
}
  