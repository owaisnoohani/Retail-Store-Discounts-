package RetailDiscountCalculator;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RetailDiscountCalculatorApplicationTests {

	@Test
	void contextLoads() {
		DiscountServiceTest discountServiceTest = new DiscountServiceTest();

		discountServiceTest.testEmployeeDiscountWithNonGroceries();
		discountServiceTest.setUp();
		discountServiceTest.testAffiliateDiscountWithNonGroceries();
		discountServiceTest.testLongTermCustomerDiscountWithNonGroceries();
		discountServiceTest.testNoPercentageDiscountWithGroceries();
		discountServiceTest.testMixedProductsWithEmployeeDiscount();


	}

}
