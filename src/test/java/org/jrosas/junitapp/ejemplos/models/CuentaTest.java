package org.jrosas.junitapp.ejemplos.models;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

import org.jrosas.junitapp.ejemplos.exceptions.DineroInsuficienteException;
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
	
	@Test
	void testDineroInsuficienteException() {
		Cuenta cuenta = new Cuenta("John", new BigDecimal("1000.50"));
		
		Exception ex = assertThrows(DineroInsuficienteException.class, () -> {
			cuenta.debito(new BigDecimal(1500));
			
			
		});
		String actual = ex.getMessage();
		String esperado = "Dinero Insuficiente";
		assertEquals(esperado, actual);
	}
	
}
