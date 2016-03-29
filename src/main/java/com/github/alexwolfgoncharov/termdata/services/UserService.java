package com.github.alexwolfgoncharov.termdata.services;


import com.github.alexwolfgoncharov.termdata.interfaces.User;

import java.util.List;

public interface UserService {
	 
    User getUser(String login);
    
    void addUser(User user);
    User update(User user);
    List<User> getAll();
    void deleteUser(User user);
 
}
