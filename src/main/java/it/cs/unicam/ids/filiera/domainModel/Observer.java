package it.cs.unicam.ids.filiera.domainModel;

public interface Observer {

	/**
	 * Method that permit Observer to update the subject
	 */
	public void update(Subject subject);
}