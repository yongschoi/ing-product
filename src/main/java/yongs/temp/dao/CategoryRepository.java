package yongs.temp.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;
import yongs.temp.model.Category;
import yongs.temp.model.Product;

public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
	public Flux<Product> findByName(String name);
}
  