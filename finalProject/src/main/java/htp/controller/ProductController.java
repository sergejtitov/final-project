package htp.controller;

import htp.dao.ProductRepository;
import htp.domain.model.Product;
import htp.controller.request.ProductFront;
import htp.exceptions.CustomValidationException;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping(value = "/products")
public class ProductController {
    public static final Integer LIMIT = 10;

    private ProductRepository productService;
    private final ConversionService conversionService;


    @GetMapping
    public ResponseEntity<List<Product>> getProducts(@RequestParam Integer offset) {
        return new ResponseEntity<>(productService.findAll(LIMIT,offset), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Product> getUserById(@ApiParam("User Path Id") @PathVariable String id) {
        long productId;
        Product product;
        try {
            productId = Long.parseLong(id);
            product = productService.findById(productId);
        } catch (NumberFormatException e){
            throw new CustomValidationException("Illegal path!");
        }
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductFront request){
        Product product = conversionService.convert(request, Product.class);
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> updateProduct(@PathVariable("id") String productId,
                                           @RequestBody ProductFront request) {
        long productIdLong;
        Product updatedProduct;
        try {
            productIdLong = Long.parseLong(productId);
            updatedProduct = conversionService.convert(request, Product.class);
            Objects.requireNonNull(updatedProduct).setProductId(productIdLong);
        } catch (NumberFormatException e){
            throw new CustomValidationException("Illegal path!");
        }
        return new ResponseEntity<>(productService.update(updatedProduct), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Long> deleteProduct(@PathVariable("id") String productId) {
        long productIdLong;
        try {
            productIdLong = Long.parseLong(productId);
            productService.delete(productIdLong);
        }catch (NumberFormatException e){
            throw new CustomValidationException("Illegal path!");
        }
        return new ResponseEntity<>(productIdLong, HttpStatus.OK);
    }

}
