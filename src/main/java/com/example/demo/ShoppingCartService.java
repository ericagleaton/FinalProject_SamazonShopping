package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.*;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ShoppingCartService {

    private final ProductRepository productRepository;

    private Map<Product, Integer> products = new HashMap<>();

    @Autowired
    public ShoppingCartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void addProduct(Product product) {
        if (products.containsKey(product)) {
            products.replace(product, products.get(product) + 1);
        } else {
            products.put(product, 1);
        }
    }


      public void removeProduct(Product product) {
        if(products.containsKey(product)){
            if (products.get(product)>1) {
                products.replace(product, products.get(product) - 1);
            } else if (products.get(product) == 1) {
                products.remove(product);
            }
            }
        }


    public Map<Product, Integer> getProductsInCart() {
        return Collections.unmodifiableMap(products);
    }

/*
    public void checkout() throws NotEnoughProductsInStockException {
       com.example.demo.Product product;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            // Refresh quantity for every product before checking
//            product = productRepository.findOne(entry.getKey().getProductId());
            product = productRepository.findById(entry.getKey().getProductId()).get();
            *//*if (product.getQuantity() < entry.getValue())*//*
//                throw new NotEnoughProductsInStockException(product);
            entry.getKey().setQuantity(1);

            productRepository.save(product);
            productRepository.flush();
        }
//       productRepository.saveAndFlush (product);
        products.clear();
    }*/

   /* public void checkout() throws NotEnoughProductsInStockException {
        Product product = productRepository.findById(entry.getKey().getProductId()).get();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            // Refresh quantity for every product before checking
            product = productRepository.findById(entry.getKey().getProductId()).get();
            *//*if (product.getQuantity() < entry.getValue())
                throw new NotEnoughProductsInStockException(product);*//*
            entry.getKey().setQuantity(products.size() );

        }
        productRepository.save();
        productRepository.flush();

        products.clear();
    }*/
   /*public void checkout()  {
       Product product;
       for (Map.Entry<Product, Integer> entry : products.entrySet()) {
           // Refresh quantity for every product before checking
           product = productRepository.findById(entry.getKey().getProductId()).get();

           entry.getKey().setQuantity(1);

//           productRepository.flush();
       }
       productRepository.saveAndFlush(new Product());
       products.clear();
   }
*/
   public void checkout() throws NotEnoughProductsInStockException{
       Product product;
       for(Map.Entry<Product, Integer> entry : products.entrySet()){
           product = productRepository.findById(entry.getKey().getProductId()).get();
           entry.getKey().setQuantity(entry.getValue());
       }
//       Set<Product> myProducts = products.keySet();
       productRepository.saveAll(products.keySet());
       productRepository.flush();
       products.clear();

   }

    public BigDecimal getTotal() {
        return products.entrySet().stream()
                .map(entry -> new BigDecimal(entry.getKey().getPrice()).multiply(BigDecimal.valueOf(entry.getValue())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    // calculates shipping cost
    public BigDecimal getShipping(){
       Double shippingCost =0.0;
       if(getTotal().doubleValue()<50.00) {
           //charging 5% shipping cost
           shippingCost = getTotal().doubleValue() * 0.05;
           //rounding up BigDecimal value
           return new BigDecimal(shippingCost).setScale(2, BigDecimal.ROUND_HALF_EVEN);
       }
       else{
           shippingCost = 0.0;
           return new BigDecimal(shippingCost);
       }
    }

    // calculates grand total after shipping cost
    public BigDecimal getGrandTotal(){
       Double grandTotal;
       grandTotal = getTotal().doubleValue() + getShipping().doubleValue();
        //rounding up BigDecimal value
       return new BigDecimal(grandTotal).setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }


    /*public String calculateCost(){
        double total = 0.0;
        ArrayList<Product> products = new ArrayList<>();
        for (Product prod : products){
            double price = Double.parseDouble(prod.getPrice());
            total += price;
        }
        return Double.toString(total);
    }

    public String productQuantity(){
        return Integer.toString(getProducts().size());
    }

    public String calculateShipping(){
        String costString = calculateCost();
        double cost = (Double.valueOf(costString)).doubleValue();
        double shippingCost = 0.0;
        if(cost < 50){
            shippingCost =  (cost * .05);
            return Double.toString(shippingCost);
        }
        return Double.toString(shippingCost);
    }

    public String costPlusShipping(){
        String shippingString = calculateShipping();
        double shipping = (Double.valueOf(shippingString)).doubleValue();
        String productString = calculateCost();
        double productCost = (Double.valueOf(productString)).doubleValue();

        return Double.toString(shipping + productCost);
    }*/
}


