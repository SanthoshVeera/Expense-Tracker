package com.example.Expense_tracker_Api.Resources;


import com.example.Expense_tracker_Api.Model.Category;
import com.example.Expense_tracker_Api.Model.CategoryDTO;
import com.example.Expense_tracker_Api.Model.User;
import com.example.Expense_tracker_Api.Repository.CategoryRepo;
import com.example.Expense_tracker_Api.Repository.userRepo;
import com.example.Expense_tracker_Api.Service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/ExpenseTracker")
@RestController
public class CategoryResource {

    Logger logger = LoggerFactory.getLogger(CategoryResource.class);

    @Autowired
    userRepo userRepo;

    @Autowired
    CategoryRepo categoryRepo;

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> getAllCategories(HttpServletRequest request)
    {
        logger.info("entered this method");
        logger.info(request.getAttribute("id") + "  --  ");

        int user_id = (Integer) request.getAttribute("id");
        List<Category> categoryList = categoryRepo.findAllCategories(user_id);
        return  categoryList;
    }

   @PostMapping("/category")
    public Optional<Category> createCategory(HttpServletRequest request, @RequestBody Map<String ,Object> map)
   {
       int category_id =(Integer) map.get("category_id");
       String title = (String) map.get("title");
       String description = (String) map.get("description");

       int user_id=(Integer)request.getAttribute("id");
        User user = userRepo.findByUser_id(user_id);
       logger.info(user_id + "");

       return categoryService.createNewCategory(user,category_id,title,description);
   }


   @GetMapping("/category/{id}")
    public Category getAcategory(HttpServletRequest request,@PathVariable("id") int category_id)
   {
       int user_id=(Integer)request.getAttribute("id");

       return categoryRepo.findByUserIdAndCategoryId(user_id, category_id);
     //  return new ResponseEntity<>(map, HttpStatus.OK);
   }

   @PutMapping("/category/{id}")
    public Category updateCategory(HttpServletRequest request,@PathVariable("id") int category_id,
                                  @RequestBody Map<String ,Object> map)
   {
       int user_id=(Integer)request.getAttribute("id");

       String title = (String) map.get("title");
       String description = (String) map.get("description");

       Category updatedCategory=categoryRepo.updateTitleAndDescriptionByCategory_id(title,description,category_id);

       return updatedCategory;
   }



}
