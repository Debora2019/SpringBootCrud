package pt.com.springboot.springcourse.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pt.com.springboot.springcourse.model.user;

@Repository
public interface UserRepository extends JpaRepository <user, Long >{
    @Query(value= "select u from user u where  upper(trim(u.name)) like %?1%")
    List<user> searchByName(String name);
}
