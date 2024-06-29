package RetailDiscountCalculator.service;


import RetailDiscountCalculator.model.Bill;
import RetailDiscountCalculator.model.Product;
import RetailDiscountCalculator.model.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class DiscountService {

    public double calculateTotalDiscount(Bill bill) {
        User user = bill.getUser();
        List<Product> products = bill.getProducts();

        double totalBill = products.stream().mapToDouble(Product::getPrice).sum();
        double percentageDiscount = calculatePercentageDiscount(user, products);
        double flatDiscount = calculateFlatDiscount(totalBill);

        return (totalBill * percentageDiscount / 100) + flatDiscount;
    }

    private double calculatePercentageDiscount(User user, List<Product> products) {
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
    }
}
