import controller.PlaceOrderController;
import controller.PlaceRushOrderController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidateRushNameTest {

	private PlaceRushOrderController placeRushOrderController;
	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController=new PlaceRushOrderController();
	}
	@ParameterizedTest
	@CsvSource({
			"Truong Giang, true",
			"01234, false",
			"123abc,false",
			"312 3123,false"
	})
	public void test(String name,boolean expected) {
		boolean isValided= placeRushOrderController.validateName(name);
		assertEquals(expected,isValided);
	}

}
