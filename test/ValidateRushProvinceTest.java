import controller.PlaceRushOrderController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidateRushProvinceTest {

	private PlaceRushOrderController placeRushOrderController;
	@BeforeEach
	void setUp() throws Exception {
		placeRushOrderController=new PlaceRushOrderController();
	}
	@ParameterizedTest
	@CsvSource({
			"Hà Nội, true",
			"Đà Nẵng, true",
			"Hải Phòng,false",
			"Hà Tĩnh,false"
	})
	public void test(String province,boolean expected) {
		boolean isValided= placeRushOrderController.validateProvince(province);
		assertEquals(expected,isValided);
	}

}
