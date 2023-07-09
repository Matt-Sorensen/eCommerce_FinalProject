package e.Commerce.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import e.Commerce.entity.Product;

public interface ProductDao extends JpaRepository<Product, Long> {

}
