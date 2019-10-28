package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    public Long countByEmail(String email){
        return userRepository.countByEmail(email);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void saveUser(User user){
        user.setRoles(Arrays.asList(roleRepository.findByRole("USER")));
        user.setEnabled(true);
        userRepository.save(user);
    }

    public void saveAdmin(User user){
        user.setRoles(Arrays.asList(roleRepository.findByRole("ADMIN")));
        user.setEnabled(true);
        userRepository.save(user);
    }

    // returns currently logged in user
    public User getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentusername = authentication.getName();
        User user = userRepository.findByUsername(currentusername);
        return user;
    }

    public Collection<Product> getProduct(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentusername = authentication.getName();
        User user = userRepository.findByUsername(currentusername);
        Collection<Product> products = user.getProducts();
        return products;
    }

   /* public Collection<Order> getUserOrders(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentusername = authentication.getName();
        User user = userRepository.findByUsername(currentusername);
        Collection<Order> tempUserOrders = user.getOrders();

        return tempUserOrders;
    }*/

//    public Collection<Product> getUserOrderProducts() {
//        Collection<Order> tempUserOrders = getUserOrders();
//        Collection<Product> tempOrderProducts = new ArrayList<>();
//        ArrayList<String> tempProductNames = new ArrayList<>();
//        for (Order order : tempUserOrders) {
//
//            for (Product tempProduct : order.getProducts()) {
//                tempOrderProducts.add(tempProduct.getName());
//            }
//        }
//    }


   /* public ArrayList<String> getUserOrderProductsNames() {
        Collection<Order> tempUserOrders = getUserOrders();
        Collection<Product> tempOrderProducts = new ArrayList<>();
        ArrayList<String> tempProductNames = new ArrayList<>();
        for (Order order : tempUserOrders) {

            for (Product tempProduct : order.getProducts()) {
                tempProductNames.add(tempProduct.getName());
            }
        }
        return tempProductNames;
    }
*/
}
