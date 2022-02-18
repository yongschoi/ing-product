package yongs.temp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import yongs.temp.dao.ProductRepository;
import yongs.temp.model.Product;
import yongs.temp.service.MinioService;

@Slf4j
@RestController
public class ProductController {	
	@Autowired
    private ProductRepository repo;
    @Autowired
    MinioService minio;
    
    @GetMapping("/all")
    public Flux<Product> findAll() {
    	log.debug("ProductController.findAll()");  	
    	// a littl 비효율(?)
    	return repo.findAll().map(p -> {
    		return new Product(p.getId(), 
					   p.getCategory(), 
					   p.getName(), 
					   p.getMadein(),
					   p.getShippingfee(),
					   p.getPrice(),
					   minio.getObjectUrl("example-product", p.getImageName()));
    	});
    }
    
    @GetMapping("/name/{name}")
    public Flux<Product> findByRegexpName(@PathVariable("name") String name) {
    	log.debug("ProductController.findByRegexpName({})", name); 
    	return repo.findByRegexpName(name).map(p -> {
    		return new Product(p.getId(), 
					   p.getCategory(), 
					   p.getName(), 
					   p.getMadein(),
					   p.getShippingfee(),
					   p.getPrice(),
					   minio.getObjectUrl("example-product", p.getImageName()));
    	});
    }	
    
    @GetMapping("/category/{category}")
    public Flux<Product> findByCategory(@PathVariable("category") String category) {
    	log.debug("ProductController.findByCategory({})", category);
    	return repo.findByCategory(category).map(p -> {
    		return new Product(p.getId(), 
					   p.getCategory(), 
					   p.getName(), 
					   p.getMadein(),
					   p.getShippingfee(),
					   p.getPrice(),
					   minio.getObjectUrl("example-product", p.getImageName()));
    	});    	
    }	
}
