package application.data.repository.product;

import application.data.model.product.Pricing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PricingRepository extends JpaRepository<Pricing,Integer> {
}
