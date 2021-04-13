package org.jrosas.junitapp.ejemplos.models;

import java.math.BigDecimal;

public class Cuenta {
	
	private String persona;
	private BigDecimal saldo;
	
	
	public Cuenta (String persona, BigDecimal saldo) {
		this.persona = persona;
		this.saldo = saldo;
	}
	
	public void credito(BigDecimal monto) {
		//Esto falla porque bigdecimal es inmutable, no se puede cambiar
		//this.saldo.subtract(monto);
		this.saldo = this.saldo.add(monto);
		
		
		
	}
	public void debito(BigDecimal monto) {
		//Esto falla porque bigdecimal es inmutable, no se puede cambiar
		//this.saldo.add(monto);
		
		this.saldo = this.saldo.subtract(monto);
	}
	
	
	
	public String getPersona() {
		return persona;
	}
	public void setPersona(String persona) {
		this.persona = persona;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}


//Sobreescribo el metodo equals para comparar por valor 
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Cuenta)) {
			return false;
		}
		Cuenta c =  (Cuenta)obj;
		if (this.persona == null || this.saldo==null) {
			return false;
		} 
		
		
		return this.persona.equals(c.getPersona()) && this.saldo.equals(c.getSaldo());
	}
	

}
