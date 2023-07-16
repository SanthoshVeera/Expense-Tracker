package com.example.Expense_tracker_Api.Service;

import com.example.Expense_tracker_Api.Model.User;
import com.example.Expense_tracker_Api.Repository.CategoryRepo;
import com.example.Expense_tracker_Api.Repository.userRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Expense_tracker_Api.Model.Category;

import java.util.Optional;


@Service
public class CategoryService {

    Logger logger = LoggerFactory.getLogger(CategoryService.class);
    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    userRepo userRepo;

    public Optional<Category> createNewCategory(User user, int category_id, String title, String descpriction)
    {
       // User user = userRepo.findByUser_id(user_id);
        Category category = new Category(category_id,title,descpriction,user);
        logger.info(category.toString());
        categoryRepo.save(category);
        return categoryRepo.findById(category_id);

    }



}
