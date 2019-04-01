package com.sensiblemetrics.api.sqoola.common.model.constraint.validator;

import com.sensiblemetrics.api.sqoola.common.model.constraint.annotation.BigDecimalRange;
import com.sensiblemetrics.api.sqoola.common.model.constraint.annotation.ContactInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.ConstraintValidator;

/**
 * {@link BigDecimalRange} constraint validator implementation {@link ConstraintValidator}
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
@Slf4j
public class ContactInfoValidator implements ConstraintValidator<ContactInfo, String> {

    @Value("${contactInfoType}")
    private String expressionType;

    private String pattern;

    @Autowired
    private ContactInfoExpressionRepository expressionRepository;

    @Override
    public void initialize(ContactInfo contactInfo) {
        if (StringUtils.isEmptyOrWhitespace(expressionType)) {
            log.error("Contact info type missing!");
        } else {
            pattern = expressionRepository.findById(expressionType)
                .map(ContactInfoExpression::getPattern).get();
        }
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!StringUtils.isEmptyOrWhitespace(pattern)) {
            return Pattern.matches(pattern, value);
        }
        log.error("Contact info pattern missing!");
        return false;
    }
}
