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

import com.wildbeeslabs.api.rest.common.model.BaseUIDEntity;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.type.Type;
import org.hibernate.type.UUIDCharType;
import org.hibernate.usertype.CompositeUserType;

/**
 *
 * UID Identity REST Application Type
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public class UIDType implements CompositeUserType {

    @Override
    public String[] getPropertyNames() {
        return new String[]{"uuId"};
    }

    @Override
    public Type[] getPropertyTypes() {
        return new Type[]{UUIDCharType.INSTANCE};//UUIDBinaryType//PostgresUUIDType
    }

    @Override
    public Object getPropertyValue(Object o, int index) throws HibernateException {
        if (Objects.isNull(o)) {
            return null;
        }
        BaseUIDEntity identity = (BaseUIDEntity) o;
        switch (index) {
            case 0:
                return identity.getUuId();
            default:
                throw new HibernateException("Invalid property index=" + index);
        }
    }

    @Override
    public void setPropertyValue(Object o, int index, Object o1) throws HibernateException {
        if (Objects.isNull(o)) {
            return;
        }
        BaseUIDEntity identity = (BaseUIDEntity) o;
        UUID value = (UUID) o1;
        switch (index) {
            case 0:
                identity.setUuId(value);
                break;
            default:
                throw new HibernateException("Invalid property index=" + index);
        }
    }

    @Override
    public Class<?> returnedClass() {
        return BaseUIDEntity.class;
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
        assert names.length == 1;
        if (rs.wasNull()) {
            return null;
        }
        UUID uuId = (UUID) UUIDCharType.INSTANCE.get(rs, names[0], sessionImplementor);
        return Objects.isNull(uuId) ? null : new BaseUIDEntity(uuId);
    }

    @Override
    public void nullSafeSet(PreparedStatement ps, Object o, int index, SessionImplementor sessionImplementor) throws HibernateException, SQLException {
        if (Objects.isNull(o)) {
            UUIDCharType.INSTANCE.set(ps, null, index, sessionImplementor);
        } else {
            BaseUIDEntity uuId = (BaseUIDEntity) o;
            UUIDCharType.INSTANCE.set(ps, uuId.getUuId(), index, sessionImplementor);
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
