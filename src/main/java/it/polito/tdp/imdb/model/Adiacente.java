package it.polito.tdp.imdb.model;

public class Adiacente implements Comparable<Adiacente>{
	
	private Director registaAdiacente;
	private int attoriCondivisi;
	
	public Adiacente(Director registaAdiacente, int attoriCondivisi) {
		super();
		this.registaAdiacente = registaAdiacente;
		this.attoriCondivisi = attoriCondivisi;
	}
	
	public Director getRegistaAdiacente() {
		return registaAdiacente;
	}
	
	public void setRegistaAdiacente(Director registaAdiacente) {
		this.registaAdiacente = registaAdiacente;
	}
	
	public int getAttoriCondivisi() {
		return attoriCondivisi;
	}
	
	public void setAttoriCondivisi(int attoriCondivisi) {
		this.attoriCondivisi = attoriCondivisi;
	}

	@Override
	public String toString() {
		return registaAdiacente.toString() + " - # Attori condivisi: " + attoriCondivisi;
	}

	@Override
	public int compareTo(Adiacente other) {
		return other.getAttoriCondivisi() - this.getAttoriCondivisi();
	}
	
}
