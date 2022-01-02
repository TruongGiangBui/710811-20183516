package controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ValidateAddressTest {
			private PlaceOrderController placeOrderController;
			
			@BeforeEach
			void setUp() throws Exception {
				placeOrderController = new PlaceOrderController();
			}

			@ParameterizedTest
			@CsvSource({
				"045 Phan chu trinh,true",
				"So 1 dai co viet,true",
				"So 1 @#!$,false",
				"Le dai H@nh,false"
			})
			
			void test(String address, boolean expected) {
				boolean isValid = placeOrderController.validateAddress(address);
				assertEquals(expected, isValid);
			}

}
