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

import com.wildbeeslabs.api.rest.common.model.BaseGeolocationEntity;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.BigDecimalType;
import org.hibernate.type.Type;
import org.hibernate.usertype.CompositeUserType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * GEO location REST Application Type
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public class GeolocationType implements CompositeUserType {

    @Override
    public String[] getPropertyNames() {
        return new String[]{"latitude", "longitude"};
    }

    @Override
    public Type[] getPropertyTypes() {
        return new Type[]{BigDecimalType.INSTANCE, BigDecimalType.INSTANCE};
    }

    @Override
    public Object getPropertyValue(Object o, int index) throws HibernateException {
        if (Objects.isNull(o)) {
            return null;
        }
        BaseGeolocationEntity location = (BaseGeolocationEntity) o;
        switch (index) {
            case 0:
                return location.getLatitude();
            case 1:
                return location.getLongitude();
            default:
                throw new HibernateException("Invalid property index=" + index);
        }
    }

    @Override
    public void setPropertyValue(Object o, int index, Object o1) throws HibernateException {
        if (Objects.isNull(o)) {
            return;
        }
        BaseGeolocationEntity location = (BaseGeolocationEntity) o;
        BigDecimal value = (BigDecimal) o1;
        switch (index) {
            case 0:
                location.setLatitude(value);
                break;
            case 1:
                location.setLongitude(value);
                break;
            default:
                throw new HibernateException("Invalid property index=" + index);
        }
    }

    @Override
    public Class<?> returnedClass() {
        return BaseGeolocationEntity.class;
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
        assert names.length == 2;
        if (rs.wasNull()) {
            return null;
        }
        BigDecimal latitude = (BigDecimal) BigDecimalType.INSTANCE.get(rs, names[0], sessionImplementor);
        BigDecimal longitude = (BigDecimal) BigDecimalType.INSTANCE.get(rs, names[1], sessionImplementor);
        return (Objects.isNull(latitude) && Objects.isNull(longitude)) ? null : new BaseGeolocationEntity(latitude, longitude);
    }

    @Override
    public void nullSafeSet(PreparedStatement ps, Object o, int index, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
        if (Objects.isNull(o)) {
            BigDecimalType.INSTANCE.set(ps, null, index, sessionImplementor);
            BigDecimalType.INSTANCE.set(ps, null, index + 1, sessionImplementor);
        } else {
            BaseGeolocationEntity location = (BaseGeolocationEntity) o;
            BigDecimalType.INSTANCE.set(ps, location.getLatitude(), index, sessionImplementor);
            BigDecimalType.INSTANCE.set(ps, location.getLongitude(), index + 1, sessionImplementor);
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
}
