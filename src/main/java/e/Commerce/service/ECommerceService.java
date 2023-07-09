package e.Commerce.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import e.Commerce.controller.model.CustomerData;
import e.Commerce.controller.model.OrderData;
import e.Commerce.controller.model.ProductData;
import e.Commerce.dao.CustomerDao;
import e.Commerce.dao.OrderDao;
import e.Commerce.dao.ProductDao;
import e.Commerce.entity.Customer;
import e.Commerce.entity.Orders;
import e.Commerce.entity.Product;

@Service
public class ECommerceService {

	@Autowired
	private CustomerDao customerDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private OrderDao orderDao;

//////////////////////////CUSTOMER//////////////////////////////////////////	
	
    /**
     * Saves a customer or updates an existing customer.
     *
     * @param customerData The customer data to be saved or updated.
     * @return The saved or updated customer data.
     */
	@Transactional(readOnly = false)
	public CustomerData saveCustomer(CustomerData customerData) {
		Long customerId = customerData.getCustomerId();
		Customer customer = findOrCreateCustomer(customerId);

		setFieldsInCustomer(customer, customerData);
		return new CustomerData(customerDao.save(customer));
	}

    /**
     * Sets the fields of a customer entity based on the provided customer data.
     *
     * @param customer      The customer entity to set the fields.
     * @param customerData  The customer data containing the field values.
     */
	private void setFieldsInCustomer(Customer customer, CustomerData customerData) {
		customer.setUsername(customerData.getUsername());
		customer.setPassword(customerData.getPassword());
		customer.setEmail(customerData.getEmail());
		customer.setFirstName(customerData.getFirstName());
		customer.setLastName(customerData.getLastName());
		customer.setCity(customerData.getCity());
		customer.setState(customerData.getState());
		customer.setZip(customerData.getZip());
		customer.setPhone(customerData.getPhone());
	}

    /**
     * Finds an existing customer by ID or creates a new customer if the ID is null.
     *
     * @param customerId The ID of the customer to find or create.
     * @return The found customer entity or a new customer entity.
     */
	private Customer findOrCreateCustomer(Long customerId) {
		Customer customer;

		if (Objects.isNull(customerId)) {
			customer = new Customer();
		} else {
			customer = findCustomerById(customerId);
		}

		return customer;
	}

    /**
     * Finds a customer by ID.
     *
     * @param customerId The ID of the customer to find.
     * @return The found customer entity.
     * @throws NoSuchElementException If no customer with the given ID is found.
     */
	private Customer findCustomerById(Long customerId) {
		return customerDao.findById(customerId)
				.orElseThrow(() -> new NoSuchElementException("Customer with ID=" + customerId + " was not found."));
	}

    /**
     * Retrieves all customers.
     *
     * @return A list of all customer data.
     */
	@Transactional(readOnly = true)
	public List<CustomerData> retrieveAllCustomers() {
		List<Customer> customers = customerDao.findAll();
		List<CustomerData> result = new LinkedList<>();

		for (Customer customer : customers) {
			result.add(new CustomerData(customer));
		}

		return result;

	}

    /**
     * Retrieves a customer by ID.
     *
     * @param customerId The ID of the customer to retrieve.
     * @return The customer data for the specified ID.
     */
	@Transactional(readOnly = false)
	public CustomerData retrieveCustomerById(Long customerId) {
		Customer customer = findCustomerById(customerId);
		return new CustomerData(customer);
	}


    /**
     * Deletes a customer by ID.
     *
     * @param customerId The ID of the customer to delete.
     */
	@Transactional(readOnly = false)
	public void deleteCustomerById(Long customerId) {
		Customer customer = findCustomerById(customerId);
		customerDao.delete(customer);
	}

//////////////////////ORDER////////////////////////////
	

    /**
     * Saves an order or updates an existing order.
     *
     * @param orderData The order data to be saved or updated.
     * @return The saved or updated order data.
     */
	@Transactional(readOnly = false)
	public OrderData saveOrder(OrderData orderData) {
		Long orderId = orderData.getOrderId();
		Orders order = findOrCreateOrder(orderId);

		setFieldsInOrder(order, orderData);
		order.setActiveOrder(true);
		return new OrderData(orderDao.save(order));
	}

    /**
     * Sets the fields of an order entity based on the provided order data.
     *
     * @param order      The order entity to set the fields.
     * @param orderData  The order data containing the field values.
     */
	private void setFieldsInOrder(Orders orders, OrderData orderData) {
		orders.setDatePlaced(orderData.getDatePlaced());
		orders.setActiveOrder(orderData.isActiveOrder());
	}

    /**
     * Finds an existing order by ID or creates a new order if the ID is null.
     *
     * @param orderId The ID of the order to find or create.
     * @return The found order entity or a new order entity.
     */
	private Orders findOrCreateOrder(Long orderId) {
		Orders orders;

		if (Objects.isNull(orderId)) {
			orders = new Orders();
		} else {
			orders = findOrderById(orderId);
		}

		return orders;
	}


    /**
     * Finds an order by ID.
     *
     * @param orderId The ID of the order to find.
     * @return The found order entity.
     * @throws NoSuchElementException If no order with the given ID is found.
     */
	private Orders findOrderById(Long orderId) {
		return orderDao.findById(orderId)
				.orElseThrow(() -> new NoSuchElementException("Orders with ID=" + orderId + " was not found."));
	}


    /**
     * Retrieves all orders.
     *
     * @return A list of all order data.
     */
	@Transactional(readOnly = true)
	public List<OrderData> retrieveAllOrders() {
		List<Orders> allOrders = orderDao.findAll();
		List<OrderData> result = new LinkedList<>();

		for (Orders orders : allOrders) {
			result.add(new OrderData(orders));
		}

		return result;

	}

    /**
     * Retrieves an order by ID.
     *
     * @param orderId The ID of the order to retrieve.
     * @return The order data for the specified ID.
     */
	@Transactional(readOnly = false)
	public OrderData retrieveOrderById(Long orderId) {
		Orders orders = findOrderById(orderId);
		return new OrderData(orders);
	}

    /**
     * Deletes an order by ID.
     *
     * @param orderId The ID of the order to delete.
     */
	@Transactional(readOnly = false)
	public void deleteOrderById(Long orderId) {
		Orders orders = findOrderById(orderId);
		orderDao.delete(orders);
	}
	

////////////////////////////////PRODUCT////////////////////////////////////////////////
	
    /**
     * Saves a product or updates an existing product.
     *
     * @param productData The product data to be saved or updated.
     * @return The saved or updated product data.
     */
	@Transactional(readOnly = false)
	public ProductData saveProduct(ProductData productData) {
		Long productId = productData.getProductId();
		Product product = findOrCreateProduct(productId);

		setFieldsInProduct(product, productData);
		return new ProductData(productDao.save(product));
	}

    /**
     * Sets the fields of a product entity based on the provided product data.
     *
     * @param product     The product entity to set the fields.
     * @param productData The product data containing the field values.
     */
	private void setFieldsInProduct(Product product, ProductData productData) {
		product.setProductName(productData.getProductName());
		product.setProductDescription(productData.getProductDescription());
		product.setProductPrice(productData.getProductPrice());
	}

    /**
     * Finds an existing product by ID or creates a new product if the ID is null.
     *
     * @param productId The ID of the product to find or create.
     * @return The found product entity or a new product entity.
     */
	private Product findOrCreateProduct(Long productId) {
		Product product;

		if (Objects.isNull(productId)) {
			product = new Product();
		} else {
			product = findProductById(productId);
		}

		return product;
	}

    /**
     * Finds a product by ID.
     *
     * @param productId The ID of the product to find.
     * @return The found product entity.
     * @throws NoSuchElementException If no product with the given ID is found.
     */
	private Product findProductById(Long productId) {
		return productDao.findById(productId)
				.orElseThrow(() -> new NoSuchElementException("Product with ID=" + productId + " was not found."));
	}

    /**
     * Retrieves all products.
     *
     * @return A list of all product data.
     */
	@Transactional(readOnly = true)
	public List<ProductData> retrieveAllProducts() {
		List<Product> products = productDao.findAll();
		List<ProductData> result = new LinkedList<>();

		for (Product product : products) {
			result.add(new ProductData(product));
		}

		return result;

	}

    /**
     * Retrieves a product by ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return The product data for the specified ID.
     */
	@Transactional(readOnly = false)
	public ProductData retrieveProductById(Long productId) {
		Product product = findProductById(productId);
		return new ProductData(product);
	}

    /**
     * Deletes a product by ID.
     *
     * @param productId The ID of the product to delete.
     */
	@Transactional(readOnly = false)
	public void deleteProductById(Long productId) {
		Product product = findProductById(productId);
		productDao.delete(product);
	}
	
/////////////////CUSTOMER/ORDER/PRODUCT-RELATIONSHIPS////////////////////////////////////////////////

    /**
     * Adds multiple products to an existing order.
     *
     * @param orderId    The ID of the order to add the products to.
     * @param productIds The IDs of the products to add.
     * @return The updated order data.
     */
	@Transactional(readOnly = false)
	public OrderData addProductsToOrder(Long orderId, List<Long> productIds) {
	    Orders order = findOrderById(orderId);
	    List<Product> products = productDao.findAllById(productIds);
	    order.getOrderProducts().addAll(products);
	    return new OrderData(orderDao.save(order));
	}

    /**
     * Adds an order for a customer.
     *
     * @param customerId The ID of the customer to add the order for.
     * @param orderData  The order data to be added.
     * @return The added order data.
     */
	@Transactional(readOnly = false)
	public OrderData addOrderForCustomer(Long customerId, OrderData orderData) {
	    Customer customer = findCustomerById(customerId);
	    Orders order = new Orders();

	    setFieldsInOrder(order, orderData);
	    order.setActiveOrder(true); 
	    order.setOrderCustomer(customer);

	    customer.getCustomerOrders().add(order);

	    orderDao.save(order);
	    customerDao.save(customer);

	    return new OrderData(order);
	}
}
