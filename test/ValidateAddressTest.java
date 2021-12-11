import controller.PlaceOrderController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
class ValidateAddressTest {

	private PlaceOrderController placeOrderController;
	@BeforeEach
	void setUp() throws Exception {
		placeOrderController=new PlaceOrderController();
	}
	@ParameterizedTest
	@CsvSource({
			"So 1 Dai Co Viet, true",
			"Ngo Cho Kham Thien, true",
			"@7 Ly Nam De,false",
			"Nguy Nhu - Kon Tum,false"
	})
	public void test(String address,boolean expected) {
		boolean isValided= placeOrderController.validateAddress(address);
		assertEquals(expected,isValided);
	}

}
