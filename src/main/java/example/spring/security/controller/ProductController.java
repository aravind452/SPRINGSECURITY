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
            List.of(new Product(1, "Iphone", 34444),
                    new Product(2, "Mac", 78888))
    );

    @GetMapping("/get-products")
    public List<Product> getProducts() {
        return products;
    }


    @PostMapping("/add-product")
    public Product saveProduct(@RequestBody Product product) {
        products.add(product);
        return product;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getToken(HttpServletRequest request) {
        return (CsrfToken) request.getAttribute("_csrf");


    }


//    Step by Step explanation for csrf token

//    Step-by-Step Explanation
//    User Makes First Request (Login or Home Page Request)
//
//    When a user first visits your web application, the backend (Spring Security in this case) responds with a page, which could be a login page or a home page.
//    At this point, the user does not have a CSRF token yet. However, Spring Security automatically generates a CSRF token for the session, typically stored in the HTTP session.
//    Backend Generates and Associates CSRF Token with the Session
//
//    Spring Security generates a unique CSRF token for the user's session, and it stores this token in the session, not yet in the request.
//    The CSRF token is associated with the user's session and is internally stored by Spring Security in the HttpSession object. This is done behind the scenes.
//    Fetching the CSRF Token Using the HttpServletRequest
//
//    When the user later interacts with your application (e.g., makes a request to submit a form or fetches the token), you want to retrieve the CSRF token for that session.
//
//    Since Spring Security stores the CSRF token in the session, it makes the token available in the HttpServletRequest as an attribute (the default attribute name is _csrf).
//
//    Now, when you call request.getAttribute("_csrf") in your Spring controller (like in your example), Spring is retrieving the CSRF token from the session through the HttpServletRequest object.
//
//    How is the CSRF Token Set in the Request?
//
//    Spring Security automatically handles this. Even though you haven't manually set the CSRF token into the request, Spring Security internally makes the CSRF token available in the HttpServletRequest as an attribute.
//    When you access request.getAttribute("_csrf"), you're actually getting the CSRF token that Spring Security has associated with the current user's session.
//    Frontend Fetches CSRF Token
//
//    After the user logs in, the frontend can call an endpoint (like /csrf-token) to fetch the CSRF token.
//    The backend responds with the CSRF token retrieved from the request, and the frontend can use it for subsequent requests (e.g., API calls or form submissions).


}
