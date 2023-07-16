package com.example.Expense_tracker_Api.Service;

import com.example.Expense_tracker_Api.Exception.AuthException;
import com.example.Expense_tracker_Api.Model.User;
import com.example.Expense_tracker_Api.Repository.userRepo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.patterns.HasThisTypePatternTriedToSneakInSomeGenericOrParameterizedTypePatternMatchingStuffAnywhereVisitor;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
@Slf4j
public class UserService {




    @Autowired
    userRepo userRepo;

    public User validateUser(String email,String password)
    {
       User user = userRepo.findByEmail(email);

        if(!BCrypt.checkpw(password,user.getPassword())  || user ==null)
        {
            throw new AuthException("Invalid user or password");
        }
        else
            return user;
    }

    public User createUser(int id,String first_name,String last_name,String email,String password) throws AuthException
    {
        String Hashpw = BCrypt.hashpw(password,BCrypt.gensalt(10));

        Pattern pattern=Pattern.compile("^(.+)@(.+)$");
        if(!pattern.matcher(email).matches())
            throw new AuthException("Invalied email");
        long count= userRepo.countByEmail(email);
        if (count > 0)
            throw new AuthException("Email already exists");
        User user= new User(id,first_name,last_name,email,Hashpw);
        userRepo.save(user);

        return userRepo.findByUser_id(id);
    }
}
