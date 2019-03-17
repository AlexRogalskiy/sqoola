/*
 * The MIT License
 *
 * Copyright 2017 WildBees Labs.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.sensiblemetrics.api.sqoola.common.model.type;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.CurrencyType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;
import org.hibernate.usertype.ParameterizedType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Currency;
import java.util.Objects;
import java.util.Properties;

/**
 * Monetary REST Application Type
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public class MonetaryType implements CompositeUserType, ParameterizedType {

    private Currency convertTo;

    @Override
    public String[] getPropertyNames() {
        return new String[]{"price", "currency", "amount", "discount"};
    }

    @Override
    public Type[] getPropertyTypes() {
        return new Type[]{BigDecimalType.INSTANCE, CurrencyType.INSTANCE, IntegerType.INSTANCE, BigDecimalType.INSTANCE};
    }

    @Override
    public Object getPropertyValue(Object o, int index) throws HibernateException {
        if (Objects.isNull(o)) {
            return null;
        }
        BaseMonetaryEntity monetary = (BaseMonetaryEntity) o;
        switch (index) {
            case 0:
                return monetary.getPrice();
            case 1:
                return monetary.getCurrency();
            case 2:
                return monetary.getAmount();
            case 3:
                return monetary.getDiscount();
            default:
                throw new HibernateException("Invalid property index=" + index);
        }
    }

    @Override
    public void setPropertyValue(Object o, int index, Object o1) throws HibernateException {
        if (Objects.isNull(o)) {
            return;
        }
        final BaseMonetaryEntity monetary = (BaseMonetaryEntity) o;
        switch (index) {
            case 0:
                monetary.setPrice((BigDecimal) o1);
                break;
            case 1:
                monetary.setCurrency((Currency) o1);
                break;
            case 2:
                monetary.setAmount((Integer) o1);
                break;
            case 3:
                monetary.setDiscount((BigDecimal) o1);
                break;
            default:
                throw new HibernateException("Invalid property index=" + index);
        }
    }

    @Override
    public Class returnedClass() {
        return BaseMonetaryEntity.class;
    }

    @Override
    public boolean equals(Object o, Object o1) throws HibernateException {
        return o.equals(o1);
    }

    @Override
    public int hashCode(Object o) throws HibernateException {
        return o.hashCode();
    }

    @Override
    public Object nullSafeGet(ResultSet rs, String[] names, SessionImplementor sessionImplementor, Object o) throws HibernateException, SQLException {
        assert names.length == 4;
        if (rs.wasNull()) {
            return null;
        }
        final BigDecimal price = (BigDecimal) BigDecimalType.INSTANCE.get(rs, names[0], sessionImplementor);
        final Currency currency = (Currency) CurrencyType.INSTANCE.get(rs, names[1], sessionImplementor);
        final Integer amount = (Integer) IntegerType.INSTANCE.get(rs, names[2], sessionImplementor);
        final BigDecimal discount = (BigDecimal) BigDecimalType.INSTANCE.get(rs, names[3], sessionImplementor);
        return (Objects.isNull(price) && Objects.isNull(currency) && Objects.isNull(amount) && Objects.isNull(discount)) ? null : new BaseMonetaryEntity(price, currency, amount, discount);
    }

    @Override
    public void nullSafeSet(PreparedStatement ps, Object o, int index, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
        if (Objects.isNull(o)) {
            BigDecimalType.INSTANCE.set(ps, null, index, sessionImplementor);
            CurrencyType.INSTANCE.set(ps, null, index + 1, sessionImplementor);
            IntegerType.INSTANCE.set(ps, null, index + 2, sessionImplementor);
            BigDecimalType.INSTANCE.set(ps, null, index + 3, sessionImplementor);
        } else {
            BaseMonetaryEntity monetary = (BaseMonetaryEntity) o;
            BaseMonetaryEntity dbMonetary = BaseMonetaryEntity.convert(monetary, this.convertTo);
            BigDecimalType.INSTANCE.set(ps, dbMonetary.getPrice(), index, sessionImplementor);
            CurrencyType.INSTANCE.set(ps, dbMonetary.getCurrency(), index + 1, sessionImplementor);
            IntegerType.INSTANCE.set(ps, dbMonetary.getAmount(), index + 2, sessionImplementor);
            BigDecimalType.INSTANCE.set(ps, dbMonetary.getDiscount(), index + 3, sessionImplementor);
        }
    }

    @Override
    public Object deepCopy(Object o) throws HibernateException {
        return o;
    }

    @Override
    public boolean isMutable() {
        return false;
    }

    @Override
    public Serializable disassemble(Object o, SessionImplementor sessionImplementor) throws HibernateException {
        return (Serializable) o;
    }

    @Override
    public Object assemble(Serializable cached, SessionImplementor sessionImplementor, Object o) throws HibernateException {
        return cached;
    }

    @Override
    public Object replace(Object o, Object o1, SessionImplementor sessionImplementor, Object o2) throws HibernateException {
        return o;
    }

    @Override
    public void setParameterValues(Properties parameters) {
        this.convertTo = Currency.getInstance(parameters.getProperty("convertTo"));
    }
}
