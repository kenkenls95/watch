package application.data.repository.user;

import application.data.model.user.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IRoleRepository extends CrudRepository<Role, Integer> {

    @Query("select r from tbl_role r where r.id = :id")
    Role findRoleById(@Param("id")int id);


}
