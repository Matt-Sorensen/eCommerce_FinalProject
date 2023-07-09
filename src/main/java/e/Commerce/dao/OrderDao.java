package e.Commerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import e.Commerce.entity.Orders;

public interface OrderDao extends JpaRepository<Orders, Long> {

}
