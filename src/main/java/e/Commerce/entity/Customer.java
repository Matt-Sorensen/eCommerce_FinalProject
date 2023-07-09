package e.Commerce.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // Establish a bidirectional relationship with Orders entity
    @JsonManagedReference
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "orderCustomer", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Orders> customerOrders = new ArrayList<>();

}
