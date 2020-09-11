package org.example.springtest.dao.mybatis;
import org.apache.ibatis.annotations.*;
import org.example.springtest.aspect.LoggerMarker;
import org.example.springtest.entities.User;
import java.util.List;


@Mapper
public interface UserMapper {

    @Select("SELECT * FROM USER")
    List<User> findAll();

    @Select("SELECT * FROM USER WHERE id = #{id}")
    User findById(@Param("id") int id);

    @Select("SELECT * FROM USER WHERE name = #{name}")
    List<User> findByName(@Param("name") String name);

    @Select("SELECT * FROM USER WHERE last_name = #{last_name}")

    List<User> findByLastName(@Param("last_name") String lastName);

    @Select("SELECT * FROM USER WHERE name = #{name} and last_name=#{lastName}")
    List<User> findByNameAndLastName(@Param("name") String name,@Param("lastName") String lastName);

    @Select("SELECT * FROM USER WHERE email = #{email}")
    User findByEmail(@Param("email") String email);



    @LoggerMarker
    @Insert("INSERT INTO USER (name, last_name, email, password, role_id)"
            +"VALUES (#{name}, #{lastName}, #{email}, #{password}, #{roleId})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    boolean save(User user);


    @Select("SELECT COUNT(id) FROM USER WHERE id = #{id}")
    boolean existsById(@Param("id") int id);


    @Delete("DELETE FROM USER WHERE id=#{id}")
    boolean deleteById(@Param("id") int id);


    @LoggerMarker
    @Update("UPDATE USER SET password=#{password} WHERE id=#{id}")
    boolean changePassword(@Param("id") int id, @Param("password") String password);


    @LoggerMarker
    @Update("UPDATE USER SET"
            +" name=#{name}, last_name=#{lastName}, role_id=#{roleId}"
            +" WHERE id=#{id}")
    boolean updateUser(User user);
}
