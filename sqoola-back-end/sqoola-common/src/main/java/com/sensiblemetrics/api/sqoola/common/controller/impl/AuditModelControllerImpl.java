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
package com.sensiblemetrics.api.sqoola.common.controller.impl;

import com.sensiblemetrics.api.sqoola.common.controller.AuditModelController;
import com.sensiblemetrics.api.sqoola.common.model.dao.AuditModelEntity;
import com.sensiblemetrics.api.sqoola.common.model.dto.AuditModelView;
import com.sensiblemetrics.api.sqoola.common.service.AuditModelDaoService;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

/**
 * {@link AuditModelController} implementation
 *
 * @param <E>  type of audit model {@link AuditModelEntity}
 * @param <T>  type of audit view model {@link AuditModelView}
 * @param <ID> type of audit identifier {@link Serializable}
 */
@Slf4j
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@RestController
@RequestMapping
public abstract class AuditModelControllerImpl<E extends AuditModelEntity, T extends AuditModelView, ID extends Serializable> extends BaseControllerImpl<E, T, ID> implements AuditModelController<E, T, ID> {

    @Autowired
    private Javers javers;

    @RequestMapping("/person")
    public String getPersonChanges() {
        final QueryBuilder jqlQuery = QueryBuilder.byClass(Person.class);
        final List<Change> changes = javers.findChanges(jqlQuery.build());
        return javers.getJsonConverter().toJson(changes);
    }

    @RequestMapping("/person/{id}")
    public String getPersonChanges(@PathVariable Integer id, @RequestParam Optional<String> param) {
        final QueryBuilder jqlQuery = QueryBuilder.byInstanceId(id, Person.class);
        jqlQuery = param.isPresent() ? jqlQuery.andProperty(param.get()) : jqlQuery;
        List<Change> changes = javers.findChanges(jqlQuery.build());
        changes.sort((o1, o2) -> -1 * o1.getCommitMetadata().get().getCommitDate().compareTo(o2.getCommitMetadata().get().getCommitDate()));
        final JsonConverter jsonConverter = javers.getJsonConverter();
        return jsonConverter.toJson(changes);
    }

    @RequestMapping("/person/snapshots")
    public String getPersonSnapshots(@RequestParam Optional<String> param) {
        QueryBuilder jqlQuery = QueryBuilder.byClass(Person.class);

        jqlQuery = param.isPresent() ? jqlQuery.andProperty(param.get()) : jqlQuery;

        List<CdoSnapshot> changes = new ArrayList(javers.findSnapshots(jqlQuery.build()));

        changes.sort((o1, o2) -> -1 * o1.getCommitMetadata().getCommitDate().compareTo(o2.getCommitMetadata().getCommitDate()));

        JsonConverter jsonConverter = javers.getJsonConverter();

        return jsonConverter.toJson(changes);
    }

    @RequestMapping("/person/{login}/snapshots")
    public String getPersonSnapshots(@PathVariable String login, @RequestParam Optional<String> param) {
        QueryBuilder jqlQuery = QueryBuilder.byInstanceId(login, Person.class);

        jqlQuery = param.isPresent() ? jqlQuery.andProperty(param.get()) : jqlQuery;

        List<CdoSnapshot> changes = javers.findSnapshots(jqlQuery.build());

        changes.sort((o1, o2) -> -1 * o1.getCommitMetadata().getCommitDate().compareTo(o2.getCommitMetadata().getCommitDate()));

        JsonConverter jsonConverter = javers.getJsonConverter();

        return jsonConverter.toJson(changes);
    }

    @RequestMapping("/hierarchy/{left}/diff/{right}")
    public String getPersonSnapshots(@PathVariable String left, @PathVariable String right) {
        Hierarchy l = hierarchyRepository.findOne(left);
        Hierarchy p = hierarchyRepository.findOne(right);

        final Diff diff = javers.compare(l, p);
//        TODO
//        List<Change> changes = diff.getChanges(input ->
//                (input instanceof NewObject
//                        && input.getAffectedGlobalId().getCdoClass().getClientsClass() != Hierarchy.class));
        JsonConverter jsonConverter = javers.getJsonConverter();
        return jsonConverter.toJson(diff.getChanges());
    }

    protected List<Map<String, Object>> getAllLeadHistory(String leadId) throws ClassNotFoundException {
        final QueryBuilder jqlQuery = QueryBuilder.byInstanceId(leadId, Lead.class);
        List<CdoSnapshot> changes = javers.findSnapshots(jqlQuery.build());
        changes.sort((o1, o2) -> -1 * (int) o1.getVersion() - (int) o2.getVersion());
        return commonUtil.getHistoryMap(changes);
    }

    /**
     * Returns {@link HttpHeaders} response headers by input parameters
     *
     * @param page - initial input {@link Page} instance
     * @return {@link HttpHeaders} response headers
     */
    protected HttpHeaders getHeaders(final Page<?> page) {
        final HttpHeaders headers = new HttpHeaders();
        headers.add(DEFAULT_TOTAL_ELEMENTS_HEADER, Long.toString(page.getTotalElements()));
        headers.add(DEFAULT_EXPIRES_AFTER_HEADER, LocalDate.now().plusDays(DEFAULT_TOKEN_EXPIRE_PERIOD).toString());
        headers.add(DEFAULT_RATE_LIMIT_HEADER, String.valueOf(DEFAULT_RATE_LIMIT));
        //headers.set(AUTHORIZATION, "Basic " + SecurityUtils.encode("", ""));
        return headers;
    }

    /**
     * Returns {@link AuditModelDaoService} service
     *
     * @return {@link AuditModelDaoService} service
     */
    protected abstract AuditModelDaoService<E, ID> getService();
}
