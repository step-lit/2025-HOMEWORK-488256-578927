package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;

class ComandoPosaTest {
	
	private IO io;
	private ComandoPosa comando;
	private Partita partita;
	private Stanza stanzaTest;
	private Attrezzo foglio;
	private Attrezzo torcia;
	private Attrezzo martello;
	private Attrezzo[] attrezziStanza;
	private Attrezzo[] attrezziBorsa;
	
	
	@BeforeEach
	void setUp() {
		this.io = new IOConsole();
		this.comando = new ComandoPosa(this.io); //all'inizio non ha un parametro
		this.partita = new Partita();
		this.stanzaTest = new Stanza("test");
		this.foglio = new Attrezzo("Foglio", 0);
		this.torcia = new Attrezzo("Torcia", 1);
		this.martello = new Attrezzo("Martello", 2);
		
		this.attrezziStanza = new Attrezzo[10]; //array di test per stanza
		this.attrezziBorsa = new Attrezzo[10]; //array di test per borsa
		
		/* aggiungiamo alla borsa tre attrezzi diversi */
		this.partita.getGiocatore().getBorsa().addAttrezzo(this.foglio);
		this.partita.getGiocatore().getBorsa().addAttrezzo(this.torcia);
		this.partita.getGiocatore().getBorsa().addAttrezzo(this.martello);
		
		/* impostiamo la stanza corrente alla stanzaTest senza nulla */
		this.partita.getLabirinto().setStanzaCorrente(this.stanzaTest);
	}
	
	@Test
	void testEseguiNoParametro() {
		this.comando.esegui(this.partita);
		
		this.attrezziBorsa[0] = this.foglio;
		this.attrezziBorsa[1] = this.torcia;
		this.attrezziBorsa[2] = this.martello;
		
		assertArrayEquals(this.attrezziStanza, this.partita.getLabirinto().getStanzaCorrente().getAttrezzi());
		assertArrayEquals(this.attrezziBorsa, this.partita.getGiocatore().getBorsa().getAttrezzi());
	}

	@Test
	void testEseguiConParametro() {
		this.comando.setParametro("Torcia");
		this.comando.esegui(this.partita); //la torcia è stata posato
		
		this.attrezziStanza[0] = this.torcia;
		this.attrezziBorsa[0] = this.foglio;
		this.attrezziBorsa[1] = this.martello;
		
		assertArrayEquals(this.attrezziStanza, this.partita.getLabirinto().getStanzaCorrente().getAttrezzi());
		assertArrayEquals(this.attrezziBorsa, this.partita.getGiocatore().getBorsa().getAttrezzi());
	}
	
	@Test
	void testEseguiConParametroErrato() {
		this.comando.setParametro("Libro");
		this.comando.esegui(this.partita);
		
		this.attrezziBorsa[0] = this.foglio;
		this.attrezziBorsa[1] = this.torcia;
		this.attrezziBorsa[2] = this.martello;
		
		assertArrayEquals(this.attrezziStanza, this.partita.getLabirinto().getStanzaCorrente().getAttrezzi());
		assertArrayEquals(this.attrezziBorsa, this.partita.getGiocatore().getBorsa().getAttrezzi());
	}
}
