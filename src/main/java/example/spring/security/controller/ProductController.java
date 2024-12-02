package example.spring.security.controller;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {


    /*
    Records implicitly define private, final fields for these components
    and provide public accessor methods for them (e.g., productId()).
    */

    private record Product(Integer productId, String prodName, double price) {
    }

    List<Product> products = new ArrayList<>(
            List.of(new Product(1,"Iphone",34444),
                    new Product(2,"Mac",78888))
    );

    @GetMapping("/get-products")
    public List<Product> getProducts(){
        return  products;
    }


    @PostMapping("/add-product")
    public Product saveProduct(@RequestBody Product product){
        products.add(product);
        return product;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");


    }




}
