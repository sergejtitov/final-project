package htp.controller;

import htp.dao.ProductRepository;
import htp.entities.db_entities.Product;
import htp.entities.front_entities.ProductFront;
import htp.services.ProductService;
import htp.utils.Parsers;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return new ResponseEntity<>(productService.findAll(LIMIT,offset), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getUserById(@ApiParam("User Path Id") @PathVariable String id) {
        Product product = productService.findById(Long.valueOf(id));
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductFront request){
        Product product = Parsers.createProduct(request);
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String productId,
                                           @RequestBody ProductFront request) {
        Product updatedProduct = Parsers.createProduct(request);
        updatedProduct.setProductId(Long.valueOf(productId));
        return new ResponseEntity<>(productService.update(updatedProduct), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteProduct(@PathVariable("id") String productId) {
        productService.delete(Long.valueOf(productId));
        return new ResponseEntity<>(Long.valueOf(productId), HttpStatus.OK);
    }

}
