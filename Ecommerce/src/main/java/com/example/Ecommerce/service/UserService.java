package com.example.Ecommerce.service;

import com.example.Ecommerce.DTO.LoginRequest;
import com.example.Ecommerce.DTO.UserDTO;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.enums.UserRole;
import com.example.Ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService myUserDetailsService;

    public User createUser(UserDTO userDTO) throws Exception {

        if (userRepository.existsByEmail(userDTO.getEmail())){
            throw new Exception("User with this email already exists.");
        }

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setUserRole(UserRole.USER);
        user.setUsername(userDTO.getUsername());
        user.setFullName(userDTO.getFullName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setCreateAt(new Date());
        user.setUpdateAt(new Date());

        return userRepository.save(user);
    }

    public UserDTO updateUser(Long userId, UserDTO userDTO) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("User with this id does not exist."));

        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword())); // Mã hóa mật khẩu mới
        }

        user.setUsername(userDTO.getUsername());
        user.setFullName(userDTO.getFullName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setAddress(userDTO.getAddress());
        user.setDateOfBirth(userDTO.getDateOfBirth());
        user.setUpdateAt(new Date());

        User updateUser = userRepository.save(user);

        return updateUser.getUserDTO();
    }

    public UserDTO getUserById(Long userId) throws Exception {
        User user = userRepository.findById(userId).orElseThrow(() -> new Exception("User with this id does not exist."));
        return user.getUserDTO();
    }


    public List<UserDTO> getAllUsers() throws Exception {
        return userRepository.findAll().stream().map(User::getUserDTO).toList();
    }

    public String verify(LoginRequest loginRequest) {
        try {
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(loginRequest.getEmail());

            if (userDetails != null) {
                // In ra mật khẩu đã mã hóa để so sánh
                System.out.println("Encoded Password: " + userDetails.getPassword());
                System.out.println("Input Password: " + loginRequest.getPassword());

                if (passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {

                    User user = userRepository.findByEmail(userDetails.getUsername());

                    return jwtService.generateToken(loginRequest.getEmail());
                } else {
                    return "Invalid email or password"; // Nếu mật khẩu không khớp
                }
            } else {
                return "Invalid email"; // Nếu không tìm thấy người dùng
            }
        } catch (Exception e) {
            return "An error occurred: " + e.getMessage(); // Thông báo lỗi chi tiết
        }
    }




}
