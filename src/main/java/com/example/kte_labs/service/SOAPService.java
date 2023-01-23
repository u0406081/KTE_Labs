package com.example.kte_labs.service;

import com.example.kte_labs.dto.product.Product_Id_Name_Price;
import com.example.kte_labs.dto.product.Product_Pair;
import com.example.kte_labs.dto.response.Product_Add_Information;
import com.example.kte_labs.entity.Client;
import com.example.kte_labs.entity.Estimate;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import java.util.List;

@WebService(targetNamespace="http://service.ws.sample/", name="SoapService")
public interface SOAPService {
    @WebResult(name="client")
    @RequestWrapper(localName="getClients", targetNamespace="http://service.ws.getAllClients/")
    @ResponseWrapper(localName="Response", targetNamespace="http://service.ws.getAllClients/")
    @WebMethod
    List<Client> getAllClients();

    @WebResult(name="client")
    @RequestWrapper(localName="changeDiscount", targetNamespace="http://service.ws.changeDiscount/")
    @ResponseWrapper(localName="Response", targetNamespace="http://service.ws.changeDiscount/")
    @WebMethod
    Client changeDiscount(@XmlElement(required=true) @WebParam(name="id") Long id,
                          @XmlElement(required=true) @WebParam(name="discount_1") Integer discount_1,
                          @XmlElement(required=true) @WebParam(name="discount_2") Integer discount_2) throws Exception;

    @WebResult(name="product")
    @RequestWrapper(localName="getAllProducts", targetNamespace="http://service.ws.getAllProducts/")
    @ResponseWrapper(localName="Response", targetNamespace="http://service.ws.getAllProducts/")
    @WebMethod
    List<Product_Id_Name_Price> getAllProducts();

    @WebResult(name="estimate")
    @RequestWrapper(localName="setEstimate", targetNamespace="http://service.ws.setEstimate/")
    @ResponseWrapper(localName="Response", targetNamespace="http://service.ws.setEstimate/")
    @WebMethod
    Estimate setEstimate(@XmlElement(required=true) @WebParam(name="client_id") Long client_id,
                         @XmlElement(required=true) @WebParam(name="product_id") Long product_id,
                         @XmlElement(required=true) @WebParam(name="estimate") Integer estimate) throws Exception;

    @WebResult(name="ProductAddInformation")
    @RequestWrapper(localName="getAddProductInfo", targetNamespace="http://service.ws.getAddProductInfo/")
    @ResponseWrapper(localName="Response", targetNamespace="http://service.ws.getAddProductInfo/")
    @WebMethod
    Product_Add_Information getAddProductInfo(@XmlElement(required=true) @WebParam(name="product_id") Long product_id,
                                              @XmlElement(required=true) @WebParam(name="client_id") Long client_id);

    @WebResult(name="getTotalCost")
    @RequestWrapper(localName="getTotalCost", targetNamespace="http://service.ws.getTotalCost/")
    @ResponseWrapper(localName="Response", targetNamespace="http://service.ws.getTotalCost/")
    @WebMethod
    Double getTotalCost(@XmlElement(required=true) @WebParam(name="client_id") Long client_id,
                        @XmlElement(required=true) @WebParam(name="pair") List<Product_Pair> pairs) throws Exception;

    @WebResult(name="registrationOfSale")
    @RequestWrapper(localName="registrationOfSale", targetNamespace="http://service.ws.registrationOfSale/")
    @ResponseWrapper(localName="Response", targetNamespace="http://service.ws.registrationOfSale/")
    @WebMethod
    String registrationOfSale(@XmlElement(required=true) @WebParam(name="client_id") Long client_id,
                              @XmlElement(required=true) @WebParam(name="pair") List<Product_Pair> pairs,
                              @XmlElement(required=true) @WebParam(name="totalCost") Double totalCost) throws Exception;
}
