package com.example.kte_labs.controller;

import com.example.kte_labs.dto.product.Product_Pair;
import com.example.kte_labs.dto.response.Product_Add_Information;
import com.example.kte_labs.entity.Client;
import com.example.kte_labs.entity.Estimate;
import com.example.kte_labs.entity.Position;
import com.example.kte_labs.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8080/")
@RequestMapping("/rest/")
public class RestFullController {
    @Autowired
    private MainService service;

    @GetMapping("/getAllClients")
    public List<Client> getAllClients() {
        return service.getAllClients();
    }

    @PutMapping(path="/changeDiscount/{id}",
            consumes={MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Client changeDiscount(@PathVariable Long id, @RequestBody Client client) throws Exception {
        return service.changeDiscount(id, client.getIndividual_discount_1(), client.getIndividual_discount_2());
    }

    @PostMapping(path="/setEstimate")
    public Estimate setEstimate(@RequestParam Long client_id,
                                @RequestParam Long product_id,
                                @RequestParam Integer estimate) throws Exception {
        return service.setEstimate(client_id, product_id, estimate);
    }

    @GetMapping("/getAddProductInfo")
    public Product_Add_Information getAddProductInfo(@RequestParam Long product_id, @RequestParam Long client_id) {
        return service.getAddProductInfo(product_id, client_id);
    }

    @GetMapping(path="/getTotalCost/{client_id}",
            consumes={MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    Double getTotalCost(@PathVariable Long client_id, @RequestBody List<Product_Pair> pairs) throws Exception {
        List<Position> listPositions = service.getTotalCost(client_id, pairs);
        return listPositions.stream()
                .map((p) -> p.getFinalPrice() * p.getAmount())
                .mapToDouble(x-> x.doubleValue())
                .sum();
    }

    @PostMapping(path="/registrationOfSale")
    String registrationOfSale(@RequestParam Long client_id,
                              @RequestBody List<Product_Pair> pairs,
                              @RequestParam Double totalCost) throws Exception {
        return service.registrationOfSale(client_id, pairs, totalCost);
    }
}
