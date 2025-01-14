package org.example.expert.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.example.expert.config.PasswordEncoder;
import org.example.expert.domain.common.exception.InvalidRequestException;
import org.example.expert.domain.user.dto.request.UserChangePasswordRequest;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.entity.User;
import org.example.expert.domain.user.exception.IncorrectPasswordException;
import org.example.expert.domain.user.exception.NotFoundUserException;
import org.example.expert.domain.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse getUser(long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundUserException("User not found"));
        return new UserResponse(user.getId(), user.getEmail());
    }

    @Transactional
    public void changePassword(long userId, UserChangePasswordRequest userChangePasswordRequest) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundUserException("User not found"));
        
        String oldPassword = userChangePasswordRequest.getOldPassword();
        String newPassword = userChangePasswordRequest.getNewPassword();
        String currentPassword = user.getPassword();

        validatePasswordChange(newPassword, currentPassword, oldPassword);

        user.changePassword(passwordEncoder.encode(userChangePasswordRequest.getNewPassword()));
    }


    private void validatePasswordChange(String newPassword, String currentPassword, String oldPassword) {
        if (passwordEncoder.matches(newPassword, currentPassword)) {
            throw new IncorrectPasswordException("새 비밀번호는 기존 비밀번호와 같을 수 없습니다.");
        }

        if (!passwordEncoder.matches(oldPassword, currentPassword)) {
            throw new IncorrectPasswordException("잘못된 비밀번호입니다.");
        }
    }

}
