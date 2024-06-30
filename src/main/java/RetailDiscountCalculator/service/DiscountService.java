package RetailDiscountCalculator.service;


import RetailDiscountCalculator.model.Bill;
import RetailDiscountCalculator.model.Product;
import RetailDiscountCalculator.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DiscountService {

    private static final Logger logger = LoggerFactory.getLogger(BillService.class);
    /*public double calculateTotalDiscount(Bill bill) {
        logger.debug("Calculating discount for bill: {}", bill);

        User user = bill.getUser();
        List<Product> products = bill.getProducts();

        double totalBill = products.stream().mapToDouble(Product::getPrice).sum();
        double percentageDiscount = calculatePercentageDiscount(user, products);
        double flatDiscount = calculateFlatDiscount(totalBill);

        return (totalBill * percentageDiscount / 100) + flatDiscount;
    }

    private double calculatePercentageDiscount(User user, List<Product> products) {

        logger.debug("Calculating percentage discount for user: {}", user);

        boolean hasGroceries = products.stream().anyMatch(p -> p.getCategory().equalsIgnoreCase("GROCERY"));

        if (hasGroceries) {
            products = products.stream().filter(p -> !p.getCategory().equalsIgnoreCase("GROCERY")).toList();
        }

        double maxDiscount = 0;

        if (user.getRole().equalsIgnoreCase("EMPLOYEE")) {
            maxDiscount = 30;
        } else if (user.getRole().equalsIgnoreCase("AFFILIATE")) {
            maxDiscount = 10;
        } else {
            long years = ChronoUnit.YEARS.between(user.getJoiningDate(), LocalDate.now());

            if (years > 2) {
                maxDiscount = 5;
            }
        }

        double nonGroceryTotal = products.stream().mapToDouble(Product::getPrice).sum();
        return (nonGroceryTotal * maxDiscount) / 100;
    }

    private double calculateFlatDiscount(double totalBill) {
        return (int) (totalBill / 100) * 5;
    }*/
    public double calculateTotalDiscount(Bill bill) {
        logger.debug("Calculating discount for bill: {}", bill);

        User user = bill.getUser();
        List<Product> products = bill.getProducts();

        double totalBill = products.stream().mapToDouble(Product::getPrice).sum();
        double percentageDiscount = calculatePercentageDiscount(user, products);
        double flatDiscount = calculateFlatDiscount(totalBill);

        return percentageDiscount + flatDiscount;
    }

    private double calculatePercentageDiscount(User user, List<Product> products) {
        logger.debug("Calculating percentage discount for user: {}", user);

        double maxDiscount = 0;

        if (user.getRole().equalsIgnoreCase("EMPLOYEE")) {
            maxDiscount = 30;
        } else if (user.getRole().equalsIgnoreCase("AFFILIATE")) {
            maxDiscount = 10;
        } else {
            long years = ChronoUnit.YEARS.between(user.getJoiningDate(), LocalDate.now());
            if (years > 2) {
                maxDiscount = 5;
            }
        }

        double nonGroceryTotal = products.stream()
                .filter(p -> !p.getCategory().equalsIgnoreCase("GROCERY"))
                .mapToDouble(Product::getPrice).sum();

        double percentageDiscount = (nonGroceryTotal * maxDiscount) / 100;
        logger.debug("Percentage discount calculated: {}", percentageDiscount);
        return percentageDiscount;
    }

    private double calculateFlatDiscount(double totalBill) {
        logger.debug("Calculating flat discount for total bill: {}", totalBill);
        double flatDiscount = (int) (totalBill / 100) * 5;
        logger.debug("Flat discount calculated: {}", flatDiscount);
        return flatDiscount;
    }
}
