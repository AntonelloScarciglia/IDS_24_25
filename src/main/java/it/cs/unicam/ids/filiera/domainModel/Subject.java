package it.cs.unicam.ids.filiera.domainModel;

public interface Subject {

	/**
	 * 
	 * @param o
	 */
	public void attach(Observer o);

	/**
	 * 
	 * @param o
	 */
	public void detach(Observer o);


	public void notifyObservers();

}