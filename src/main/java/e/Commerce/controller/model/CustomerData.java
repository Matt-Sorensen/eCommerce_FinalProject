package e.Commerce.controller.model;

import java.util.Date;

import e.Commerce.entity.Customer;
import e.Commerce.entity.Orders;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerData {
	private Long customerId;
	private String username;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String city;
	private String state;
	private String zip;
	private String phone;
	
    // Constructor to map Customer entity to CustomerData
	public CustomerData(Customer customer) {
		customerId = customer.getCustomerId();
		username = customer.getUsername();
		password = customer.getPassword();
		email = customer.getEmail();
		firstName = customer.getFirstName();
		lastName = customer.getLastName();
		city = customer.getCity();
		state = customer.getState();
		zip = customer.getZip();
		phone = customer.getPhone();
	}

	@Data
	@NoArgsConstructor
	static class CustomerOrders {
		private Long orderId;
		private Date datePlaced;
		private boolean activeOrder;
		
        // Constructor to map Orders entity to CustomerOrders
		CustomerOrders(Orders orders) {
			orderId = orders.getOrderId();
			datePlaced = orders.getDatePlaced();
			activeOrder = orders.isActiveOrder();
		}
	}
}
