package com.sensiblemetrics.api.sqoola.common.validator;


@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoJValidationApplicationTests2 {
    // указываем файл сообщений
    private static final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();

    static {
        messageSource.setBasename("message");
    }

    @Autowired
    private PersonValidator personValidator;

    @Test
    public void testValidators() {
        final Person person = new Person("Иван Петров", -4500);

        final DataBinder dataBinder = new DataBinder(person);
        dataBinder.addValidators(personValidator);
        dataBinder.validate();

        Assert.assertTrue(dataBinder.getBindingResult().hasErrors());

        if (dataBinder.getBindingResult().hasErrors()) {
            dataBinder.getBindingResult().getAllErrors().stream().
                forEach(e -> System.out.println(messageSource
                    .getMessage(e, Locale.getDefault())));
        }
    }
}
