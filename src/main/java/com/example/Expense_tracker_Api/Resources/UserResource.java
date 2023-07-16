package com.example.Expense_tracker_Api.Resources;

import com.example.Expense_tracker_Api.Model.User;
import com.example.Expense_tracker_Api.Repository.userRepo;
import com.example.Expense_tracker_Api.Service.UserService;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/et_users")
public class UserResource {

    @Autowired
    userRepo userRepo;

    @Autowired
    UserService userService;


    @PostMapping("/login")
    public ResponseEntity<Map<String ,String>> userLogin(@RequestBody Map<String ,String> usermap)
    {
        String email = usermap.get("email");
        String password = usermap.get("password");
        User user=userService.validateUser(email,password);
        return new ResponseEntity(generateJWT(user),HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String,String>> registerUser(@RequestBody Map<String,Object> usermap)
    {
        int id=(Integer)usermap.get("id");
        String first_name=(String) usermap.get("first_name");
        String last_name=(String) usermap.get("last_name");
        String email=(String) usermap.get("email");
        String password=(String) usermap.get("password");

        User user=userService.createUser(id,first_name,last_name,email,password);

        return new ResponseEntity<>(generateJWT(user), HttpStatus.OK);

    }


    Map<String,String> generateJWT(User user)
    {
        long timeStamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256,"API_SECRET_KEY")
                .setIssuedAt(new Date(timeStamp))
                .setExpiration(new Date(timeStamp + (2 * 60 * 60 * 1000)))
                .claim("id",user.getUser_id())
                .claim("first_name",user.getFirst_name())
                .claim("last_name",user.getLast_name())
                .claim("email",user.getEmail())
                .compact();

        HashMap<String ,String> map = new HashMap<>();
        map.put("token",token);
        return map;
    }

}
