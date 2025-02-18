package it.cs.unicam.ids.filiera.util;

import it.cs.unicam.ids.filiera.domainModel.Users.Role;
import it.cs.unicam.ids.filiera.domainModel.Users.User;
import it.cs.unicam.ids.filiera.domainModel.products.Content;
import it.cs.unicam.ids.filiera.domainModel.products.Product;
import it.cs.unicam.ids.filiera.repositories.ProductRepository;

import java.util.List;

public final class ValidationUtils {

    private ValidationUtils() {
        // Prevent instantiation of utility class
    }

    public static void checkCreator(User creator) {
        if(creator == null || creator.getId() == null){
            throw new NullPointerException("Creator must not be null");
        }
        if(creator.getRole().equals(Role.ADMIN) || creator.getRole().equals(Role.CURATOR) || creator.getRole().equals(Role.ANIMATOR)){
            throw new IllegalArgumentException("User must be a producer, a transformer or a distributor");
        }
    }

    public static void checkStatus(Status status) {
        if(status == null){
            throw new NullPointerException("Status must not be null");
        }
    }

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

    public static void checkContent(Content c) {
        if(c == null){
            throw new NullPointerException("Content must not be null");
        }
    }

    public static void checkUser(User user){
        if(!(user.getRole().equals(Role.CURATOR))){
            throw new IllegalArgumentException("User must be a curator");
        }
    }
}
