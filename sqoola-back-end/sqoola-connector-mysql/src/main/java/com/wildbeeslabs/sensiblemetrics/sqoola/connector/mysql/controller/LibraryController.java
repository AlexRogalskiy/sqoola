package com.wildbeeslabs.sensiblemetrics.sqoola.connector.mysql.controller;

import com.ubs.network.api.rest.services.artifactory.controller.interfaces.ILibraryController;
import com.ubs.network.api.rest.services.artifactory.model.dto.LibraryDTO;
import com.ubs.network.api.gateway.services.artifactory.model.entities.LibraryEntity;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.sample.core.Customer;
import org.springframework.hateoas.sample.core.Customers;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/library")
@Profile("hateoas")
@ExposesResourceFor(LibraryEntity.class)
@RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class LibraryController<E extends LibraryEntity, D extends LibraryDTO> extends ArtifactoryBaseController<E, D> implements ILibraryController<E, D> {

    @RequestMapping(method = RequestMethod.GET)
    HttpEntity<Resources<Customer>> showCustomers() {

        Resources<Customer> resources = new Resources<>(customers.findAll());
        resources.add(entityLinks.linkToCollectionResource(Customer.class));

        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    HttpEntity<Resource<Customer>> showCustomer(@PathVariable Long id) {

        Resource<Customer> resource = new Resource<>(customers.findOne(id));
        resource.add(entityLinks.linkToSingleResource(Customer.class, id));

        return new ResponseEntity<>(resource, HttpStatus.OK);
    }

    void linkBuilder(Long id) {

        // import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
        resource.add(linkTo(CustomerController.class).slash(id).withSelfRel());
        resource.add(linkTo(methodOn(CustomerController.class).showCustomer(id)).withSelfRel());
    }
}
