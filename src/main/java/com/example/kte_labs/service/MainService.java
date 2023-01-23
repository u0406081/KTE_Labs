package com.example.kte_labs.service;

import com.example.kte_labs.component.Receipt;
import com.example.kte_labs.dto.discount.Discount_Discount_ProductId;
import com.example.kte_labs.dto.estimate.Estimate_Average;
import com.example.kte_labs.dto.pair.EstimatesCount;
import com.example.kte_labs.dto.product.Product_Id_Name_Price;
import com.example.kte_labs.dto.product.Product_Pair;
import com.example.kte_labs.dto.response.Product_Add_Information;
import com.example.kte_labs.entity.*;
import com.example.kte_labs.exception.CalculatedTotalCostDoesNotEqualsPassed;
import com.example.kte_labs.exception.ClientNotFound;
import com.example.kte_labs.exception.ProductNotFound;
import com.example.kte_labs.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class MainService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    EstimateRepository estimateRepository;
    @Autowired
    FactOfSaleRepository factOfSaleRepository;
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    DiscountRepository discountRepository;
    @Autowired
    Receipt receipt;


    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client changeDiscount(Long id, Integer discount_1, Integer discount_2) throws Exception {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFound(id));
        client.setIndividual_discount_1(discount_1);
        client.setIndividual_discount_2(discount_2);
        clientRepository.save(client);
        return client;
    }

    public List<Product_Id_Name_Price> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Transactional
    public Estimate setEstimate(Long client_id, Long product_id, Integer estimate) throws Exception {
        Client client = clientRepository.getClientIdById(client_id)
                .orElseThrow(() -> new ClientNotFound(client_id));

        Product product = productRepository.getProductIdById(product_id)
                .orElseThrow(() -> new ProductNotFound(product_id));

        Estimate result;
        if (isNull(estimate) || estimate == 0) {
            result = estimateRepository.getEstimateIdByClientIdAndProductId(client_id,product_id);
            if (nonNull(result)) {
                estimateRepository.delete(result);
                return null;
            }
        }

        result = new Estimate();
        result.setClient(client);
        result.setProduct(product);
        result.setEstimate(estimate);
        estimateRepository.save(result);
        return result;
    }

    public Product_Add_Information getAddProductInfo(Long product_id, Long client_id) {
        Product_Add_Information result = factOfSaleRepository.getProductAddInfo(client_id, product_id);
        if (nonNull(result)) {
            Estimate_Average estimateAverage = estimateRepository.getAverageByProduct(product_id);
            List<EstimatesCount> countEstimates = estimateRepository.getCountEstimatesByProduct(product_id);
            result.setAverageEstimate(String.format("%.1f", estimateAverage.getAverage()));
            result.setEstimatesCount(countEstimates);
        }
        return result;
    }

    public List<Position> getTotalCost(Long client_id, List<Product_Pair> pairs) throws Exception {
        Client client = clientRepository.findById(client_id).orElseThrow(() -> new ClientNotFound(client_id));
        Discount_Discount_ProductId currentDiscount = discountRepository.getCurrentDiscount();
        List<Position> result = new ArrayList<>(pairs.size());

        for (Product_Pair pair : pairs) {
            Product product = productRepository.getProductPriceById(pair.getId());
            Integer finalDiscount = 0;
            if (pair.getHowMany() > 4) {
                if (discountTwoNotZero(client)) {
                    finalDiscount = client.getIndividual_discount_2();
                }
            } else {
                if (discountTwoNotZero(client)) {
                    finalDiscount = client.getIndividual_discount_2();
                } else {
                    finalDiscount = client.getIndividual_discount_1();
                }
            }
            if (nonNull(currentDiscount) && currentDiscount.getProductId().equals(product.getId())) {
                finalDiscount = finalDiscount + currentDiscount.getDiscount();
            }
            if (finalDiscount > 18) {
                finalDiscount = 18;
            }

            double finalPrice = product.getPrice() - (product.getPrice() * finalDiscount / 100);

            Position position = new Position();
            position.setProduct(product);
            position.setAmount(pair.getHowMany());
            position.setOriginalPrice(product.getPrice());
            position.setFinalPrice(finalPrice);
            position.setFinalDiscount(finalDiscount);
            result.add(position);
        }
        return result;
    }

    private boolean discountTwoNotZero(Client client) {
        if (nonNull(client.getIndividual_discount_2()) && !client.getIndividual_discount_2().equals(0)) {
            return true;
        }
        return false;
    }

    @Transactional
    public String registrationOfSale(Long client_id, List<Product_Pair> pairs, Double totalCost) throws Exception {
        List<Position> listPositions = getTotalCost(client_id, pairs);
        Double calculatedTotalCost = listPositions.stream()
                .map((p) -> p.getFinalPrice() * p.getAmount())
                .mapToDouble(x-> x.doubleValue())
                .sum();
        if (!calculatedTotalCost.equals(totalCost)) {
            throw new CalculatedTotalCostDoesNotEqualsPassed(calculatedTotalCost, totalCost);
        }

        String receiptNumber = String.format("%05d", receipt.getReceiptNumber().incrementAndGet());

        FactOfSale factOfSale = new FactOfSale();
        factOfSale.setClient(new Client(client_id));
        factOfSale.setDateOfSale(new Date(System.currentTimeMillis()));
        factOfSale.setPrice(totalCost);
        factOfSale.setReceiptNumber(receiptNumber);
        factOfSale.setPositions(listPositions);
        factOfSaleRepository.save(factOfSale);

        listPositions.stream()
                .map((p)-> {p.setFactOfSale(factOfSale); return p;})
                .forEach((p)->positionRepository.save(p));
        return receiptNumber;
    }
}
