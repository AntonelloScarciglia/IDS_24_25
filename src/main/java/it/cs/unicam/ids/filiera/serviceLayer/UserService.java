package it.cs.unicam.ids.filiera.serviceLayer;

import it.cs.unicam.ids.filiera.domainModel.Users.AuthUser;
import it.cs.unicam.ids.filiera.domainModel.Users.User;
import it.cs.unicam.ids.filiera.domainModel.products.Invite;
import it.cs.unicam.ids.filiera.repositories.UserRepository;
import it.cs.unicam.ids.filiera.util.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * @param inviteId
     * @param userId
     */
    public void acceptInvite(Invite invite, Long userId) {
        // TODO - implement UserService.acceptInvite
        throw new UnsupportedOperationException();
    }

    /**
     * @param inviteId
     * @param userId
     */
    public void declineInvite(Long inviteId, Long userId) {
        // TODO - implement UserService.declineInvite
        throw new UnsupportedOperationException();
    }

    /**
     * @param r
     */
    public List<User> filterByRole(Role r) {
        // TODO - implement UserService.filterByRole
        throw new UnsupportedOperationException();
    }

    /**
     * @param userId
     */
    public void getUserPendingInvites(int userId) {
        // TODO - implement UserService.getUserPendingInvites
        throw new UnsupportedOperationException();
    }

    public void auth() {
        // TODO - implement UserService.auth
        throw new UnsupportedOperationException();
    }

    /**
     * @param mail
     * @param psw
     */
    public void Login(int mail, int psw) {
        // TODO - implement UserService.Login
        throw new UnsupportedOperationException();
    }

    /**
     * @param mail
     * @param psw
     */
    public void SingUp(int mail, int psw) {
        // TODO - implement UserService.SingUp
        throw new UnsupportedOperationException();
    }

    /**
     * @param mail
     * @param psw
     * @param CF
     * @param role
     */
    public void SingUBusiness(int mail, int psw, int CF, int role) {
        // TODO - implement UserService.SingUBusiness
        throw new UnsupportedOperationException();
    }

    /**
     * @param mail
     */
    public void checkIfPossible(int mail) {
        // TODO - implement UserService.checkIfPossible
        throw new UnsupportedOperationException();
    }

    /**
     * @param user
     */
    public void register(int user) {
        // TODO - implement UserService.register
        throw new UnsupportedOperationException();
    }

    public void getPendingUser() {
        // TODO - implement UserService.getPendingUser
        throw new UnsupportedOperationException();
    }

    /**
     * @param User
     */
    public void ToStringInfo(int User) {
        // TODO - implement UserService.ToStringInfo
        throw new UnsupportedOperationException();
    }

    /**
     * @param user
     * @param c
     */
    public void saveChoice(int user, boolean c) {
        // TODO - implement UserService.saveChoice
        throw new UnsupportedOperationException();
    }

    /**
     * @param user
     */
    public void acceptUser(int user) {
        // TODO - implement UserService.acceptUser
        throw new UnsupportedOperationException();
    }

    /**
     * @param user
     */
    public void delateUserRequest(int user) {
        // TODO - implement UserService.delateUserRequest
        throw new UnsupportedOperationException();
    }

    public void getAllBusinessUser() {
        // TODO - implement UserService.getAllBusinessUser
        throw new UnsupportedOperationException();
    }

    public AuthUser getUserById(Long userId) {
        Optional<AuthUser> optionalAuthUser = userRepository.findById(userId);
        return optionalAuthUser.orElse(null);
    }
}