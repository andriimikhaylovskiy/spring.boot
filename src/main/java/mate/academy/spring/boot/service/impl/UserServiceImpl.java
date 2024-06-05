package mate.academy.spring.boot.service.impl;

import lombok.RequiredArgsConstructor;
import mate.academy.spring.boot.dto.user.UserRegistrationRequestDto;
import mate.academy.spring.boot.dto.user.UserResponseDto;
import mate.academy.spring.boot.exception.RegistrationException;
import mate.academy.spring.boot.mapper.UserMapper;
import mate.academy.spring.boot.repository.user.UserRepository;
import mate.academy.spring.boot.service.UserService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;
    private final UserMapper userMapper;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto requestDto)
            throws RegistrationException {
        if (userRepo.existsByEmail(requestDto.email())) {
            throw new RegistrationException(String.format("User with this email: %s already"
                    + " exists",requestDto.email()));
        }
        return userMapper.toResponseDto(userRepo.save(userMapper.toModel(requestDto)));
    }
}
