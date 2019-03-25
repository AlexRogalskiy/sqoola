package com.sensiblemetrics.api.sqoola.common.controller.impl;

@RestController
public class UserController {

    private static List<User> userList = new ArrayList<>();
    static {
        userList.add(new User(1, "John", 24));
        userList.add(new User(2, "Jane", 22));
        userList.add(new User(3, "Max", 27));
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable int id) {
        if (id < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        User user = findUser(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(user);
    }

    private User findUser(int id) {
        return userList.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }
}
