package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Product;
import cursoSpringBoot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductController {

    // Instancia de clase
    // ProductService productsService = new ProductsServiceImpl();

    //Puedo hacer inyección de dependencia porque he puesto el @Service en el Impl, si no sería obligatoria la anterior instancia
    @Autowired
    //Este es el bean que se va a ejecutar, el de json, no el de list
    //@Qualifier("jsonResourceService")
    private ProductService productsService;

    @GetMapping
    public ResponseEntity<?> getProducts(){
        List<Product> products = productsService.getProducts();
        return ResponseEntity.ok(products);
    }

}
