package e.Commerce.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import e.Commerce.controller.model.CustomerData;
import e.Commerce.controller.model.OrderData;
import e.Commerce.controller.model.ProductData;
import e.Commerce.service.ECommerceService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/ecommerce")
@Slf4j
public class ECommerceController {

	@Autowired
	private ECommerceService eCommerceService;

//////////////////////////CUSTOMER//////////////////////////////////////////	
	
    // Create a new customer
	@PostMapping("/customer")
	@ResponseStatus(code = HttpStatus.CREATED)
	public CustomerData insertCustomer(@RequestBody CustomerData customerData) {
		log.info("Creating Customer {}", customerData);
		return eCommerceService.saveCustomer(customerData);
	}

    // Update an existing customer
	@PutMapping("/customer/{customerId}")
	public CustomerData updateCustomer(@PathVariable Long customerId, @RequestBody CustomerData customerData) {
		customerData.setCustomerId(customerId);
		log.info("Updating customer {}", customerData);
		return eCommerceService.saveCustomer(customerData);
	}

    // Retrieve all customers
	@GetMapping("/customer")
	public List<CustomerData> retrieveAllCustomers() {
		log.info("Retrieve all customers called.");
		return eCommerceService.retrieveAllCustomers();
	}

    // Retrieve a customer by ID
	@GetMapping("/customer/{customerId}")
	public CustomerData retrieveCustomerById(@PathVariable Long customerId) {
		log.info("Retrieving customer with ID={}", customerId);
		return eCommerceService.retrieveCustomerById(customerId);
	}

    // Delete all customers (Unsupported operation)
	@DeleteMapping("/customer")
	public void deleteAllCustomers() {
		log.info("Attempting to delete all customers");
		throw new UnsupportedOperationException("Deleting all customers is not allowed");
	}

    // Delete a customer by ID
	@DeleteMapping("/customer/{customerId}")
	public Map<String, String> deleteCustomerById(@PathVariable Long customerId) {
		log.info("Deleting customer with ID={}", customerId);
		eCommerceService.deleteCustomerById(customerId);

		return Map.of("message", "Deletion of customer with ID=" + customerId + " was successful.");
	}

////////////////////////////////ORDER////////////////////////////////////////////////
	
    // Create a new order
	@PostMapping("/orders")
	@ResponseStatus(code = HttpStatus.CREATED)
	public OrderData insertOrder(@RequestBody OrderData orderData) {
		log.info("Creating Order {}", orderData);
		return eCommerceService.saveOrder(orderData);
	}

    // Update an existing order
	@PutMapping("/orders/{orderId}")
	public OrderData updateOrder(@PathVariable Long orderId, @RequestBody OrderData orderData) {
		orderData.setOrderId(orderId);
		log.info("Updating Order {}", orderData);
		return eCommerceService.saveOrder(orderData);
	}

    // Retrieve all orders
	@GetMapping("/orders")
	public List<OrderData> retrieveAllOrders() {
		log.info("Retrieve all orders called.");
		return eCommerceService.retrieveAllOrders();
	}

    // Retrieve an order by ID
	@GetMapping("/orders/{orderId}")
	public OrderData retrieveOrderById(@PathVariable Long orderId) {
		log.info("Retrieving order with ID={}", orderId);
		return eCommerceService.retrieveOrderById(orderId);
	}

    // Delete all orders (Unsupported operation)
	@DeleteMapping("/orders")
	public void deleteAllOrders() {
		log.info("Attempting to delete all orders");
		throw new UnsupportedOperationException("Deleting all orders is not allowed");
	}

    // Delete an order by ID
	@DeleteMapping("/orders/{orderId}")
	public Map<String, String> deleteOrderById(@PathVariable Long orderId) {
		log.info("Deleting order with ID={}", orderId);
		eCommerceService.deleteOrderById(orderId);

		return Map.of("message", "Deletion of order with ID=" + orderId + " was successful.");
	}
	


//////////////////////////////PRODUCT////////////////////////////////////////////////
	
    // Create a new product
	@PostMapping("/product")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ProductData insertProduct(@RequestBody ProductData productData) {
		log.info("Creating Product {}", productData);
		return eCommerceService.saveProduct(productData);
	}

    // Update an existing product
	@PutMapping("/product/{productId}")
	public ProductData updateProduct(@PathVariable Long productId, @RequestBody ProductData productData) {
		productData.setProductId(productId);
		log.info("Updating Product {}", productData);
		return eCommerceService.saveProduct(productData);
	}

    // Retrieve all products
	@GetMapping("/product")
	public List<ProductData> retrieveAllProducts() {
		log.info("Retrieve all products called.");
		return eCommerceService.retrieveAllProducts();
	}

    // Retrieve a product by ID
	@GetMapping("/product/{productId}")
	public ProductData retrieveProductById(@PathVariable Long productId) {
		log.info("Retrieving product with ID={}", productId);
		return eCommerceService.retrieveProductById(productId);
	}

    // Delete all products (Unsupported operation)
	@DeleteMapping("/product")
	public void deleteAllProducts() {
		log.info("Attempting to delete all products");
		throw new UnsupportedOperationException("Deleting all products is not allowed");
	}

    // Delete a product by ID
	@DeleteMapping("/product/{productId}")
	public Map<String, String> deleteProductById(@PathVariable Long productId) {
		log.info("Deleting product with ID={}", productId);
		eCommerceService.deleteProductById(productId);

		return Map.of("message", "Deletion of product with ID=" + productId + " was successful.");
	}
	
/////////////////CUSTOMER/ORDER/PRODUCT-RELATIONSHIPS////////////////////////////////////////////////

    // Add an order for a customer
	@PostMapping("/customer/{customerId}/orders")
	@ResponseStatus(code = HttpStatus.CREATED)
	public OrderData addOrderForCustomer(@PathVariable Long customerId, @RequestBody OrderData orderData) {
		log.info("Creating new order with ID={} for customer with ID={}", orderData, customerId);
		return eCommerceService.addOrderForCustomer(customerId, orderData);
	}
	
    // Add products to an order
	@PutMapping("/orders/{orderId}/products")
	public OrderData addProductsToOrder(@PathVariable Long orderId, @RequestBody List<Long> productIds) {
	    log.info("Adding products {} to order with ID={}", productIds, orderId);
	    return eCommerceService.addProductsToOrder(orderId, productIds);
	}
}
