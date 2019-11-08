package controllers;

import beans.CustomerOrderFacade;
import beans.Basket;
import entities.CustomerOrder;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.*;
import javax.faces.bean.*;

@ManagedBean(name = "orderController")
@SessionScoped
public class OrderController implements Serializable {

    @EJB
    private CustomerOrderFacade orderFacade;
    @EJB
    private Basket basket;

    private final CustomerOrder order = new CustomerOrder();

    private String firstName;
    private String lastName;
    private String address;
    private String address2;
    private String city;
    private String postcode;
    private String phone;
    private String email;

    public String makeOrder() {
        StringBuilder customer = new StringBuilder();
        customer.append(lastName).append(" ").append(firstName).append(" | ").append(phone).append(" | ").append(city).append(" | ").append(address).append(" | ").append(address2).append(" | ").append(postcode).append(" | ").append(email).append(" |");

        order.setDateCreated(new Date());
        order.setCustomerOrder(basket.getString());
        order.setAmmount((float) basket.getTotal());
        order.setCustomer(customer.toString());

        orderFacade.create(order);

        basket.clear();

        return "success";
    }

    public CustomerOrder getOrder() {
        return order;
    }

    public CustomerOrderFacade getOrderFacade() {
        return orderFacade;
    }

    public Basket getBasket() {
        return basket;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCity() {
        return city;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
