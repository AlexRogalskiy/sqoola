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
package com.sensiblemetrics.api.sqoola.common.controller.category.impl;

import com.sensiblemetrics.api.sqoola.common.annotation.SwaggerAPI;
import com.sensiblemetrics.api.sqoola.common.controller.category.CategoryController;
import com.sensiblemetrics.api.sqoola.common.controller.impl.BaseModelControllerImpl;
import com.sensiblemetrics.api.sqoola.common.model.dao.CategoryEntity;
import com.sensiblemetrics.api.sqoola.common.search.controller.wrapper.SearchRequest;
import com.sensiblemetrics.api.sqoola.common.exception.BadRequestException;
import com.sensiblemetrics.api.sqoola.common.exception.EmptyContentException;
import com.sensiblemetrics.api.sqoola.common.model.dao.UserEntity;
import com.sensiblemetrics.api.sqoola.common.model.dto.BaseCategoryView;
import com.sensiblemetrics.api.sqoola.common.search.model.iface.SearchableCategory;
import com.sensiblemetrics.api.sqoola.common.service.iface.BaseCategoryDaoService;
import com.sensiblemetrics.api.sqoola.common.utility.MapperUtils;
import io.swagger.annotations.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.sensiblemetrics.api.sqoola.common.utility.MapperUtils.map;

/**
 * {@link CategoryController} controller implementation
 */
@Slf4j
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@RestController(CategoryController.CONTROLLER_ID)
@RequestMapping(value = "/api/category", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@SwaggerAPI
@Api(
    value = "/api/category",
    description = "Endpoint for category search operations",
    consumes = "application/json, application/xml",
    produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
    authorizations = {
        @Authorization(value = "category_store_auth",
            scopes = {
                @AuthorizationScope(scope = "write:documents", description = "modify category documents"),
                @AuthorizationScope(scope = "read:documents", description = "read category documents")
            })
    })
public class CategoryControllerImpl extends BaseModelControllerImpl<CategoryEntity, BaseCategoryView, String> implements CategoryController {

    /**
     * Default {@link BaseCategoryDaoService} instance
     */
    @Autowired
    private BaseCategoryDaoService categoryService;

    @GetMapping("/search")
    @ResponseBody
    @ApiOperation(
        httpMethod = "GET",
        value = "Finds category documents by search query",
        notes = "Returns list of category documents by search query",
        nickname = "search",
        tags = {"fetchByQuery"},
        response = BaseCategoryView.class,
        responseContainer = "List",
        consumes = "application/json, application/xml",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        responseHeaders = {
            @ResponseHeader(name = "X-Expires-After", description = "date in UTC when token expires", response = Date.class),
            @ResponseHeader(name = "X-Total-Elements", description = "total number of results in response", response = Integer.class)
        }
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid search query value"),
        @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<?> search(@ApiParam(value = "Search query query to fetch categories by", allowEmptyValue = true, readOnly = true) @RequestParam(value = "q", required = false) final String query,
                                    @ApiParam(value = "Page number to filter by") @PageableDefault(size = DEFAULT_PAGE_SIZE) final Pageable pageable,
                                    final HttpServletRequest request) {
        log.info("Fetching categories by search query: {}", query);
        final Page<? extends CategoryEntity> categoryPage = getService().findByTitle(query, pageable);
        if (Objects.isNull(categoryPage)) {
            throw new BadRequestException(com.sensiblemetrics.api.sqoola.common.utility.StringUtils.formatMessage(getMessageSource(), "error.bad.request"));
        }
        return ResponseEntity
            .ok()
            .headers(getHeaders(categoryPage))
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(MapperUtils.mapAll(categoryPage.getContent(), BaseCategoryView.class));
    }

    @GetMapping("/autocomplete")
    @ResponseBody
    @ApiOperation(
        httpMethod = "GET",
        value = "Finds category documents by autocomplete search term",
        notes = "Returns list of category documents by autocomplete search query",
        nickname = "autoComplete",
        tags = {"fetchByAutocomplete"},
        position = 1,
        response = BaseCategoryView.class,
        responseContainer = "List",
        consumes = "application/json, application/xml",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        responseHeaders = {
            @ResponseHeader(name = "X-Expires-After", description = "date in UTC when token expires", response = Date.class),
            @ResponseHeader(name = "X-Total-Elements", description = "total number of results in response", response = Integer.class)
        }
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid search term value")
    })
    public ResponseEntity<?> autoComplete(@ApiParam(value = "Search term query to fetch categories by", required = true, readOnly = true) @RequestParam("term") final String searchTerm,
                                          @ApiParam(value = "Page number to filter by") @PageableDefault(size = DEFAULT_PAGE_SIZE) final Pageable pageable) {
        log.info("Fetching categories by autocomplete search term: {}", searchTerm);
        final FacetPage<? extends CategoryEntity> categoryPage = getService().findByAutoCompleteTitleFragment(searchTerm, pageable);
        return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .headers(getHeaders(categoryPage))
            .body(MapperUtils.mapAll(getResultSetByTerm(categoryPage, searchTerm), BaseCategoryView.class));
    }

    @GetMapping("/page")
    @ResponseBody
    @ApiOperation(
        httpMethod = "GET",
        value = "Finds category documents by search term",
        notes = "Returns list of category documents by search term",
        nickname = "find",
        tags = {"fetchByTerm"},
        position = 2,
        response = BaseCategoryView.class,
        responseContainer = "List",
        consumes = "application/json, application/xml",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        responseHeaders = {
            @ResponseHeader(name = "X-Expires-After", description = "date in UTC when token expires", response = Date.class),
            @ResponseHeader(name = "X-Total-Elements", description = "total number of results in response", response = Integer.class)
        }
    )
    @ApiResponses(value = {
        @ApiResponse(code = 405, message = "Invalid input value")
    })
    @SuppressWarnings("unchecked")
    public ResponseEntity<?> find(@ApiParam(value = "Search term query to fetch categories by", required = true, readOnly = true) @RequestParam("term") final String searchTerm,
                                  @ApiParam(value = "Offset number to filter by", required = true, readOnly = true) @RequestParam(value = "offset", defaultValue = DEFAULT_PAGE_OFFSET_VALUE) int offset,
                                  @ApiParam(value = "Limit number to filter by", required = true, readOnly = true) @RequestParam(value = "limit", defaultValue = DEFAULT_PAGE_LIMIT_VALUE) int limit) {
        log.info("Fetching categories by search term: {}, offset: {}, limit: {}", searchTerm, offset, limit);
        final HighlightPage<CategoryEntity> page = (HighlightPage<CategoryEntity>) findBy(SearchableCategory.COLLECTION_ID, searchTerm, offset, limit);
        return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .headers(getHeaders(page))
            .body(page
                .stream()
                .map(document -> getHighLightSearchResult(document, page.getHighlights(document), BaseCategoryView.class))
                .collect(Collectors.toList()));
    }

    @GetMapping("/all")
    @ResponseBody
    @ApiOperation(
        httpMethod = "GET",
        value = "Finds all category documents",
        notes = "Returns list of all category documents",
        nickname = "findAll",
        tags = {"fetchAll"},
        position = 3,
        response = BaseCategoryView.class,
        responseContainer = "List",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ApiResponses(value = {
        @ApiResponse(code = 404, message = "Not found")
    })
    public ResponseEntity<?> findAll() {
        log.info("Fetching all categories");
        try {
            return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(MapperUtils.mapAll(this.getAllItems(), BaseCategoryView.class));
        } catch (EmptyContentException ex) {
            return ResponseEntity
                .noContent()
                .build();
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    @ApiOperation(
        httpMethod = "GET",
        value = "Finds category document by ID",
        notes = "For valid response try integer IDs with positive integer value. Negative or non-integer values will generate API errors",
        nickname = "searchById",
        tags = {"fetchById"},
        position = 4,
        response = BaseCategoryView.class,
        responseContainer = "List",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid category ID value"),
        @ApiResponse(code = 404, message = "Not found"),
        @ApiResponse(code = 405, message = "Validation exception")
    })
    public ResponseEntity<?> search(@ApiParam(value = "CategoryEntity ID to fetch by", required = true, readOnly = true) @PathVariable("id") final String id,
                                    final HttpServletRequest request) {
        log.info("Fetching categories by ID: {}", id);
        return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(map(this.getItem(id), BaseCategoryView.class));
    }

    @GetMapping("/search/{term}/{page}")
    @ResponseBody
    @ApiOperation(
        httpMethod = "GET",
        value = "Finds category documents by search term",
        notes = "Returns list of category documents by search term",
        nickname = "findBySearchTerm",
        tags = {"fetchByTermAnPage"},
        position = 5,
        response = BaseCategoryView.class,
        responseContainer = "List",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid search term value")
    })
    public ResponseEntity<?> findBySearchTerm(@ApiParam(value = "Search term query to fetch categories by", required = true, readOnly = true) @PathVariable("term") final String searchTerm,
                                              @ApiParam(value = "Page number to filter by", allowableValues = "range[1,infinity]", required = true, readOnly = true) @PathVariable("page") int page) {
        log.info("Fetching products by search term: {}, page: {}", searchTerm, page);
        final HighlightPage<? extends CategoryEntity> categoryPage = getService().find(SearchableCategory.COLLECTION_ID, searchTerm, PageRequest.of(page, DEFAULT_PAGE_SIZE));
        if (Objects.isNull(categoryPage)) {
            throw new BadRequestException(com.sensiblemetrics.api.sqoola.common.utility.StringUtils.formatMessage(getMessageSource(), "error.bad.request"));
        }
        return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(MapperUtils.mapAll(categoryPage.getContent(), BaseCategoryView.class));
    }

    @GetMapping("/desc/{desc}/{page}")
    @ResponseBody
    @ApiOperation(
        httpMethod = "GET",
        value = "Finds category documents by description",
        notes = "Returns list of category documents by description query",
        nickname = "findByDescription",
        tags = {"fetchByDesc"},
        position = 6,
        response = BaseCategoryView.class,
        responseContainer = "List",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    @ApiResponses(value = {
        @ApiResponse(code = 400, message = "Invalid description value")
    })
    public ResponseEntity<?> findByDescription(@ApiParam(value = "Search description query to fetch categories by", required = true, readOnly = true) @PathVariable("desc") final String description,
                                               @ApiParam(value = "Page number to filter by", allowableValues = "range[1,infinity]", required = true, readOnly = true) @PathVariable("page") int page) {
        log.info("Fetching categories by description: {}, page: {}", description, page);
        final Page<? extends CategoryEntity> categoryPage = getService().findByDescription(description, PageRequest.of(page, DEFAULT_PAGE_SIZE));
        if (Objects.isNull(categoryPage)) {
            throw new BadRequestException(com.sensiblemetrics.api.sqoola.common.utility.StringUtils.formatMessage(getMessageSource(), "error.bad.request"));
        }
        return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(MapperUtils.mapAll(categoryPage.getContent(), BaseCategoryView.class));
    }

    @PostMapping("/search/title")
    @ResponseBody
    @ApiOperation(
        httpMethod = "POST",
        value = "Finds category documents by title",
        notes = "Returns list of category documents by title",
        nickname = "findByTitles",
        tags = {"fetchByTitles"},
        position = 7,
        response = BaseCategoryView.class,
        responseContainer = "List",
        consumes = "application/json, application/xml",
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
        responseHeaders = {
            @ResponseHeader(name = "X-Expires-After", description = "date in UTC when token expires", response = Date.class),
            @ResponseHeader(name = "X-Total-Elements", description = "total number of results in response", response = Integer.class)
        }
    )
    @ApiResponses(value = {
        @ApiResponse(code = 405, message = "Invalid input value")
    })
    @SuppressWarnings("unchecked")
    public ResponseEntity<?> findByTitles(@ApiParam(value = "Search request to fetch categories by title", required = true, readOnly = true) @Valid @RequestBody final SearchRequest searchRequest,
                                          @ApiParam(value = "Page number to filter by") @PageableDefault(size = DEFAULT_PAGE_SIZE) final Pageable pageable) {
        log.info("Fetching categories by title: {}", StringUtils.join(searchRequest.getKeywords(), "|"));
        final HighlightPage<CategoryEntity> page = (HighlightPage<CategoryEntity>) getService().findByTitleIn(searchRequest.getKeywords(), pageable);
        return ResponseEntity
            .ok()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .headers(getHeaders(page))
            .body(page
                .stream()
                .map(document -> getHighLightSearchResult(document, page.getHighlights(document), BaseCategoryView.class))
                .collect(Collectors.toList()));
    }

    // Handler method to produce JSON response
    @GetMapping(path = "/get/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getJSON() {
        final List<String> list = new ArrayList<>();
        list.add("One");
        list.add("Two");
        list.add("Three");
        return ResponseEntity
            .ok()
            .cacheControl(CacheControl.noCache())
            .body(list);
    }

    /*
     * Get user from session attribute
     */
    @GetMapping("/info")
    public String userInfo(@SessionAttribute("user") UserEntity user) {

        System.out.println("Email: " + user.getEmail());
        System.out.println("First Name: " + user.getFname());

        return "user";
    }

    /*
     * Binding a matrix variable with optional and default value
     */
    @GetMapping("/person/{name}")
    @ResponseBody
    public String handler(@PathVariable("name") String name,
                          @MatrixVariable("dob") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dob,
                          @MatrixVariable(required = false, defaultValue = "91XXXXXXXX") String mobile) {

        return "Path Variables <br>"
            + "name = " + name + "<br>"
            + "<br>Matxrix variable <br> "
            + "dob =" + dob + "<br>"
            + "mobile =" + mobile;
    }

    /**
     * Returns {@link BaseCategoryDaoService} service
     *
     * @return {@link BaseCategoryDaoService} service
     */
    protected BaseCategoryDaoService getService() {
        return this.categoryService;
    }
}
