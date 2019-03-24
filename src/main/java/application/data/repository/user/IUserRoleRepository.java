package application.data.repository.user;

import application.data.model.user.UserRole;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;


public interface IUserRoleRepository extends CrudRepository<UserRole, Integer> {

    @Transactional(readOnly = true)
    @Query("select u from tbl_userrole u where u.userId = :id")
    Iterable<UserRole> findRolesOfUser(@Param("id") Integer userId);

    @Query("select u from tbl_userrole u")
    ArrayList<UserRole> getUserRole();
}
