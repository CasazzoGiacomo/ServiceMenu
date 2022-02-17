package it.omicron;

import it.omicron.util.ServiceMenuParser;

public class Principale {
	public static void main(String[] args) {
		ServiceMenuParser operazione = new ServiceMenuParser();
		operazione.deserializzaJson_CreaxlsFile();
		System.out.println("Creazione del file excel nella directory output andata a buon fine");
	}
}
