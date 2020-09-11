package org.example.springtest.dao.mybatis;


import org.apache.ibatis.annotations.*;
import org.example.springtest.entities.UserLogger;
import java.util.List;

@Mapper
public interface  UserLoggerMapper {

    @Select("SELECT * FROM user_logger")
    List<UserLogger> findAll();

    @Insert("INSERT INTO user_logger (changed_user_id, changer_id, changes, date, message)"
            + "VALUES (#{changedUserId}, #{changerId}, #{changes}, #{date}, #{message})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    Boolean save(UserLogger userLogger);


}
