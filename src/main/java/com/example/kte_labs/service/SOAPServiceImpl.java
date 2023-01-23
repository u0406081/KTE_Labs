package com.example.kte_labs.service;

import com.example.kte_labs.dto.product.Product_Id_Name_Price;
import com.example.kte_labs.dto.product.Product_Pair;
import com.example.kte_labs.dto.response.Product_Add_Information;
import com.example.kte_labs.entity.Client;
import com.example.kte_labs.entity.Estimate;
import com.example.kte_labs.entity.Position;
import javax.jws.WebService;
import java.util.List;

@WebService(serviceName = "SoapService", portName = "soapPort", targetNamespace = "http://service.ws.sample/",
        endpointInterface = "com.example.kte_labs.service.SOAPService")
public class SOAPServiceImpl implements SOAPService {
    MainService service;

    public SOAPServiceImpl(MainService service) {
        this.service = service;
    }

    public SOAPServiceImpl() {
    }

    @Override
    public List<Client> getAllClients() {
        return service.getAllClients();
    }

    @Override
    public Client changeDiscount(Long id, Integer discount_1, Integer discount_2) throws Exception {
        return service.changeDiscount(id, discount_1, discount_2);
    }

    @Override
    public List<Product_Id_Name_Price> getAllProducts() {
        return service.getAllProducts();
    }

    @Override
    public Estimate setEstimate(Long client_id, Long product_id, Integer estimate) throws Exception {
        return service.setEstimate(client_id, product_id, estimate);
    }

    @Override
    public Product_Add_Information getAddProductInfo(Long product_id, Long client_id) {
        return service.getAddProductInfo(product_id, client_id);
    }

    @Override
    public Double getTotalCost(Long client_id, List<Product_Pair> pairs) throws Exception {
        List<Position> listPositions = service.getTotalCost(client_id, pairs);
        return listPositions.stream()
                .map((p) -> p.getFinalPrice() * p.getAmount())
                .mapToDouble(x-> x.doubleValue())
                .sum();
    }

    @Override
    public String registrationOfSale(Long client_id, List<Product_Pair> pairs, Double totalCost) throws Exception {
        return service.registrationOfSale(client_id, pairs, totalCost);
     }
}
