package com.santandertecnologia.testbackjava.service;

import com.santandertecnologia.testbackjava.resource.Expense;
import com.santandertecnologia.testbackjava.resource.ExpenseRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ExpenseServiceTest {

    private ExpenseRepository expenseRepository;
    private RedisTemplate<String, Expense> expenseRedisTemplate;
    private ExpenseService expenseService;
    private List<Expense> expenses;
    private String dateTest;
    private Expense expense;

    @Before
    public void init() {
        dateTest = "2019-01-08T12:32:58.596Z";
        expense = new Expense(0L, "Teste gasto", 22.0, 1, dateTest, "Teste");
        expenses = new ArrayList<>();
        expenses.add(expense);

        expenseRepository = Mockito.mock(ExpenseRepository.class);
        expenseRedisTemplate = Mockito.mock(RedisTemplate.class);

        expenseService = new ExpenseService(expenseRepository, expenseRedisTemplate);
        Mockito.when(expenseRepository.findAll()).thenReturn(expenses);
    }

    @Test
    public void expenseService_listCategories_whenGetCategories() {
        Set<String> categories = expenseService.getCategories("Tes");
        Assert.assertTrue(categories.size() > 0);
    }

    @Test
    public void expenseService_listExpenses_whenGetByPeriod() {
        List<Expense> expensesList = expenseService.getExpensesByPeriod(dateTest, dateTest);
        Assert.assertTrue(expensesList.size() > 0);
    }
}
