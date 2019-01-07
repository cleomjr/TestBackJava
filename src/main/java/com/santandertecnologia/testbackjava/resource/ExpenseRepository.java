package com.santandertecnologia.testbackjava.resource;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends CrudRepository<Expense, String> {

    List<Expense> findByCategoryIgnoreCase(String category);
    List<Expense> findByDescriptionIgnoreCase(String description);

}