package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;

@Controller
public class ShoppingCartController {


    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    OrderRepository orderRepository;



    /*@RequestMapping("/addtoCart/{id}")
    public String addItem(@PathVariable("id") long id, Model model){
//        model.addAttribute("product", new Product());
        model.addAttribute("products", productRepository.findById(id).get());
        return "productform";
    }*/

    @RequestMapping("/addtoCart/{id}")
    public String processCartItem(@Valid Product product,@PathVariable("id") long id, BindingResult result, Model model){
        if(result.hasErrors()){
            return "list";
        }
       product.setUser(userService.getUser());
//        orderRepository.save(product);


        User user = userService.getUser();
        Collection<Order> orders =user.getOrders();
        if( orders.isEmpty() ) {

            Collection<Product> products = new ArrayList<>(); // making an order
            products.add(product);

            Order order1 = new Order();
            orderRepository.save(order1);
            orders.add(order1);
            Product product1=productRepository.findById(id).get();
     //       product1.setOrders(order1);
            product1.addOrder(order1);
            productRepository.save(product1);

            System.out.println(productRepository.findById(id).get());
            model.addAttribute("product",productRepository.findById(id).get());
            model.addAttribute("products", productRepository.findAll());
            model.addAttribute("myuser", userService.getUser());
            //model.addAttribute("productnames", userService.getUserOrderProductsNames());
            System.out.println(userService.getUserOrderProductsNames());
            System.out.println( userService.getUser());

        }
        model.addAttribute("product", productRepository.findById(id).get());
        //model.addAttribute("productnames", userService.getUserOrderProductsNames());
        System.out.println(userService.getUserOrderProductsNames());
        model.addAttribute("user", userService.getUser());
        System.out.println( userService.getUser());
        model.addAttribute("products", productRepository.findAll());
        return "cartlist";
    }
}