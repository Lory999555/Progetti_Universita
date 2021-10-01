package controller;

import model.Constraint;

public interface Controller {
	
	public void setValue(int value);

	public void setConstraint(Constraint c,int dir);
	
	public void emptyCell();

	public void emptyScheme();

	public void solve();

	public void next();

	public void displayNext();

	public void previous();

	public void displayPrevious();

}
