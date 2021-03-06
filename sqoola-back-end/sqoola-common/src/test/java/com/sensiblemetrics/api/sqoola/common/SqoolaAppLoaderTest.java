/*
 * The MIT License
 *
 * Copyright 2019 WildBees Labs, Inc.
 *
 * PermissionEntity is hereby granted, free of charge, to any person obtaining a copy
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
package com.sensiblemetrics.api.sqoola.common;

import io.zonky.test.db.postgres.embedded.EmbeddedPostgres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;
import javax.sql.DataSource;
import java.io.IOException;

@SpringBootApplication(scanBasePackages = {"com.sensiblemetrics.api.sqoola.common"})
public class SqoolaAppLoaderTest {

    @Autowired
    private EmbeddedPostgres embeddedPostgres;

    public static void main(String[] args) {
        SpringApplication.run(CommonAppLoader.class, args);
    }

    @Configuration
    public class TestConfiguration {

        @Bean
        public EmbeddedPostgres getEmbededPostgres() throws IOException {
            return EmbeddedPostgres.start();
        }

        @Bean
        public DataSource getDataSource() {
            return embeddedPostgres.getPostgresDatabase();
        }

        @PreDestroy
        public void destroy() throws IOException {
            embeddedPostgres.close();
        }
    }
}
