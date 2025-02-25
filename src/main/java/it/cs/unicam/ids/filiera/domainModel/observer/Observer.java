package it.cs.unicam.ids.filiera.domainModel.observer;

public interface Observer {

	/**
	 * Method that permits Observer to update the subject
	 */
	public void update(Subject subject);
}