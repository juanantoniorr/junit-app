package org.jrosas.junitapp.ejemplos.models;

import static org.junit.jupiter.api.Assertions.*;
import java.math.BigDecimal;

import org.jrosas.junitapp.ejemplos.exceptions.DineroInsuficienteException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CuentaTest {

	@Test
	@DisplayName("Probando nombre cuenta")
	@Disabled
	void testNombreCuenta() {
		Cuenta cuenta = new Cuenta("Juan", new BigDecimal("123.456"));
		String esperado = "Juan";
		String real = cuenta.getPersona();
		assertEquals(esperado, real);

	}

	@Test
	@DisplayName("Probando saldo cuenta")
	void testSaldoCuenta() {
		Cuenta cuenta = new Cuenta("Juan", new BigDecimal("-5"));
		assertEquals(-5, cuenta.getSaldo().doubleValue());
		//Al pasarle una expresion lambda se optimiza la memoria ya que solo construye
		//el string si falla.
		assertFalse(cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0,() -> "La cuenta no tiene fondos");
	}

	@Test
	void testReferenciaCuenta() {
		Cuenta cuenta = new Cuenta("John", new BigDecimal("1000.50"));
		Cuenta cuenta2 = new Cuenta("John", new BigDecimal("1000.50"));
		// Pasa porque sobreescribi el metodo equals y compare por valor
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

	@Test
	void testTransferir() {
		Cuenta cuenta1 = new Cuenta("John", new BigDecimal("10000"));
		Cuenta cuenta2 = new Cuenta("Saul", new BigDecimal("1000"));
		Banco banco = new Banco();
		banco.setNombre("Bancomer");
		banco.transferir(cuenta1, cuenta2, new BigDecimal(500));
		assertEquals("9500", cuenta1.getSaldo().toPlainString());
		assertEquals("1500", cuenta2.getSaldo().toPlainString());

	}

	@Test
	void testRelacionBancoCuentas() {
		Cuenta cuenta1 = new Cuenta("John", new BigDecimal("10000"));
		Cuenta cuenta2 = new Cuenta("Saul", new BigDecimal("1000"));
		Banco banco = new Banco();
		banco.addCuenta(cuenta1);
		banco.addCuenta(cuenta2);

		banco.setNombre("Bancomer");
		banco.transferir(cuenta1, cuenta2, new BigDecimal(500));
		assertEquals("9500", cuenta1.getSaldo().toPlainString());
		assertEquals("1500", cuenta2.getSaldo().toPlainString());
		assertAll(() -> {
			assertEquals(2, banco.getCuentas().size());
		},
				// Tenemos relacion bidireccional
				() -> {
					assertEquals("Bancomer", cuenta1.getBanco().getNombre());
				}, () -> {
					// find first operacion final, get obtiene el elemento.
					// Metodo que busca si el nombre esta en el registro del banco
					assertEquals("Saul", banco.getCuentas().stream().filter((p) -> p.getPersona().equals("Saul"))
							.findFirst().get().getPersona());
				});

		

	}

}
