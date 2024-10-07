package cursoSpringBoot.controllers;

import cursoSpringBoot.domain.Customer;
import jakarta.servlet.Servlet;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController

//para generalizar ruta, se pone aquí y sirve para todos los get, put, etc
@RequestMapping("/sistema/api/v1/clientes")
public class CustomerController {

    private final List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(1, "Alba", "adelrio", "1234a"),
            new Customer(2, "Mikel", "mgarcia", "1234b"),
            new Customer(3, "Unai", "ufernandez", "1234c"),
            new Customer(4, "Martxelo", "marmontes", "1234d")
    ));

    //Esto y GetMapping es lo mismo
    @RequestMapping(method = RequestMethod.GET)
    //@GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        //genera códigos de respuesta de forma exitosa o fallida:
        return ResponseEntity.ok(customers);
    }

    //Esto y GetMapping es lo mismo + parámetro
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    //@GetMapping("/{id}")
    public ResponseEntity<?> getCustomersById(@PathVariable int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return ResponseEntity.ok(customer);
            }
        }
        //en vez de return null controlamos error con esto:
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con ID: " + id);
    }

    @PostMapping
    //la interrogación la ponemos para que podamos poner texto en el mensaje de error porque si no Costumer solo puede ser objeto, no texto
    public ResponseEntity<?> addCustomer(@RequestBody Customer customer) {
        customers.add(customer);
        //return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado correctamente: " + customer.getName());

        //buenas prácticas para generar también y devolver la URL
        URI localizacion = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(customer.getId())
                .toUri();
        //return ResponseEntity.created(localizacion).build();
        return ResponseEntity.created(localizacion).body(customer);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer){
        for(Customer c : customers){
            if(c.getId() == customer.getId()){
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());

                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomerById(@PathVariable int id){
        for(Customer c : customers){
            if(c.getId() == id){
                customers.remove(c);
                return ResponseEntity.noContent().build();
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping
    public ResponseEntity<?> patchCustomer(@RequestBody Customer customer){
        for(Customer c : customers){
            if(c.getId() == customer.getId()){
                if(customer.getName() != null){
                    c.setName(customer.getName());
                }

                if(customer.getUsername() != null){
                    c.setUsername(customer.getUsername());
                }

                if(customer.getPassword() != null){
                    c.setPassword(customer.getPassword());
                }

                return ResponseEntity.ok("Cliente modificado correctamente: " + customer.getName());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con ID: " + customer.getId());
    }
}
