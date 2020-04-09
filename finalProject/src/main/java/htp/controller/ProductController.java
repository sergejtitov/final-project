package htp.controller;

import htp.domain.model.Product;
import htp.controller.request.ProductFront;
import htp.exceptions.CustomValidationException;
import htp.services.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;


import javax.validation.Valid;
import java.util.Objects;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping(value = "/products")
public class ProductController {
    public static final Integer LIMIT = 10;

    private ProductService productService;
    private final ConversionService conversionService;

    @ApiOperation(value = "Find all products")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Products successfully got"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @GetMapping
    public ResponseEntity<Page<Product>> getProducts(@RequestParam String offsetString) {
        int offset;
        try {
            offset = Integer.parseInt(offsetString);
        }catch (NumberFormatException e){
            throw new CustomValidationException("Illegal path!");
        }
        return new ResponseEntity<>(productService.findAll(LIMIT,offset), HttpStatus.OK);
    }

    @ApiOperation(value = "Find product by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product successfully got"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
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

    @ApiOperation(value = "Create new product")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product successfully created"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductFront request){
        Product product = conversionService.convert(request, Product.class);
        return new ResponseEntity<>(productService.save(Objects.requireNonNull(product)), HttpStatus.OK);
    }


    @ApiOperation(value = "Update product by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product successfully updated"),
            @ApiResponse(code = 400, message = "Invalid product Id supplied"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "Product not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
    @Transactional(rollbackFor = Exception.class)
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


    @ApiOperation(value = "Delete product by Id")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Product successfully deleted"),
            @ApiResponse(code = 400, message = "Invalid product Id supplied"),
            @ApiResponse(code = 403, message = "Access Denied"),
            @ApiResponse(code = 404, message = "Product not found"),
            @ApiResponse(code = 500, message = "Server error, something wrong")
    })
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
