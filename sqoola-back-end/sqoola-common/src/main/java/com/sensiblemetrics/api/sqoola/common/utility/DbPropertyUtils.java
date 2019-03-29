package com.sensiblemetrics.api.sqoola.common.utility;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Property utilities implementation
 *
 * @author Alex
 * @version 1.0.0
 * @since 2017-08-08
 */
public final class DbPropertyUtils extends Properties {

    /**
     * Default property query
     */
    public static final String DEFAULT_PROPERTY_QUERY = "select config_key, config_value from config_params";

    /**
     * Default constructor with initial {@link DataSource} instance
     *
     * @param dataSource {@link DataSource} instance
     */
    public DbPropertyUtils(final DataSource dataSource) {
        this(dataSource, DEFAULT_PROPERTY_QUERY, "config_key", "config_value");
    }

    /**
     * Default constructor with initial {@link DataSource} instance and query string
     *
     * @param dataSource {@link DataSource} instance
     */
    public DbPropertyUtils(final DataSource dataSource, final String query, final String key, final String value) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        final List<Map<String, Object>> configs = jdbcTemplate.queryForList(query);
        configs.forEach((config) -> setProperty((config.get(key)).toString(), (config.get(value)).toString()));
    }
}
