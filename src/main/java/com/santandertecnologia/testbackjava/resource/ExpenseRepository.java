package com.santandertecnologia.testbackjava.resource;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, String> {

    Iterable<Expense> findByCategoryIgnoreCase(String category);
    Iterable<Expense> findByDescriptionIgnoreCase(String description);
    Iterable<Expense> findByDate(LocalDateTime date);

}