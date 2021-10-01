package backtracking;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;

import entity.Scheme;

public class Futoshiki extends Problema<Point,Integer>{
	private Scheme scheme;
	private List<Scheme> solution;
	private Point p_start;
	private Point p_final;
	private int dim;


	public Futoshiki ( int max_solution,Scheme scheme){
		super(max_solution);
		this.scheme=scheme;
		this.dim=scheme.getDim();
		p_start=prossimoPuntoDiScelta(new Point(0,-1));
		p_final=precedentePuntoDiScelta(new Point(dim-1,dim));
		
		solution=new LinkedList<Scheme>();
		
	}
	@Override
	protected Point primoPuntoDiScelta() {
		return p_start;
	}

	@Override
	protected Point prossimoPuntoDiScelta(Point ps) {
		Point tmp=new Point(ps);
		tmp.setLocation(ps);
		if ( tmp.y == dim-1)
			tmp.setLocation(ps.x+1, 0);
		else
			tmp.setLocation(ps.x, ps.y+1);
		if(!scheme.getModificabile(tmp.x, tmp.y))
			return prossimoPuntoDiScelta(tmp);
		return tmp;
			
	}

	@Override
	protected Point ultimoPuntoDiScelta() {
		return p_final;
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
		return dim;
	}

	@Override
	protected boolean assegnabile(Integer scelta, Point puntoDiScelta) {
		return scheme.check(scelta, puntoDiScelta.x, puntoDiScelta.y) &&
				scheme.checkConstraint(scelta, puntoDiScelta.x, puntoDiScelta.y);
	}
	
	@Override
	protected void assegna(Integer scelta, Point puntoDiScelta) {
		scheme.setValue(scelta, puntoDiScelta.x, puntoDiScelta.y,true);
		
	}

	@Override
	protected void deassegna(Integer scelta, Point puntoDiScelta) {
		scheme.setValue(0, puntoDiScelta.x, puntoDiScelta.y,true);
		
	}

	@Override
	protected Point precedentePuntoDiScelta(Point ps) {
		Point tmp=new Point(ps);
		if ( tmp.y == 0)
			tmp.setLocation(ps.x-1, dim-1);
		else
			tmp.setLocation(ps.x, ps.y-1);
		if(!scheme.getModificabile(tmp.x, tmp.y))
			return precedentePuntoDiScelta(tmp);
		return tmp;
	}

	@Override
	protected Integer ultimaSceltaAssegnataA(Point puntoDiScelta) {
		return scheme.getValue(puntoDiScelta.x,puntoDiScelta.y);
	}

	@Override
	protected void scriviSoluzione(int nr_sol) {
		Scheme sol=scheme.getScheme();
		solution.add(sol);
	}
	public List<Scheme> getSoluzioni(){
		return solution;
	}
	@Override
	public void risolvi(){
		if(!scheme.checkSyntax())
			return ;
		super.risolvi();
	}
//	@Override
//	protected void visualizza() {
//		System.out.println(scheme.toString());
//	}
//	
}

	