package facade;

import java.awt.Point;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JOptionPane;

import backtracking.Futoshiki;
import entity.Constraint;
import entity.FactorySchema;
import entity.Scheme;
import graphics.FutoshikiScheme;


public class SchemeController implements Controller{
	private FactorySchema factory;
	private Scheme scheme;
	private int dimension;
	private List<Scheme> solutions;
	private ListIterator<Scheme> iterator;
	private int index;
	private FutoshikiScheme fs;
	private Point notSelected=new Point(-1,-1);
	
	public SchemeController(int dim,FactorySchema f){
		this.dimension=dim;
		this.factory=f;
		this.scheme=factory.create(dimension);
		fs=new FutoshikiScheme(dimension,this,new Point(600, 300));
	}

	@Override
	public void setValue(int value) {
		Point p= fs.getSelectedPoint();
		if(p.equals(notSelected)){
			JOptionPane.showMessageDialog(fs.getFrame(),"Nessuna cella selezionata!","Attention!", JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			if( p.x%2==0 && p.y%2==0){
			    scheme.setValue(value, p.x/2, p.y/2,false);
			    fs.setPointValue(p, value);
			}
		    else JOptionPane.showMessageDialog(fs.getFrame(),"Non puoi farlo su questa cella!","Attention!", JOptionPane.INFORMATION_MESSAGE);
				
		}
	}

	@Override
	public void setConstraint(Constraint c, int dir) {
		Point p=fs.getSelectedPoint();
		if(p.equals(notSelected)){
			JOptionPane.showMessageDialog(fs.getFrame(),"Nessuna cella selezionata!","Attention!", JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			if(dir==0 && p.x%2==0 && p.y%2!=0){
				scheme.setRight(c, p.x/2, p.y/2);
				if (c==Constraint.Lower) fs.setPointString(p, "<" );
				else fs.setPointString(p, ">" );
			}
			else if(dir==1 && p.x%2!=0 && p.y%2==0){
				scheme.setDown(c, p.x/2, p.y/2);
				if (c==Constraint.Lower) fs.setPointString(p, "^" );
				else fs.setPointString(p, "v" );
			}
			else{
				JOptionPane.showMessageDialog(fs.getFrame(),"Non puoi farlo su questa cella!","Attention!", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

	@Override
	public void emptyCell() {
		Point p=fs.getSelectedPoint();
		if(p.equals(notSelected)){
			JOptionPane.showMessageDialog(fs.getFrame(),"Nessuna cella selezionata!","Attention!", JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			if(p.x%2==0 && p.y%2!=0){
				scheme.setRight(Constraint.Null, p.x/2, p.y/2);
			}
			else if(p.x%2!=0 && p.y%2==0){
				scheme.setDown(Constraint.Null, p.x/2, p.y/2);
			}
			else scheme.setValue(0, p.x/2, p.y/2,true);
			fs.setEmpty(p);
		}
	}

	@Override
	public void emptyScheme() {
		fs.getFrame().setTitle("Futoshiki");
		fs.emptyScheme();
		scheme=factory.create(dimension);
		solutions=new LinkedList<>();
		fs.setEnableSolve(true);
		fs.setEnableNumbers(true);
		fs.setEnableNext(false);
		fs.setEnablePrevious(false);
		fs.setEnableSol(true);
	}

	@Override
	public void solve() {
		int numS=0;
		if (!fs.getNumSol().matches("[0-9]+")) {
			JOptionPane.showMessageDialog(null, "Il numero di soluzioni deve essere un numero intero positivo!");
			return;
		}
		try{
		numS = Integer.parseInt(fs.getNumSol());
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Il numero di soluzioni è un valore non valido");
		}
		if (numS <= 0) {
			JOptionPane.showMessageDialog(null, "Il numero di soluzioni deve essere un numero intero positivo!");
			return;
		}
		if(!scheme.checkSyntax()){
			JOptionPane.showMessageDialog(null, "Non sono rispettate le regole del Futoshiki!");
			return;
		}
		Futoshiki futo=new Futoshiki(numS,scheme);
		futo.risolvi();
		solutions=futo.getSoluzioni();
		if(solutions.isEmpty()){
			JOptionPane.showMessageDialog(fs.getFrame(),"Non ci sono soluzioni!","Keep Attention!", JOptionPane.INFORMATION_MESSAGE);
		}
		else{
			String message= "Trovate "+solutions.size()+" soluzioni!";
			JOptionPane.showMessageDialog(fs.getFrame(),message,"Message" , JOptionPane.INFORMATION_MESSAGE);
			fs.setEnableSolve(false);
			fs.setEnableNumbers(false);
			fs.setEnableNext(true);
			fs.setEnablePrevious(false);
			fs.setEnableSol(false);
			iterator=solutions.listIterator();
			index=-1;
			displayNext();
		}
	}

	@Override
	public void next() {
		iterator.next();
	}

	@Override
	public void displayNext() {
		int[][]solution = nextSol();
		if(iterator.hasNext() && index != solutions.size()-1){
	    	fs.setEnableNext(true);
		 }
		 else
			fs.setEnableNext(false);
		 if(iterator.hasPrevious() && index != 0){
		    	fs.setEnablePrevious(true);
		 }
		 else
			 fs.setEnablePrevious(false);
		 fs.setScheme(solution);
		 fs.getFrame().setTitle("soluzione del Futoshiki numero "+Integer.toString(index+1));
	}

	@Override
	public void previous() {
		iterator.previous();
	}

	@Override
	public void displayPrevious() {
		int[][]solution = previousSol();
		if(iterator.hasNext() && index != solutions.size()-1){
	    	fs.setEnableNext(true);
		 }
		 else
			fs.setEnableNext(false);
		 if(iterator.hasPrevious() && index != 0){
		    	fs.setEnablePrevious(true);
		 }
		 else
			 fs.setEnablePrevious(false);
		 fs.setScheme(solution);
		 fs.getFrame().setTitle("soluzione del Futoshiki numero "+Integer.toString(index+1));
	}
	
	private int[][] nextSol(){
		index++;
		return iterator.next().getMatrixValues();
	}
	private int[][] previousSol(){
		index--;
		return iterator.previous().getMatrixValues();
	}
	

}
