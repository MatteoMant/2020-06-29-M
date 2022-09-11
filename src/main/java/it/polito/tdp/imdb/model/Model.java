package it.polito.tdp.imdb.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.imdb.db.ImdbDAO;

public class Model {
	
	ImdbDAO dao;
	private Graph<Director, DefaultWeightedEdge> grafo;
	private Map<Integer, Director> idMap;
	
	public Model() {
		dao = new ImdbDAO();
		idMap = new HashMap<>();
		dao.listAllDirectors(idMap);
	}
	
	public void creaGrafo(int anno) {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		// Aggiunta dei vertici
		Graphs.addAllVertices(this.grafo, this.dao.listAllDirectorsWithYear(anno));
		
		// Aggiunta degli archi
		for (Adiacenza a : this.dao.getAllAdiacenze(idMap, anno)) {
			Graphs.addEdge(this.grafo, a.getD1(), a.getD2(), a.getPeso());
		} 
	}
	
	public List<Adiacente> getVerticiAdiacenti(Director regista){
		List<Adiacente> adiacenti = new LinkedList<>();
		List<Director> vicini = Graphs.neighborListOf(this.grafo, regista);
		for (Director d : vicini) {
			int attoriCondivisi = (int)this.grafo.getEdgeWeight(this.grafo.getEdge(regista, d));
			adiacenti.add(new Adiacente(d, attoriCondivisi));
		}
		return adiacenti;
	}
	
	public Set<Director> getAllVertici(){
		return this.grafo.vertexSet();
	}
	
	public int getNumVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int getNumArchi() {
		return this.grafo.edgeSet().size();
	}
}
