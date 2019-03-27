package com.sensiblemetrics.api.sqoola.common.validation.validator;

@Service
public class PersonValidator implements Validator {

    @Autowired
    private OtherValidator otherValidator;

    @Autowired
    private Validator validator;

    @Override
    public boolean supports(final Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(final Object obj, final Errors errors) {
        Person p = (Person) obj;
        if (p.getAge() < 0) {
            errors.rejectValue("age", "value.negative");
        }
        otherValidator.validate(obj, errors);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        final Set<ConstraintViolation<Object>> validates = validator.validate(obj);
        for (final ConstraintViolation<Object> constraintViolation : validates) {
            String propertyPath = constraintViolation.getPropertyPath().toString();
            String message = constraintViolation.getMessage();
            errors.rejectValue(propertyPath, "", message);
        }
        final Person p = (Person) obj;
        if (p.getAge() < 0) {
            errors.rejectValue("age", "only.positive.numbers");
        }
    }
}
