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
public class OrderData {
	@Id
	@GeneratedValue
	private Long orderId;
	private Date datePlaced;
	private boolean activeOrder;
	private CustomerData orderCustomer;
	private List<OrderProducts> orderProducts = new ArrayList<>();
	
    // Constructor to map Orders entity to OrderData
	public OrderData(Orders order) {
		orderId = order.getOrderId();
		datePlaced = order.getDatePlaced();
		activeOrder = order.isActiveOrder();
		orderCustomer = new CustomerData(order.getOrderCustomer());
		
		for(Product product : order.getOrderProducts()) {
			orderProducts.add(new OrderProducts(product));
		}
	}
	
	@Data
	@NoArgsConstructor
	static class OrderProducts {
		private Long productId;
		private String productName;
		private String productDescription;
		private String productPrice;
		
        // Constructor to map Product entity to OrderProducts
		OrderProducts(Product product) {
			productId = product.getProductId();
			productName = product.getProductName();
			productDescription = product.getProductDescription();
			productPrice = product.getProductPrice();
		}
	}
}
