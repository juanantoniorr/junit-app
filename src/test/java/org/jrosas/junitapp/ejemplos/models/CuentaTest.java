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
		assertEquals(2, banco.getCuentas().size());
		//Tenemos relacion bidireccional 
		assertEquals("Bancomer", cuenta1.getBanco().getNombre());
		//find first operacion final, get obtiene el elemento.
		//Metodo que busca si el nombre esta en el registro del banco
		assertEquals("Saul", banco.getCuentas().stream()
				.filter((p) -> p.getPersona().equals("Saul")).
				findFirst().get().getPersona());
	}
	
}
