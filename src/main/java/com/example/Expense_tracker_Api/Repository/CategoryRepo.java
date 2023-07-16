package com.example.Expense_tracker_Api.Repository;

import com.example.Expense_tracker_Api.Model.Category;
import com.example.Expense_tracker_Api.Model.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

public interface CategoryRepo extends JpaRepository<Category,Integer> {

    @Query(value = "update Categories c set c.title = ?1, c.Description = ?2 " +
            "where c.category_id = ?3",nativeQuery = true)
    Category updateTitleAndDescriptionByCategory_id(String title, String Description, int Category_id);

//
//    @Query(value = "select c.user_id, c.category_id, c.title, c.description," +
//            " COALESCE(SUM(t.AMOUNT), 0) TOTAL_EXPENSE" +
//            " from transactions t on right outer join Categories c " +
//            "on c.category_id = t.category_id where c.user_id = ?1 and c.category_id= ?2 " +
//            "group by c.category_id",nativeQuery = true)
//    Category findByUserIdAndCategoryId(int user_id, int category_id);

    @Query(value = "select user_id, category_id, title,description from Categories c " +
            "where c.user_id = ?1 and c.category_id= ?2" ,nativeQuery = true)
    Category findByUserIdAndCategoryId(int user_id, int category_id);

    @Query(value = "select c.user_id, c.category_id, c.title, c.description from Categories c where user_id = ?1",nativeQuery = true)
    List<Category> findAllCategories(int user_id);
}
