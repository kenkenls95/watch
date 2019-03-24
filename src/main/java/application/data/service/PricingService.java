package application.data.service;

import application.data.repository.product.PricingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PricingService {
    @Autowired
    PricingRepository pricingRepository;
}
