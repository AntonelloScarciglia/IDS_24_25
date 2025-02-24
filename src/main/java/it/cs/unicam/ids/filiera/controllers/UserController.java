package it.cs.unicam.ids.filiera.controllers;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import it.cs.unicam.ids.filiera.serviceLayer.UserService;
import it.cs.unicam.ids.filiera.util.Role;

import java.util.List;

public class UserController {

	private UserService userService;

	public void getAllBusinessUsers() {
		// TODO - implement UserController.getAllBusinessUsers
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param r
	 */
	public List<AuthUser> getAllUsersByRole(Role r) {
		// TODO - implement UserController.getAllUsersByRole
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param userId
	 */
	public void getUserPendingInvites(int userId) {
		// TODO - implement UserController.getUserPendingInvites
		throw new UnsupportedOperationException();
	}

	public void autenticarsi() {
		// TODO - implement UserController.autenticarsi
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mail
	 * @param psw
	 */
	public void Login(int mail, int psw) {
		// TODO - implement UserController.Login
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mail
	 * @param psw
	 */
	public void SingUp(int mail, int psw) {
		// TODO - implement UserController.SingUp
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param mail
	 * @param psw
	 * @param CF
	 * @param role
	 */
	public void SingUBusiness(int mail, int psw, int CF, int role) {
		// TODO - implement UserController.SingUBusiness
		throw new UnsupportedOperationException();
	}

	public void getPendingUser() {
		// TODO - implement UserController.getPendingUser
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param user
	 */
	public void selectUserInfo(int user) {
		// TODO - implement UserController.selectUserInfo
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param user
	 * @param c
	 */
	public void choice(int user, boolean c) {
		// TODO - implement UserController.choice
		throw new UnsupportedOperationException();
	}

}