import controller.PlaceOrderController;
import controller.PlaceRushOrderController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidateRushAddressTest {

	private PlaceRushOrderController placeRushOrderController;
	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController=new PlaceRushOrderController();
	}
	@ParameterizedTest
	@CsvSource({
			"So 1 Dai Co Viet, true",
			"Ngo Cho Kham Thien, true",
			"@7 Ly Nam De,false",
			"Nguy Nhu - Kon Tum,false"
	})
	public void test(String address,boolean expected) {
		boolean isValided= placeRushOrderController.validateAddress(address);
		assertEquals(expected,isValided);
	}

}
