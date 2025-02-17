package it.cs.unicam.ids.filiera.domainModel;

public interface Subject {

	/**
	 * Method to attach a new subject to the list of object observed
	 * @param o
	 */
	public void attach(Observer o);

	/**
	 * Method to detach a subject from the list of object observed
	 * @param o
	 */
	public void detach(Observer o);

	/**
	 * Method to notify observers about the updated subject
	 */
	public void notifyObservers();

}