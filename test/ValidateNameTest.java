import controller.PlaceOrderController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidateNameTest {

	private PlaceOrderController placeOrderController;
	@BeforeEach
	void setUp() throws Exception {
		placeOrderController=new PlaceOrderController();
	}
	@ParameterizedTest
	@CsvSource({
			"Truong Giang, true",
			"01234, false",
			"123abc,false",
			"312 3123,false"
	})
	public void test(String name,boolean expected) {
		boolean isValided= placeOrderController.validateName(name);
		assertEquals(expected,isValided);
	}

}
