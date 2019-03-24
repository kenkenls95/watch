package application.service;

import application.constant.RoleIdConstant;
import application.constant.StatusRegisterUserEnum;
import application.constant.StatusRoleConstant;
import application.data.model.user.Role;
import application.data.model.user.User;
import application.data.model.user.UserRole;
import application.data.repository.user.IRoleRepository;
import application.data.repository.user.IUserRepository;
import application.data.repository.user.IUserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.*;

@Service
public class UserService {

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserRoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    UUID uuid = UUID.randomUUID();
    String uuId = uuid.toString();

    public StatusRegisterUserEnum registerNewUser(User user) {
        try {
            if(findUserByUsername(user.getUsername()) != null) {
                return StatusRegisterUserEnum.Existed_Username;
            }
            if(findUserByEmail(user.getEmail()) != null) {
                return StatusRegisterUserEnum.Existed_Email;
            }
            if(user.getPassword() == null){
                user.setPasswordHashed(uuId);
            }else {
                user.setPasswordHashed(passwordEncoder.encode(user.getPassword()));
            }
            user.setAddress(user.getAddress());
            user.setGender("Male");
            user.setPhone(user.getPhone());
            if(user.getImageUrl() == null){
                user.setImageUrl("/link/1528177697-default-user.png");
            }else{
                user.setImageUrl(user.getImageUrl());
            }
            userRepository.save(user);
            UserRole userRole = new UserRole();
            userRole.setRoleId(RoleIdConstant.Role_User);
            userRole.setUserId(user.getUserId());
            userRole.setStatus(StatusRoleConstant.ActiveStatus);
            userRoleRepository.save(userRole);
            return StatusRegisterUserEnum.Success;
        } catch (Exception ex) {
            logger.info("Exception on registerNewUser: " + ex.getMessage());
            return StatusRegisterUserEnum.Error_OnSystem;
        }
    }


    public User findOne(int id){
        return userRepository.findOne(id);
    }

    public Integer findIdByUsername(String username){
        User user = userRepository.findUserByUsername(username);
        return user.getUserId();
    }

    public boolean saveUserRole(UserRole userRole){
        try {
            userRoleRepository.save(userRole);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean saveUser (User user){
        try {
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public List<Role> getListRole() {
        return StreamSupport
                .stream(roleRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());

    }

    public User findUserByEmail(String email) {
        return StreamSupport
                .stream(userRepository.findByEmail(email).spliterator(), false)
                .findFirst().orElse(null);
    }

    public User findUserByUsername(String username) {
        return StreamSupport
                .stream(userRepository.findByUserName(username).spliterator(), false)
                .findFirst().orElse(null);
    }

    public List<Role> getActiveListRole(Integer userId) {
        List<UserRole> listUserRoles = StreamSupport
                .stream(userRoleRepository.findRolesOfUser(userId).spliterator(), false).filter(
                        userRole -> userRole.getStatus() == StatusRoleConstant.ActiveStatus
                ).collect(Collectors.toList());

        return getListRole().stream().filter(role -> {
            return (listUserRoles.stream().filter(userRole -> userRole.getRoleId() == role.getRoleId()).findFirst().orElse(null) != null);
        }).collect(Collectors.toList());
    }

}

