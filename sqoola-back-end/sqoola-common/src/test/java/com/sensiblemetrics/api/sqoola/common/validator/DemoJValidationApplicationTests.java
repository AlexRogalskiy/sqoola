//package de.pearl.pem.product.fetcher.validator;
//
//import javax.validation.Validator;
///**
// * Test Validation
// */
//public class DemoJValidationApplicationTests {
//
//    // Инициализация Validator
//    private static Validator validator;
//    static {
//        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//        validator = validatorFactory.usingContext().getValidator();
//    }
//
//    @Test
//    public void testValidators() {
//        final Person person = new Person("Иван Петров", -4500);
//
//        Set<ConstraintViolation<Person>> validates = validator.validate(person);
//        Assert.assertTrue(validates.size() > 0);
//        validates.stream().map(v -> v.getMessage())
//                .forEach(System.out::println);
//    }
//
//    @Test
//    public void healthAndProfessionalValidators() {
//        final Person person = new Person("Иван Петров", 45);
//        person.setHealthDocuments(new Documents(Arrays.asList("справка 1", "справка 3")));
//        person.setProfessionalDocuments(new Documents(Arrays.asList("тест 1", "тест 4")));
//
//        // проверка на здоровье
//        Set<ConstraintViolation<Person>> validates = validator.validate(person, Health.class);
//        Assert.assertTrue(validates.size() == 0);
//
//        // и если здоровье Ок, то проф. тест
//        validates = validator.validate(person, Professional.class);
//        Assert.assertTrue(validates.size() == 0);
//
//    }
//}
