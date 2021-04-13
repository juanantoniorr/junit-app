package org.jrosas.junitapp.ejemplos.models;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class CuentaTest {

	@Test
	void testNombreCuenta() {
		Cuenta cuenta = new Cuenta("Juan", new BigDecimal("123.456") );
		String esperado = "Juan";
		String real = cuenta.getPersona();
		assertEquals(esperado, real);
		
	}
	
	@Test 
	void testSaldoCuenta() {
		Cuenta cuenta = new Cuenta("Juan", new BigDecimal("123.456") );
		assertEquals(123.456, cuenta.getSaldo().doubleValue());
		assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0);
	}
	
	@Test
	void testReferenciaCuenta() {
		Cuenta cuenta = new Cuenta("John", new BigDecimal("1000.50"));
		Cuenta cuenta2 = new Cuenta("John", new BigDecimal("1000.50"));
		//Pasa porque sobreescribi el metodo equals y compare por valor
		assertEquals(cuenta, cuenta2);
	}
	//Restar 20
	@Test
	void testDebitoCuenta() {
		Cuenta cuenta = new Cuenta("Juan", new BigDecimal("123.456") );
		cuenta.debito(new BigDecimal(20));
		assertNotNull(cuenta.getSaldo());
		assertEquals(103, cuenta.getSaldo().intValue());
		
	}
	
	//Sumar 20
	@Test
	void testCreditoCuenta() {
		Cuenta cuenta = new Cuenta("Juan", new BigDecimal("123.456") );
		cuenta.credito(new BigDecimal(20));
		assertNotNull(cuenta.getSaldo());
		assertEquals(143, cuenta.getSaldo().intValue());
		
	}

}
