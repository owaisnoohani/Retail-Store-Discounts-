package RetailDiscountCalculator.service;

import RetailDiscountCalculator.model.Bill;
import RetailDiscountCalculator.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public Bill saveBill(Bill bill) {
        return billRepository.save(bill);
    }
}
