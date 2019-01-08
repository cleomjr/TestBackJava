package com.santandertecnologia.testbackjava.service;

import com.santandertecnologia.testbackjava.resource.Expense;
import com.santandertecnologia.testbackjava.resource.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ExpenseService {
    private ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public Expense createExpense(Expense expense) {
        // Usually the method would sanitize the data before persisting
        //
        // Expense expense = new Expense();
        // expense.setCategory(sanitize(receivedExpense.getCategory()));
        // ...
        return expenseRepository.save(expense);
    }

    public Iterable<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Iterable<Expense> getExpensesByCategory(String category) {
        return expenseRepository.findByCategoryIgnoreCase(category);
    }

    public Iterable<Expense> getExpensesByDescription(String description) {
        return expenseRepository.findByDescriptionIgnoreCase(description);
    }

    public Iterable<Expense> getExpensesByDate(LocalDateTime localDateTime) {
        return expenseRepository.findByDate(localDateTime);
    }

    public void setExpense(Integer expenseId, Expense newExpenseData) {
        Optional<Expense> expense = expenseRepository.findById(expenseId.toString());

        if(expense.isPresent()) {
            if(newExpenseData.getCategory() != null) {
                expense.get().setCategory(newExpenseData.getCategory());
            }

            if(newExpenseData.getDate() != null) {
                expense.get().setDate(newExpenseData.getDate());
            }

            if(newExpenseData.getDescription() != null) {
                expense.get().setDescription(newExpenseData.getDescription());
            }

            if(newExpenseData.getUserCode() != 0) {
                expense.get().setUserCode(newExpenseData.getUserCode());
            }

            if(newExpenseData.getValue() != 0.0) {
                expense.get().setValue(newExpenseData.getValue());
            }

            expenseRepository.save(expense.get());
        }
    }


}
