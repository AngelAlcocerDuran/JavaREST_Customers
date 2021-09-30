package mx.edu.utez.controller;

import mx.edu.utez.model.Customer;
import mx.edu.utez.model.DaoCostumer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.ArrayList;
import java.util.List;

//ANGEL YAZVECK ALCOCER DURÁN 4B DSM

@Path("/customer")
public class Service {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getEmployees() {
        return new DaoCostumer().findAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomer(@PathParam("id") int customerNumber) {
        return new DaoCostumer().findByCustomerNumber(customerNumber);
    }

    @POST
    @Path("/save")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Customer save(MultivaluedMap<String, String> formParams) {
        int customerNumber = Integer.parseInt(formParams.get("customerNumber").get(0));

        if (new DaoCostumer().saveCustomer(getParams(customerNumber, formParams), true)){
            return new DaoCostumer().findByCustomerNumber(customerNumber);
        }else{
            return null;
        }
    }

    @POST
    @Path("/save/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("application/x-www-form-urlencoded")
    public Customer save(@PathParam("id") int customerNumber, MultivaluedMap<String, String> formParams) {
        if (new DaoCostumer().saveCustomer(getParams(customerNumber, formParams), false)){
            return new DaoCostumer().findByCustomerNumber(customerNumber);
        }else{
            return null;
        }
    }

    @DELETE
    @Path("/delete/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean deleteCustomer(@PathParam("id") int customerNumber) {
        return new DaoCostumer().deleteCustomer(customerNumber);
    }

    private Customer getParams(int customerNumber, MultivaluedMap<String, String> formParams) {
        String customerName = formParams.get("customerName").get(0);
        String contactLastName = formParams.get("contactLastName").get(0);
        String contactFirstName = formParams.get("contactFirstName").get(0);
        String phone = formParams.get("phone").get(0);
        String addressLine1 = formParams.get("addressLine1").get(0);
        String addressLine2 = formParams.get("addressLine2").get(0);
        String city = formParams.get("city").get(0);
        String state = formParams.get("state").get(0);
        String postalCode = formParams.get("postalCode").get(0);
        String country = formParams.get("country").get(0);
        int salesRepEmployeeNumber = Integer.parseInt(formParams.get("salesRepEmployeeNumber").get(0));
        double creditLimit = Double.parseDouble(formParams.get("creditLimit").get(0));

        Customer c = new Customer(customerName, contactLastName, contactFirstName, phone, addressLine1, addressLine2, city, state, postalCode, country, customerNumber, salesRepEmployeeNumber, creditLimit);
        System.out.println(c);
        return c;
    }
}
