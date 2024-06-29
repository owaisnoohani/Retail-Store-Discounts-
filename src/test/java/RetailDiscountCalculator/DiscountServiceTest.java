package RetailDiscountCalculator;

import RetailDiscountCalculator.service.DiscountService;
import RetailDiscountCalculator.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DiscountServiceTest {

    private DiscountService discountService;

    @BeforeEach
    public void setUp() {
        discountService = new DiscountService();
    }

    @Test
    public void testEmployeeDiscountWithNonGroceries() {
        User employee = new User();
        employee.setRole("EMPLOYEE");
        employee.setJoiningDate(LocalDate.now().minusYears(3));

        Product product1 = new Product();
        product1.setPrice(200);
        product1.setCategory("OTHERS");

        Product product2 = new Product();
        product2.setPrice(100);
        product2.setCategory("OTHERS");

        Bill bill = new Bill();
        bill.setUser(employee);
        bill.setProducts(Arrays.asList(product1, product2));

        double discount = discountService.calculateTotalDiscount(bill);
        assertEquals(75, discount); // 30% of 300 + $5 flat discount
    }

    @Test
    public void testAffiliateDiscountWithNonGroceries() {
        User affiliate = new User();
        affiliate.setRole("AFFILIATE");
        affiliate.setJoiningDate(LocalDate.now().minusYears(3));

        Product product1 = new Product();
        product1.setPrice(200);
        product1.setCategory("OTHERS");

        Product product2 = new Product();
        product2.setPrice(100);
        product2.setCategory("OTHERS");

        Bill bill = new Bill();
        bill.setUser(affiliate);
        bill.setProducts(Arrays.asList(product1, product2));

        double discount = discountService.calculateTotalDiscount(bill);
        assertEquals(35, discount); // 10% of 300 + $10 flat discount
    }

    @Test
    public void testLongTermCustomerDiscountWithNonGroceries() {
        User customer = new User();
        customer.setRole("CUSTOMER");
        customer.setJoiningDate(LocalDate.now().minusYears(3));

        Product product1 = new Product();
        product1.setPrice(200);
        product1.setCategory("OTHERS");

        Product product2 = new Product();
        product2.setPrice(100);
        product2.setCategory("OTHERS");

        Bill bill = new Bill();
        bill.setUser(customer);
        bill.setProducts(Arrays.asList(product1, product2));

        double discount = discountService.calculateTotalDiscount(bill);
        assertEquals(20, discount); // 5% of 300 + $10 flat discount
    }

    @Test
    public void testNoPercentageDiscountWithGroceries() {
        User customer = new User();
        customer.setRole("EMPLOYEE");
        customer.setJoiningDate(LocalDate.now().minusYears(3));

        Product product1 = new Product();
        product1.setPrice(200);
        product1.setCategory("GROCERY");

        Product product2 = new Product();
        product2.setPrice(100);
        product2.setCategory("GROCERY");

        Bill bill = new Bill();
        bill.setUser(customer);
        bill.setProducts(Arrays.asList(product1, product2));

        double discount = discountService.calculateTotalDiscount(bill);
        assertEquals(15, discount); // No percentage discount + $15 flat discount
    }

    @Test
    public void testMixedProductsWithEmployeeDiscount() {
        User employee = new User();
        employee.setRole("EMPLOYEE");
        employee.setJoiningDate(LocalDate.now().minusYears(3));

        Product product1 = new Product();
        product1.setPrice(200);
        product1.setCategory("GROCERY");

        Product product2 = new Product();
        product2.setPrice(100);
        product2.setCategory("OTHERS");

        Bill bill = new Bill();
        bill.setUser(employee);
        bill.setProducts(Arrays.asList(product1, product2));

        double discount = discountService.calculateTotalDiscount(bill);
        assertEquals(45, discount); // 30% of 100 + $10 flat discount
    }
}
