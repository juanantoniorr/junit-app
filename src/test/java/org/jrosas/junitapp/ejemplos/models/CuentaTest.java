package org.jrosas.junitapp.ejemplos.models;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CuentaTest {

	@Test
	void testNombreCuenta() {
		Cuenta cuenta = new Cuenta("Juan", new BigDecimal("123.456") );
		String esperado = "Juan";
		String real = cuenta.getPersona();
		assertEquals(esperado, real);
		
	}

}
