package com.vpos.server.product;

import com.vpos.server.utils.QRCodeGenerate;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Rithy SKUN
 * @created 12/04/2023 - 10:22 PM
 * @project server
 **/

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/api/v1/products")
public class ProductController {

    private final ProductService productService;
    private QRCodeGenerate qrCodeGenerate;
    @Autowired
    public ProductController(ProductService productService, QRCodeGenerate qrCodeGenerate) {
        this.productService = productService;
        this.qrCodeGenerate = qrCodeGenerate;
    }

    @GetMapping
    public ResponseEntity<Collection<Product>> getProducts() {
        return ResponseEntity.ok().body(productService.getProducts());
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product) throws Exception {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/products").toUriString());
        return ResponseEntity.created(uri).body(productService.createProduct(product));
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable("id") @Valid Long id) {
        productService.deleteProduct(id);
        Map<String, Boolean> response = new HashMap<>();

        response.put("The product id " +  id + " has been deleted", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody @Valid Product product) throws Exception {
        return ResponseEntity.ok().body(productService.updateProduct(id, product));
    }

    //    localhost:8080/api/v1/products/qrcode?text=1b54cb64-2dfd-4aff-80d3-6f69f25d9baa
    @GetMapping(path = "/qrcode")
    public ResponseEntity<BufferedImage> getQrcode(HttpServletResponse response, @RequestParam String text) throws Exception {

        BufferedImage image = qrCodeGenerate.generateQRCode(text, 350, 350);
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);
        outputStream.flush();
        outputStream.close();

        return ResponseEntity.ok().body(image);
    }
}
