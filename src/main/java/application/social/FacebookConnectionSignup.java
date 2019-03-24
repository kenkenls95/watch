//package application.social;
//
//import application.constant.RoleIdConstant;
//import application.constant.StatusRoleConstant;
//import application.data.model.user.User;
//import application.data.model.user.UserRole;
//import application.data.repository.user.IUserRepository;
//import application.data.repository.user.IUserRoleRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.social.connect.Connection;
//import org.springframework.social.connect.ConnectionSignUp;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.stream.StreamSupport;
//
//
//@Service
//public class FacebookConnectionSignup implements ConnectionSignUp {
//
//    @Autowired
//    private IUserRepository userRepository;
//
//    @Autowired
//    private IUserRoleRepository userRoleRepository;
//
//    @Override
//    public String execute(Connection<?> connection) {
//        User user = new User();
//        try {
//            if(findUserByUsername(connection.getDisplayName()) != null) {
//                return connection.getDisplayName();
//            }else {
//
//                user.setUsername(connection.getDisplayName());
//                user.setPassword(null);
//                user.setCreatedDate(new Date());
//                user.setUpdatedDate(null);
//                user.setGender("Male");
//                user.setAddress("");
//                user.setImageUrl(connection.getImageUrl());
//                userRepository.save(user);
//                UserRole userRole = new UserRole();
//                userRole.setRoleId(RoleIdConstant.Role_User);
//                userRole.setUserId(user.getId());
//                userRole.setStatus(StatusRoleConstant.ActiveStatus);
//
//                userRoleRepository.save(userRole);
//            }
//            return user.getUsername();
//        } catch (Exception e) {
//            return connection.getDisplayName();
//        }
//    }
//
//    public User findUserByEmail(String email) {
//        return StreamSupport
//                .stream(userRepository.findByEmail(email).spliterator(), false)
//                .findFirst().orElse(null);
//    }
//
//    public User findUserByUsername(String username) {
//        return StreamSupport
//                .stream(userRepository.findByUserName(username).spliterator(), false)
//                .findFirst().orElse(null);
//    }
//}
