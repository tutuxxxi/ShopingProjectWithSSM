package com.linmu.dao;

import com.linmu.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

    int addUser(User user);

    int updateUser(User user);

    int updateEmailById(@Param("uuid") String id,@Param("email") String email);

    int updateUsernameById(@Param("uuid") String id,@Param("username") String username);

    int updatePasswordById(@Param("uuid") String id,@Param("password") String password);

    int deleteUser(@Param("uuid") String id);

    User getUserById(@Param("uuid") String id);

    User getUserByEmail(@Param("email") String email);


}
