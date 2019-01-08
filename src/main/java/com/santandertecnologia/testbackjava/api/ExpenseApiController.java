package com.santandertecnologia.testbackjava.api;

import com.santandertecnologia.testbackjava.resource.Expense;
import com.santandertecnologia.testbackjava.service.ExpenseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ExpenseApiController {

    public static final String CATEGORY_KEY = "category";
    public static final String DATE_KEY = "date";
    public static final String DESCRIPTION_KEY = "description";
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
            @RequestHeader(value = "Authorization") String authorizationHeader,
            @RequestParam(value = DATE_KEY, required = false) LocalDateTime date,
            @RequestParam(value = DESCRIPTION_KEY, required = false) String description,
            HttpServletRequest request)  {

        if(authorizationHeader == null || !request.isUserInRole(ApiSecurityConfig.ADMIN_ROLE)) {
            throw new ForbiddenException("The provided authentication is invalid");
        }

        List<Expense> expenseList = new ArrayList<>();

        if(request.isUserInRole(ApiSecurityConfig.ADMIN_ROLE)) {
            if(date != null) {
                expenseService.getExpensesByDate(date).forEach(expenseList::add);

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
            @RequestHeader(value = "Authorization") String authorizationHeader,
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
            @RequestHeader(value = "Authorization") String authorizationHeader,
            @RequestBody Expense newExpenseData,
            @PathVariable Integer expenseId,
            HttpServletRequest request) {

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
    public List<String> getCategories (
            @RequestHeader(value = "Authorization") String authorizationHeader,
            @RequestParam(value = CATEGORY_KEY) Optional<String> category,
            HttpServletRequest request)  {

        if(authorizationHeader == null || !request.isUserInRole(ApiSecurityConfig.ADMIN_ROLE)) {
            throw new ForbiddenException("The provided authentication is invalid");
        }

        List<String> categoryList = new ArrayList<>();

        if(category.isPresent()) {
            expenseService.getExpensesByCategory(category.get())
                    .forEach(e -> categoryList.add(e.getCategory()));
        }

        return categoryList;
    }

}
