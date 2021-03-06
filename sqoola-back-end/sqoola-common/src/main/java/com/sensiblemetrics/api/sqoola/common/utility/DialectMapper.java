package com.sensiblemetrics.api.sqoola.common.utility;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.*;
import org.javers.common.exception.JaversException;
import org.javers.common.exception.JaversExceptionCode;
import org.javers.repository.sql.DialectName;

/**
 * Dialect utilities implementation
 */
@Slf4j
@UtilityClass
public class DialectMapper {

    public DialectName map(final Dialect hibernateDialect) {

        if (hibernateDialect instanceof SQLServerDialect) {
            return DialectName.MSSQL;
        }
        if (hibernateDialect instanceof H2Dialect) {
            return DialectName.H2;
        }
        if (hibernateDialect instanceof Oracle8iDialect) {
            return DialectName.ORACLE;
        }
        if (hibernateDialect instanceof PostgreSQL81Dialect) {
            return DialectName.POSTGRES;
        }
        if (hibernateDialect instanceof MySQLDialect) {
            return DialectName.MYSQL;
        }
        throw new JaversException(JaversExceptionCode.UNSUPPORTED_SQL_DIALECT, hibernateDialect.getClass().getSimpleName());
    }
}
