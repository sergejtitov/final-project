package htp.controller;

import htp.dao.ProductRepository;
import htp.entities.db_entities.Product;
import htp.services.ProductService;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/products")
public class ProductController {
    public static final Integer LIMIT = 10;

    private ProductRepository productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestParam Integer offset) {
        return new ResponseEntity<>(productService.findAll(LIMIT,offset*LIMIT), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getUserById(@ApiParam("User Path Id") @PathVariable String id) {
        Product product = productService.findById(Long.valueOf(id));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }


}
