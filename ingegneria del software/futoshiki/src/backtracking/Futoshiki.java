package backtracking;

import java.awt.Point;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import model.Constraint;
import model.Scheme;

public class Futoshiki extends Problema<Point,Integer>{
	
	private int dimension;
	private Scheme scheme;
	private List<Point> puntiDiScelta;
	private List<Scheme> solutions;
	
	public Futoshiki(int maxSolutions,int dimension, Scheme scheme){
		super(maxSolutions);
		this.dimension=dimension;
		this.scheme=scheme;
		puntiDiScelta=scheme.getEmptyCell();
		solutions=new LinkedList<>();
		
	}
	@Override
	public void risolvi() {
		if (!scheme.check())
			return;

		super.risolvi();
	}
	
	@Override
	protected Point primoPuntoDiScelta() {
		return puntiDiScelta.get(0);
	}

	@Override
	protected Point prossimoPuntoDiScelta(Point ps) {
		int index = puntiDiScelta.indexOf(ps);
		return puntiDiScelta.get(index+1);
	}

	@Override
	protected Point ultimoPuntoDiScelta() {
		return puntiDiScelta.get(puntiDiScelta.size()-1);
	}

	@Override
	protected Integer primaScelta(Point ps) {
		return 1;
	}

	@Override
	protected Integer prossimaScelta(Integer s) {
		return s+1;
	}

	@Override
	protected Integer ultimaScelta(Point ps) {
		return dimension;
	}

	@Override
	protected boolean assegnabile(Integer scelta, Point puntoDiScelta) {
		int rowIndex=puntoDiScelta.x;
		int columnIndex=puntoDiScelta.y;
		return scheme.checkValuesColumn(scelta, columnIndex) && scheme.checkValuesRow(scelta, rowIndex) && scheme.checkConstraints(scelta, rowIndex,columnIndex) ;
	}

	@Override
	protected void assegna(Integer scelta, Point puntoDiScelta) {
		scheme.setValue(scelta, puntoDiScelta.x, puntoDiScelta.y);
	}

	@Override
	protected void deassegna(Integer scelta, Point puntoDiScelta) {
		scheme.setValue(0, puntoDiScelta.x, puntoDiScelta.y);
	}

	@Override
	protected Point precedentePuntoDiScelta(Point puntoDiScelta) {
		int index = puntiDiScelta.indexOf(puntoDiScelta);
		return puntiDiScelta.get(index-1);
	}

	@Override
	protected Integer ultimaSceltaAssegnataA(Point puntoDiScelta) {
		return scheme.getValue(puntoDiScelta.x, puntoDiScelta.y);
	}

	@Override
	protected void scriviSoluzione(int nr_sol) {
		Scheme solution = scheme.getStructure();
		solutions.add(solution);
	}
	
	public List<Scheme> getSolutions(){
		return solutions;
	}
	
	
	
	
	

}
