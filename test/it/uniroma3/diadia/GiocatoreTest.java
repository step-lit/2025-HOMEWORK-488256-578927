package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

class GiocatoreTest {

	
	private Giocatore giocatore;
	
	@BeforeEach
	public void setUp() {
		this.giocatore = new Giocatore();
	}
	
	
	/**********************************************************************
	 *    metodi di test per il metodo getCfu() della classe Giocatore    *
	 *    viene testato anche il metodo setCfu()                          *
	 **********************************************************************/
	
	@Test
	public void testGetCfu() {
		//test dei cfu iniziali
		assertEquals(20, this.giocatore.getCfu());
	}
	
	@Test
	public void testGetCfuDiversi() {
		//vengono impostati i cfu ad un altro numero
		giocatore.setCfu(15);
		assertEquals(15, this.giocatore.getCfu());
	}
	
	@Test
	public void testGetBorsa() {
		//il giocatore viene inizializzato con un oggetto borsa da costruttore
		assertNotNull(this.giocatore.getBorsa());
	}
	
	
}
