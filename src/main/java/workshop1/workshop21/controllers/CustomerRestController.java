package workshop1.workshop21.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import workshop1.workshop21.models.Customer;
import workshop1.workshop21.models.Order;
import workshop1.workshop21.repositories.CustomerRepository;

@RestController
@RequestMapping(path = "/api/customer" , produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerRestController {

    @Autowired
    private CustomerRepository custRepo;
    
    @GetMapping()
    public ResponseEntity<String> getAllCustomer(@RequestParam String limit, 
                                                 @RequestParam String offset){
        List<Customer> custs = custRepo.getAllCustomer(Integer.parseInt(offset), Integer.parseInt(limit));
        
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        for (Customer c : custs)
        arrBuilder.add(c.toJson());
        JsonArray result = arrBuilder.build();
        
        return ResponseEntity.status(HttpStatus.OK)
                              .contentType(MediaType.APPLICATION_JSON)
                              .body(result.toString());
    } 

    @GetMapping( path = "{customerId}")
    public ResponseEntity<String> getCustomerById(@PathVariable Integer customerId) {
        JsonObject result = null;

        try {

            Customer customer = custRepo.findCustomerById(customerId);
            JsonObjectBuilder objBuilder = Json.createObjectBuilder();

            objBuilder.add("Customer", customer.toJson());
            result = objBuilder.build();
            
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body("{\"error_mesg\": \"record not found\"}");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());

    }

    @GetMapping(path = "{customerId}/orders")
    public ResponseEntity<String> getCustomersOrder(@PathVariable Integer customerId) {
        JsonArray result = null;

        try {
            List<Order> orders = custRepo.getCustomerOrder(customerId); JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
            for (Order o : orders)
                arrBuilder.add(o.toJSON());
            result = arrBuilder.build();
        } catch (IndexOutOfBoundsException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body("{\"error_mesg\": \"record not found\"}");
        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result.toString());

    }


}
