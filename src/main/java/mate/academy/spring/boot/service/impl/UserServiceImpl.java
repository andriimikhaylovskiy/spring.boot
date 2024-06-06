package mate.academy.spring.boot.service.impl;

import jakarta.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.spring.boot.dto.user.UserRegistrationRequestDto;
import mate.academy.spring.boot.dto.user.UserResponseDto;
import mate.academy.spring.boot.exception.RegistrationException;
import mate.academy.spring.boot.mapper.UserMapper;
import mate.academy.spring.boot.model.Role;
import mate.academy.spring.boot.model.RoleName;
import mate.academy.spring.boot.model.User;
import mate.academy.spring.boot.repository.user.RoleRepository;
import mate.academy.spring.boot.repository.user.UserRepository;
import mate.academy.spring.boot.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private static final String DEFAULT_ROLE = "ROLE_USER";
    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepo;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepo.existsByEmail(requestDto.email())) {
            throw new RegistrationException(String.format("User with this email: %s already"
                    + " exists",requestDto.email()));
        }
        User user = userMapper.toModel(requestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(generateDefaultSetRoles());
        return userMapper.toResponseDto(userRepo.save(user));
    }

    private Set<Role> generateDefaultSetRoles() {
        Role roleFromDB = roleRepo.findByName(RoleName.getByType(DEFAULT_ROLE))
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("Can't find %s in table roles: ", DEFAULT_ROLE)));
        Set<Role> roles = new HashSet<>();
        roles.add(roleFromDB);
        return roles;
    }
}
