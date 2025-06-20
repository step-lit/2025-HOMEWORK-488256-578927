package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashSet;
import java.util.Set; 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Direzione;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	
	private Stanza stanzaTest;
	private Stanza stanzaNord;
	private Attrezzo martello;
	private Attrezzo foglio;
	private Attrezzo torcia;
	private Set<Direzione> direzioni;
	
	
	@BeforeEach
	public void setUp() {
		
		this.stanzaTest = new Stanza("Stanza test");
		
		this.stanzaNord = new Stanza("Stanza nord");
		this.stanzaTest.impostaStanzaAdiacente("nord", this.stanzaNord); //stanzaTest ha la direzione nord (verso stanzaNord)
		this.stanzaNord.impostaStanzaAdiacente("sud", this.stanzaTest); //stanzaNord ha la direzione sud (verso stanzaTest)
		this.direzioni = new HashSet<Direzione>();
		
		this.martello = new Attrezzo("Martello", 2);
		this.foglio = new Attrezzo("Foglio", 0);
		this.torcia = new Attrezzo("Torcia", 1);
	}
	
	
	
	/**********************************************************************
	 *   metodi di test per il metodo impostaStanzaAdiacente() della      *
	 *   classe Stanza. viene testato anche getStanzaAdiacente(String d)  *
	 **********************************************************************/

	@Test
	public void testgetStanzaAdiacente() {
		//la stanza ha solo una stanza adiacente a nord;
		assertNotNull(this.stanzaTest.getStanzaAdiacente(Direzione.nord));
        assertNull(this.stanzaTest.getStanzaAdiacente(Direzione.sud));
        assertNull(this.stanzaTest.getStanzaAdiacente(Direzione.est));
        assertNull(this.stanzaTest.getStanzaAdiacente(Direzione.ovest));
	}
	
	@Test
	public void testImpostaStanzaAdiacenteNord() {
		//stanzaTest inizialmente ha solo la stanza in direzione nord come stanza adiacente;
		assertEquals(this.stanzaNord, this.stanzaTest.getStanzaAdiacente(Direzione.nord));
	}
	
	@Test
	public void testImpostaStanzaAdiacenteNordEst() {
		//la stanza ha già una stanza adiacente in direzione nord; viene inserita la nuova stanza ad est;
		Stanza stanzaEst = new Stanza("Stanza est");
		this.stanzaTest.impostaStanzaAdiacente("est", stanzaEst);
		assertEquals(stanzaEst, this.stanzaTest.getStanzaAdiacente(Direzione.est));
	}
	
	@Test
	public void testImpostaStanzaAdiacenteNuovaNord() {
		//la stanza ha già una stanza adiacente in direzione nord; viene sostituita la stanza nord;
		Stanza nuovaNord = new Stanza("Stanza nord nuova");
		this.stanzaTest.impostaStanzaAdiacente("nord", nuovaNord);
		assertEquals(nuovaNord, this.stanzaTest.getStanzaAdiacente(Direzione.nord));
	}
	
	
	
	/**********************************************************************
	 *   metodi di test per il metodo addAttrezzo() della classe Stanza.  *
	 *   viene testato anche il metodo getAttrezzo(String nomeAttrezzo)   *
	 **********************************************************************/
	
	@Test
	public void testgetAttrezzoNull() {
		//la stanza non ha attrezzi all'interno;
		String test = new String();
		assertNull(this.stanzaTest.getAttrezzo(test));
	}
	
	@Test
	public void testAddAttrezzoPiatto() {
		//viene aggiunto l'attrezzo piatto alla stanzaTest
		Attrezzo piatto = new Attrezzo("Piatto", 1);
		assertTrue(this.stanzaTest.addAttrezzo(piatto));
		assertEquals(piatto, this.stanzaTest.getAttrezzo("Piatto"));
	}
	
	@Test
	public void testAddAttrezzoFull() {
		//aggiungo 10 attrezzi (numero massimo): la stanza a quel punto ha finito lo spazio per gli attrezzi
		for(int i = 0; i<10; i++) {
			Integer numero = i;
			this.stanzaTest.addAttrezzo(new Attrezzo(numero.toString(),i));
		}
		
		Attrezzo chiaveInglese = new Attrezzo("Chiave Inglese", 1);
		assertFalse(this.stanzaTest.addAttrezzo(chiaveInglese));  //false perché l'array è pieno
		assertNull(this.stanzaTest.getAttrezzo("Chiave Inglese")); //null perché non è stato inserito
	}
	
	
	
	/**********************************************************************
	 *   metodi di test per il metodo getDirezioni() della classe Stanza  *
	 **********************************************************************/
	
	@Test
	public void testGetDirezioniNord() {
		//stanzaTest ha solo direzione nord da setUp
		this.direzioni.add(Direzione.nord);
		assertEquals(this.direzioni, this.stanzaTest.getDirezioni());
	}
	
	@Test
	public void testGetDirezioniStanzaNord() {
		//stanzaNord ha solo la direzione sud da setUp (verso stanzaTest)
		//se però non viene impostata la direzione sud di stanzaNord non si può tornare indietro
		assertTrue(this.stanzaNord.getDirezioni().size() == 1);
	}
	
	@Test
	public void testGetDirezioniNordEst() {
		//stanzaTest ottiene direzione est oltre a nord
		this.direzioni.add(Direzione.est);
		this.direzioni.add(Direzione.nord);
		
		Stanza stanzaEst = new Stanza("Stanza est");
		stanzaTest.impostaStanzaAdiacente("est", stanzaEst);
		
		assertEquals(this.direzioni, this.stanzaTest.getDirezioni());
	}
	
	
	/*************************************************************************
	 *   metodi di test per il metodo removeAttrezzo() della classe Stanza   *
	 *************************************************************************/
	
	@Test
	public void testRemoveAttrezzoNoAttrezzi() {
		//non ci sono attrezzi nella stanza
		assertFalse(this.stanzaTest.removeAttrezzo("Martello"));
	}
	
	@Test
	public void testRemoveAttrezzo() {
		//c'è solo l'attrezzo martello nella stanza
		stanzaTest.addAttrezzo(martello);
		assertTrue(this.stanzaTest.removeAttrezzo("Martello"));
	}
	
	@Test
	public void testRemoveAttrezzoNonPresente() {
		//c'è solo l'attrezzo martello e voglio rimuoverne un altro
		this.stanzaTest.addAttrezzo(martello);
		assertFalse(this.stanzaTest.removeAttrezzo("a"));
	}
	
	@Test
	public void testRemoveAttrezzoInMezzo() {
		//rimuove un attrezzo in mezzo all'array
		this.stanzaTest.addAttrezzo(this.foglio);
		this.stanzaTest.addAttrezzo(this.torcia);
		this.stanzaTest.addAttrezzo(this.martello);
		assertTrue(this.stanzaTest.removeAttrezzo("Torcia"));
	}
	
}
