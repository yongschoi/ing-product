package yongs.temp.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor 
@NoArgsConstructor
@Data
@Document(collection = "product")
@Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Product {
	@Id
	private String id;
	private String category;
    private String name;
    private int madein;
    private int shippingfee;
    private long price;    
    private String imageName;
    private String imageUrl;
    
    public Product(String id, String category, String name, int madein, int shippingfee, long price, String imageUrl) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.madein = madein;
		this.shippingfee = shippingfee;
		this.price = price;
		this.imageUrl = imageUrl;
	}
}
