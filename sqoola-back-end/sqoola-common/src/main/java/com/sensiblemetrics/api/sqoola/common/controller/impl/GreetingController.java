//package com.sensiblemetrics.api.sqoola.common.controller.impl;
//
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
//import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
//
//@RestController
//public class GreetingController {
//
//    private static final String TEMPLATE = "Hello, %s!";
//
//    @RequestMapping("/greeting")
//    public HttpEntity<Greeting> greeting(
//        @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
//
//        Greeting greeting = new Greeting(String.format(TEMPLATE, name));
//        greeting.add(linkTo(methodOn(GreetingController.class).greeting(name)).withSelfRel());
//
//        return new ResponseEntity<>(greeting, HttpStatus.OK);
//    }

//@ApiOperation(value = "Get Che server info")
//@GetMapping("/server")
//public CheServerInfo getCheServerInfo(@RequestParam(required = false) String masterUrl, @RequestParam(required = false) String namespace,
//@ApiParam(value = "Keycloak token", required = true) @RequestHeader("Authorization") String keycloakToken, HttpServletRequest request) throws Exception {
//    KeycloakTokenValidator.validate(keycloakToken);
//    boolean isClusterFull = clusterCapacityTracker.isClusterFull(keycloakToken);
//    return getCheServerInfo(request, true, isClusterFull);
//    }
//}
