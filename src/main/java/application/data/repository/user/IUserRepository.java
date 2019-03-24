package application.data.repository.user;

import application.data.model.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface IUserRepository extends CrudRepository<User, Integer> {

    @Transactional(readOnly = true)
    @Query("select u from tbl_user u where u.email = :email")
    Iterable<User> findByEmail(@Param("email") String email);

    @Transactional(readOnly = true)
    @Query("select u from tbl_user u where u.username = :username")
    Iterable<User> findByUserName(@Param("username") String username);

    @Query("select u from tbl_user u where u.id = :id")
    User findUserByUserId(@Param("id") String id);

    @Query("select u from tbl_user u where u.username = :username")
    User findUserByUsername(@Param("username") String username);

}

