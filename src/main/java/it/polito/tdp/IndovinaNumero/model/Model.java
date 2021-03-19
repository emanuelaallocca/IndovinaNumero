package it.polito.tdp.IndovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {

	private final int NMAX = 100;
	private final int TMAX = 8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco = false;
	
	private Set <Integer> tentativi; //INTEGER CLASSE CON PROPRIETà DEFINITE
	
	//CREO UN METODO PUBBLICO VOID, NON DEVE TORNARE NESSUN RISULTATO
	
	public void nuovaPartita() {
		//gestione inizio nuova partita
    	this.segreto = (int) (Math.random() * NMAX) +1;
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	
    	this.tentativi = new HashSet<Integer>();
	}
	
	public int tentativo (int tentativo) {
		
		//controllo se la partita è in corso
		
		if (!inGioco) { //SCATENO UN'ECCEZIONE SE HO UNO STATO ILLEGALE DEL SISTEMA
			throw new IllegalStateException("La partita è già terminata!");
		}
		
		//controllo dell'input
		if (!tentativoValido(tentativo)) {
			//ho numero inserito fuori dal range, ho eccezioni fatte ad hoc
			throw new InvalidParameterException("Devi inserire un numero tra 1 e NMAX =100; non puoi inserire due volte lo stesso numero");
		}
		
		//sono qui --> IL TENTATIVO è VALIDO
		 this.tentativiFatti++;
		 this.tentativi.add(tentativo);
		 if (this.tentativiFatti==this.TMAX) {
			 this.inGioco= false;}
		 //LA PROSSIMA VOLTA CHE IL METODO VERRà ESEGUITO SI FERMERà PRIMA, NON è PIù IN GIOCO
		 
		 if (tentativo == this.segreto) {
			 this.inGioco=false;
			 return 0;
		 }else if (tentativo <this.segreto)
			 return -1;
		 else 
			 return 1;
		 
	}
	
	private boolean tentativoValido (int tentativo) {
		if (tentativo<1 || tentativo >NMAX)
			return false;
//		else
//			return true;
		if (this.tentativi.contains(tentativo))
			return false;
		return true;
	}

	public int getSegreto() {
		return segreto;
	}

	public int getTentativiFatti() {
		return tentativiFatti;
	}

	public boolean isInGioco() {
		return inGioco;
	}

	public int getNMAX() {
		return NMAX;
	}

	public int getTMAX() {
		return TMAX;
	}
	
	
}
