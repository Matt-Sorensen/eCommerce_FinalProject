package e.Commerce.controller.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import e.Commerce.entity.Orders;
import e.Commerce.entity.Product;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductData {
	@Id
	@GeneratedValue
	private Long productId;
	
	private String productName;
	private String productDescription;
	private String productPrice;
	private List<ProductOrders> productOrders = new ArrayList<>();
	
    // Constructor to map Product entity to ProductData
	public ProductData(Product product) {
		productId = product.getProductId();
		productName = product.getProductName();
		productDescription = product.getProductDescription();
		productPrice = product.getProductPrice();
		
		for(Orders orders : product.getProductOrders()) {
			productOrders.add(new ProductOrders(orders));
		}
	}
	
	@Data
	@NoArgsConstructor
	static class ProductOrders {
		private Long orderId;
		private Date datePlaced;
		
        // Constructor to map Orders entity to ProductOrders
		ProductOrders(Orders orders) {
			orderId = orders.getOrderId();
			datePlaced = orders.getDatePlaced();
		}
	}
	
}
