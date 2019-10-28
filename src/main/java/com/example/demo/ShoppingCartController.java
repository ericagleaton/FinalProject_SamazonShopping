package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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


    private  final SimpleEmailService simpleEmailService;

    private final ShoppingCartService shoppingCartService;

    private final ProductService productService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService, ProductService productService,  SimpleEmailService simpleEmailService ) {
        this.shoppingCartService = shoppingCartService;
        this.productService = productService;
        this.simpleEmailService = simpleEmailService;
    }


    /*@RequestMapping("/addtoCart/{id}")
    public String addItem(@PathVariable("id") long id, Model model){
//        model.addAttribute("product", new Product());
        model.addAttribute("products", productRepository.findById(id).get());
        return "productform";
    }*/
   /* @RequestMapping("/cartlist")
    public String productList(Model model) {
        model.addAttribute("products", productRepository.findAll());
//        model.addAttribute("user",userService.getUser());
        if (userService.getUser() != null) {
            model.addAttribute("user_id", userService.getUser().getId());
        }
        return "cartlist";
    }*/
   /* @RequestMapping("/addtoCart/{id}")
    public String processCartItem(@Valid Product product,@PathVariable("id") long id, BindingResult result, Model model){
        if(result.hasErrors()){
            return "list";
        }
       product.setUser(userService.getUser());
        productRepository.save(product);
//            orderRepository.save(product);



        User user = userService.getUser();
        Collection <Order> orders =user.getOrders();
        if( orders.isEmpty() ) {

            List<Product> products = new ArrayList<>(); // making an order
            products.add(product);

            Order order1 = new Order();
            orderRepository.save(order1);
//            productRepository.save(order1);
            orders.add(order1);
            Product product1=productRepository.findById(id).get();

     //       product1.setOrders(order1);
//            product1.addOrder(order1);
            productRepository.save(product1);

            System.out.println(productRepository.findById(id).get());
            model.addAttribute("product", productRepository.findById(id).get());
            model.addAttribute("products", productRepository.findAll());
            model.addAttribute("myuser", userService.getUser());
            //model.addAttribute("productnames", userService.getUserOrderProductsNames());
//            System.out.println(userService.getUserOrderProductsNames());
            System.out.println( userService.getUser());

        }
        model.addAttribute("product", productRepository.findById(id).get());
        //model.addAttribute("productnames", userService.getUserOrderProductsNames());
//        System.out.println(userService.getUserOrderProductsNames());
        model.addAttribute("myuser", userService.getUser());
        System.out.println( userService.getUser());
        model.addAttribute("products", productRepository.findAll());
        return "cartlist";
    }*/

    /*@RequestMapping("/addtoCart/{id}")
    public String processCartItem(@Valid Product product, @PathVariable("id") long id, BindingResult result, Model model){
        if(result.hasErrors()){
            return "list";
        }

        List<Product> products = new ArrayList<>();
        if (products.isEmpty()){
            Product product1 = productRepository.findById(id).get();
            products.add(product1);
            products.add(product);
            model.addAttribute("products");
            model.addAttribute("product1");
        }
//        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("products");
       // model.addAttribute("product",productRepository.findById(id).get());
        model.addAttribute("product1");
        model.addAttribute("user",userService.getUser());
        product.setUser(userService.getUser());
       *//* if(userService.getUser() != null) {
            model.addAttribute("user_id", userService.getUser().getId());
        }*//*
//

//        productRepository.save(product);

        return "cartlist";
    }*/



    /*//Working code
    @RequestMapping("/addtoCart/{id}")
    public String processCartItem(@PathVariable("id") long id, @Valid Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "list";
        }
        product = productRepository.findById(id).get();
        User user = userService.getUser();
        product.setUser(user);
        Collection<Product> products;
        System.out.println("Cart User: " + user.getUsername());

        Collection<Order> orders = user.getOrders();

        if (orders.isEmpty()) {

            System.out.println("Cart is Empty.");
//            order1 = new Order();

            products = new ArrayList<>(); // making an order
            products.add(product);
            Order order1 = new Order(true);
            product.setOrder(order1);
            order1.setProducts(products);
            System.out.println("Product " + product.getName() + " added to new collection products for user " + product.getUser().getUsername());


            orders.add(order1);
            order1.setUser(user);



            orderRepository.save(order1);
            model.addAttribute("products", order1.getProducts());

        } else {
            Order order1 = orderRepository.findByOrderStatus(true);
//            Order order1 = orderRepository.findByUser(user);
            product.setOrder(order1);
            orderRepository.save(order1);
            model.addAttribute("products", order1.getProducts());
        }

//        model.addAttribute("products", order1.getProducts());
        return "tempCart";
    }
*/

//    @GetMapping("/addtoCart")
    @GetMapping ("/shoppingCart")
    public ModelAndView shoppingCart() {
        ModelAndView modelAndView = new ModelAndView("/cartlist");
        modelAndView.addObject("products", shoppingCartService.getProductsInCart());
        modelAndView.addObject("total", shoppingCartService.getTotal().toString());
        modelAndView.addObject("shippingCost", shoppingCartService.getShipping().toString());
        modelAndView.addObject("grandTotal", shoppingCartService.getGrandTotal().toString());

        return modelAndView;
    }

    @GetMapping("/addtoCart/{productId}")
    public ModelAndView addProductToCart(@PathVariable("productId") Long productId, Model model) {
        productService.findById(productId).ifPresent(shoppingCartService::addProduct);
//        model.addAttribute("products", order1.getProducts());
//        productService.findById(productId).ifPresent(shoppingCartService::addProduct);
        return shoppingCart();
//        return "tempCart";
    }

    @GetMapping("/removeProduct/{productId}")
    public ModelAndView removeProductFromCart(@PathVariable("productId") Long productId) {
//        productService.findById(productId).ifPresent(shoppingCartService::removeProduct);
        productService.findById(productId).ifPresent(shoppingCartService::removeProduct);
        return shoppingCart();
    }



    /*@GetMapping("/shoppingCart/checkout")
    public ModelAndView checkout() {
        try {
            shoppingCartService.checkout();
//            shoppingCartService.getTotal();
        } catch (NotEnoughProductsInStockException e) {
            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
        }
        return shoppingCart();
    }*/

    /*@GetMapping("/checkout")
    public ModelAndView checkout(){
        shoppingCartService.checkout();
        shoppingCartService.getGrandTotal();

        return shoppingCart();
    }*/
    @RequestMapping("/checkout")
    public String checkout(Model model){
//        shoppingCartService.checkout();
        try {
            simpleEmailService.sendEmail();
        }
        catch(Exception e){
            return "Error in sending email: "+e;
        }
        model.addAttribute("total",   shoppingCartService.getTotal().toString());
        model.addAttribute("shippingCost",shoppingCartService.getShipping().toString());
        model.addAttribute("grandTotal", shoppingCartService.getGrandTotal().toString());
        return "checkoutlist";
    }
    /*@GetMapping("checkout")
    public ModelAndView checkout() {
        try {
            shoppingCartService.checkout();
        } catch (NotEnoughProductsInStockException e) {
            return shoppingCart().addObject("outOfStockMessage", e.getMessage());
        }
        return shoppingCart();
    }
*/
}
