package it.cs.unicam.ids.filiera;

import it.cs.unicam.ids.filiera.domainModel.products.CatalogItem;

public interface UI {

	/**
	 * 
	 * @param userId
	 */
	void getUserPendingInvites(int userId);

	/**
	 * 
	 * @param event
	 */
	void getEventInfo(int event);

	/**
	 * 
	 * @param invite
	 * @param coiche
	 */
	void responsdeToInvite(int invite, int coiche);

	void apportamodifiche();

	/**
	 * 
	 * @param CatalogItem
	 */
	void selectItemToChange(int CatalogItem);

	void modifyInfo();

	void autenticarsi();

	/**
	 * 
	 * @param mail
	 * @param psw
	 */
	void Login(int mail, int psw);

	/**
	 * 
	 * @param mail
	 * @param psw
	 */
	void SingUp(int mail, int psw);

	/**
	 * 
	 * @param mail
	 * @param psw
	 * @param CF
	 * @param role
	 */
	void SingUBusiness(int mail, int psw, int CF, int role);

	void menageRole();

	/**
	 * 
	 * @param user
	 */
	void selectUserInfo(int user);

	/**
	 * 
	 * @param user
	 * @param c
	 */
	void choice(int user, boolean c);

	/**
	 * 
	 * @param c
	 */
	void addToCart(CatalogItem c);

	void getEvents();

	/**
	 * 
	 * @param event
	 */
	void selecEvent(int event);

	/**
	 * 
	 * @param event
	 * @param user
	 */
	void reserveEvent(int event, int user);

	void EntryMarkeplace();

	void createSession();

	/**
	 * 
	 * @param c
	 */
	void selectItemInfo(CatalogItem c);

	void returnToHome();

	/**
	 * 
	 * @param data
	 */
	void createProduct(int data);

	/**
	 * 
	 * @param data
	 */
	void addSupplyChainToProduct(int data);

	void initBundle();

	/**
	 * 
	 * @param product
	 */
	void addProduct(int product);

	void endBundle();

	/**
	 * 
	 * @param choice
	 * @param c
	 */
	void review(int choice, CatalogItem c);

	/**
	 * 
	 * @param c
	 */
	void selectItemToReview(CatalogItem c);

	/**
	 * 
	 * @param c
	 */
	void getInfoProduct(CatalogItem c);

	void initReview();

	/**
	 * 
	 * @param product
	 */
	void removeProduct(int product);

	void menageProduct();

	void createEvent();

	void getAllBusinessUsers();

	void inviteUser();

	void getAllBusinessUser();

	/**
	 * 
	 * @param event
	 */
	void selectInviteEvent(int event);

	/**
	 * 
	 * @param user
	 */
	void selectUserToAddInvite(int user);

	void acceptInvitation();

}