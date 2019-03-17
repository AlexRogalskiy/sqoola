package com.ubs.network.api.gateway.services.jenkins.controller;

import com.ubs.network.api.rest.services.jenkins.controller.interfaces.IJobController;
import com.ubs.network.api.rest.services.jenkins.model.dto.JobDTO;
import com.ubs.network.api.gateway.services.jenkins.model.entities.JobEntity;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.ArrayList;
import java.util.List;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.sample.core.Customer;
import org.springframework.hateoas.sample.core.Customers;
import org.springframework.hateoas.sample.core.Order;
import org.springframework.hateoas.sample.core.Order.LineItem;
import org.springframework.hateoas.sample.core.Orders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/job")
@Profile("hateoas")
@ExposesResourceFor(JobEntity.class)
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class JobController<E extends JobEntity, D extends JobDTO> extends JenkinsBaseController<E, D> implements IJobController<E, D> {
    /**
     * Exposes a collection resource for {@link Order}s.
     *
     * @return
     */
    @RequestMapping("/orders")
    HttpEntity<Resources<Order>> showOrders() {

        Resources<Order> orders = new Resources<>(this.orders.findAll());
        orders.add(linkTo(methodOn(OrderController.class).showOrders()).withSelfRel());

        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    /**
     * Exposes a single {@link Order} resource.
     *
     * @param id
     * @return
     */
    @RequestMapping("/orders/{id}")
    HttpEntity<Resource<Order>> showOrder(@PathVariable Long id) {

        Resource<Order> order = new Resource<Order>(orders.findOne(id));
        order.add(linkTo(methodOn(OrderController.class).showOrder(id)).withSelfRel());

        return new ResponseEntity<Resource<Order>>(order, HttpStatus.OK);
    }

    /**
     * Exposes all {@link Order}s for {@link Customer}s.
     *
     * @param id
     * @return
     */
    @RequestMapping("/customers/{id}/orders")
    HttpEntity<Resources<OrderResource>> showCustomerOrders(@PathVariable Long id) {
        Customer customer = customers.findOne(id);

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        List<OrderResource> orderResources = new ArrayList<>();
        for (final Order order : orders.findAll(customer)) {
            OrderResource resource = new OrderResource(order.getLineItems());
            resource.add(linkTo(methodOn(OrderController.class).showOrder(order.getId())).withSelfRel());
            resource.add(linkTo(methodOn(CustomerController.class).showCustomer(id)).withRel("customer"));
            orderResources.add(resource);
        }

        Resources<OrderResource> resources = new Resources<>(orderResources);
        resources.add(linkTo(methodOn(OrderController.class).showCustomerOrders(id)).withSelfRel());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Link", linkTo(methodOn(OrderController.class).showCustomerOrders(id)).withSelfRel().toString());

        return new ResponseEntity<>(resources, headers, HttpStatus.OK);
    }

    /**
     * Step 3 - EntityLinks
     */
    @RequestMapping("/customers/{id}/orders")
    HttpEntity<Resources<Order>> showCustomerOrders(@PathVariable Long id) {

        // @ExposesResourceFor(Customer.class)

        // @Autowired
        // EntityLinks links;

        // @EnableHypermediaSupport

        Customer customer = customers.findOne(id);

        if (customer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Resources<Order> resources = new Resources<>(orders.findAll(customer));
        resources.add(linkTo(methodOn(CustomerController.class).showCustomer(id)).withRel("customer"));
        // resources.add(links.linkForSingleResource(Customer.class, id).withRel("customer"));

        return new ResponseEntity<>(resources, HttpStatus.OK);
    }
}
