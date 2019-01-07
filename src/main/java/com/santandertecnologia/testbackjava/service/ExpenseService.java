package com.santandertecnologia.testbackjava.service;

import com.santandertecnologia.testbackjava.resource.Expense;
import com.santandertecnologia.testbackjava.resource.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ExpenseService {
    private ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void createExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    public Iterable<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }
}
