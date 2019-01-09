package com.santandertecnologia.testbackjava.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.santandertecnologia.testbackjava.resource.Expense;
import com.santandertecnologia.testbackjava.service.ExpenseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class ExpenseApiController {

    public static final String CATEGORY_KEY = "category";
    public static final String FROM_DATE_KEY = "fromDate";
    public static final String TO_DATE_KEY = "toDate";
    public static final String DESCRIPTION_KEY = "description";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String ID_PATH_VAR = "id";
    private ExpenseService expenseService;

    @Autowired
    public ExpenseApiController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @ApiOperation(value = "Get the expenses",
            notes = "Returns a list of expenses. You can filter it by date or description",
            response = Expense[].class)
    @RequestMapping(value = "/expense",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Expense> getExpenses (
            @RequestHeader(value = AUTHORIZATION_HEADER) String authorizationHeader,
            @RequestParam(value = FROM_DATE_KEY, required = false) String fromDate,
            @RequestParam(value = TO_DATE_KEY, required = false) String toDate,
            @RequestParam(value = DESCRIPTION_KEY, required = false) String description,
            HttpServletRequest request)  {

        if(authorizationHeader == null || !request.isUserInRole(ApiSecurityConfig.ADMIN_ROLE)) {
            throw new ForbiddenException("The provided authentication is invalid");
        }

        List<Expense> expenseList = new ArrayList<>();

        if(request.isUserInRole(ApiSecurityConfig.ADMIN_ROLE)) {
            if(fromDate != null && toDate != null) {
                expenseService.getExpensesByPeriod(fromDate, toDate).forEach(expenseList::add);

            } else if(description != null) {
                expenseService.getExpensesByDescription(description).forEach(expenseList::add);

            } else {
                expenseService.getAllExpenses().forEach(expenseList::add);
            }
        }

        return expenseList;
    }

    @ApiOperation(value = "Create a new expense",
            notes = "Returns the new created expense",
            response = Expense[].class)
    @RequestMapping(value = "/expense",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> createExpense(
            @RequestHeader(value = AUTHORIZATION_HEADER) String authorizationHeader,
            @RequestBody Expense newExpenseData,
            HttpServletRequest request) {

        if(authorizationHeader == null || !request.isUserInRole(ApiSecurityConfig.ADMIN_ROLE)) {
            throw new ForbiddenException("Insufficient privileges for creating the expense");
        }

        if(newExpenseData != null) {
            Expense expense = expenseService.createExpense(newExpenseData);
            return new ResponseEntity<>(expense, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Update an expense",
            notes = "Returns a list of expenses. You can filter it by date or description",
            response = Expense[].class)
    @RequestMapping(value = "/expense/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public void setExpense(
            @RequestHeader(value = AUTHORIZATION_HEADER) String authorizationHeader,
            @RequestBody Expense newExpenseData,
            @PathVariable(value= ID_PATH_VAR) Integer expenseId,
            HttpServletRequest request) throws InterruptedException, ExecutionException, JsonProcessingException {

        if(authorizationHeader == null || !request.isUserInRole(ApiSecurityConfig.ADMIN_ROLE)) {
            throw new ForbiddenException("Insufficient privileges for updating the expense");
        }

        if(newExpenseData != null) {
            expenseService.setExpense(expenseId, newExpenseData);
        }

    }

    @RequestMapping(value = "/expense/category",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Set<String> getCategories (
            @RequestHeader(value = AUTHORIZATION_HEADER) String authorizationHeader,
            @RequestParam(value = CATEGORY_KEY, required = false) String category,
            HttpServletRequest request)  {

        if(authorizationHeader == null || !request.isUserInRole(ApiSecurityConfig.ADMIN_ROLE)) {
            throw new ForbiddenException("The provided authentication is invalid");
        }

        Set<String> categoryList = new HashSet<>();

        if(category != null) {
            categoryList = expenseService.getCategories(category);
        }

        return categoryList;
    }

}
