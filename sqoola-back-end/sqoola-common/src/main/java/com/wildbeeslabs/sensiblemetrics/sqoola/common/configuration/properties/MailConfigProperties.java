package com.wildbeeslabs.sensiblemetrics.sqoola.common.configuration.properties;

@ConfigurationProperties(prefix = "myapp.mail")
@Validated
//@EnableConfigurationProperties(MailProperties.class)
public class MailConfigProperties {

    @Email
    private String to;
    @NotBlank
    private String host;
    private int port;
    private String[] cc;
    private List<String> bcc;

    @Valid
    private Credential credential = new Credential();

    //Setter and Getter methods

    public class Credential {
        @NotBlank
        private String userName;
        @Size(max = 15, min = 6)
        private String password;

        //Setter and Getter methods

    }
}
//myapp:
//    mail:
//    to: sunil@example.com
//    host: mail.example.com
//        port: 250
//        cc:
//        - mike@example.com
//      - david@example.com
//    bcc:
//        - sumit@example.com
//      - admin@example.com
//    credential:
//        user-name: sunil1234
//        password: xyz@1234
