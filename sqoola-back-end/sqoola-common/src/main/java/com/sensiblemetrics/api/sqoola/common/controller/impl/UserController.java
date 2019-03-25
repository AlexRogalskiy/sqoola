package com.sensiblemetrics.api.sqoola.common.controller.impl;

@RestController
public class UserController {

    private static List<User> userList = new ArrayList<>();
    static {
        userList.add(new User(1, "John", 24));
        userList.add(new User(2, "Jane", 22));
        userList.add(new User(3, "Max", 27));
    }

    @PatchMapping(value = "/user/{id}")
    public ResponseEntity<?> updateAge(@PathVariable int id, @RequestParam int age) {
        if (id < 0) {
            throw new ValidationException("Id cannot be less than 0");
        }
        if (age < 20 || age > 60) {
            throw new ValidationException("Age must be between 20 to 60");
        }
        User user = findUser(id);
        user.setAge(age);

        return ResponseEntity.accepted().body(user);
    }

    private User findUser(int id) {
        return userList.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
    }
}
