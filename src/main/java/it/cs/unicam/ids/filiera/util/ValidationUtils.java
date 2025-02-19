package it.cs.unicam.ids.filiera.util;

import it.cs.unicam.ids.filiera.domainModel.Users.Role;
import it.cs.unicam.ids.filiera.domainModel.Users.User;
import it.cs.unicam.ids.filiera.domainModel.products.Content;
import it.cs.unicam.ids.filiera.domainModel.products.Phase;
import it.cs.unicam.ids.filiera.domainModel.products.Product;

import java.util.List;

/**
 * Class that represents all the control and check for the project
 */
public final class ValidationUtils {

    private ValidationUtils() {
        // Prevent instantiation of utility class
    }

    /**
     * Method to validate the creator (Must be a Producer, Transformer or Distributor)
     * @param creator
     * @throws NullPointerException if creator is null
     * @throws IllegalArgumentException if creator is not a producer, transformer or Distributor
     */
    public static void checkCreator(User creator) {
        if(creator == null || creator.getId() == null){
            throw new NullPointerException("Creator must not be null");
        }
        if(creator.getRole().equals(Role.ADMIN) || creator.getRole().equals(Role.CURATOR) || creator.getRole().equals(Role.ANIMATOR)){
            throw new IllegalArgumentException("User must be a producer, a transformer or a distributor");
        }
    }

    /**
     * Method to validate the status
     * @param status
     * @throws NullPointerException if status is null
     */
    public static void checkStatus(Status status) {
        if(status == null){
            throw new NullPointerException("Status must not be null");
        }
    }

    /**
     * Method to validate the product for the approvation/rejection
     * @param p
     * @throws IllegalArgumentException if product is null, had a null id or is not in pending state
     */
    public static void checkProductToApproveOrReject(Product p) {
        if(p == null || p.getId() == null || p.getStatus()!= Status.PENDING){
            throw new IllegalArgumentException("Product must not be null, must have a non-null ID and status PENDING");
        }
    }

//    public static void checkProductIfPresent(ProductRepository productRepo, Long id) {
//        if(productRepo.findById(id).isEmpty()) {
//            throw new IllegalArgumentException("Product not found");
//        }
//    }

    /**
     * Method to validate the content of the product
     * @param c
     * @throws NullPointerException if content is null
     */
    public static void checkContent(Content c) {
        if(c == null){
            throw new NullPointerException("Content must not be null");
        }
    }

    /**
     * Method to validate the role of a user (must be a Curator)
     * @param user
     * @throws IllegalArgumentException if user is not a curator
     */
    public static void checkUser(User user){
        if(user == null || !(user.getRole().equals(Role.CURATOR))){
            throw new IllegalArgumentException("User must be a curator");
        }
    }

    /**
     * Method to validate the presence of the phase in a supply chain
     * @param supplyChain
     * @param p
     * @throws IllegalArgumentException if phase does not exist in the supply chain
     */
    public static void checkPhaseIfPresent(List<Phase> supplyChain, Phase p) {
        checkPhase(p);
        if(!(supplyChain.contains(p))){
            throw new IllegalArgumentException("Phase does not exist in supply chain");
        }
    }

    /**
     * Method to validate the absence of the phase in a supply chain
     * @param supplyChain
     * @param p
     * @throws IllegalArgumentException if phase already exists
     */
    public static void checkPhaseToAdd(List<Phase> supplyChain, Phase p) {
        checkPhase(p);
        if(supplyChain.contains(p)){
            throw new IllegalArgumentException("Phase already exists in supply chain");
        }
    }

    /**
     * Method to validate a phase
     * @param p
     * @throws NullPointerException id phase is null
     */
    public static void checkPhase(Phase p) {
        if(p == null){
            throw new NullPointerException("Phase must not be null");
        }
    }
}
