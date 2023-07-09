package e.Commerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import e.Commerce.entity.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long> {

}
