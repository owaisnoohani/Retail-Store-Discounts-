package RetailDiscountCalculator.controller;

import RetailDiscountCalculator.model.Bill;
import RetailDiscountCalculator.service.BillService;
import RetailDiscountCalculator.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private DiscountService discountService;

    @PostMapping("/calculate-discount")
    public double calculateDiscount(@RequestBody Bill bill) {
        return discountService.calculateTotalDiscount(bill);
    }
}
