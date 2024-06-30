package RetailDiscountCalculator.controller;

import RetailDiscountCalculator.model.Bill;
import RetailDiscountCalculator.service.BillService;
import RetailDiscountCalculator.service.DiscountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillController {

    private static final Logger logger = LoggerFactory.getLogger(BillController.class);
    @Autowired
    private BillService billService;

    @Autowired
    private DiscountService discountService;

    @PostMapping("/calculate-discount")
    public double calculateDiscount(@RequestBody Bill bill) {

        logger.debug("Received request to calculate discount for bill: {}", bill);

        double discount = discountService.calculateTotalDiscount(bill);

        logger.info("Calculated discount: {}", discount);

        return discount;
    }

}
