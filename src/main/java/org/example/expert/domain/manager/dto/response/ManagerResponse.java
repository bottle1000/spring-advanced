package org.example.expert.domain.manager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.expert.domain.manager.entity.Manager;
import org.example.expert.domain.user.dto.response.UserResponse;
import org.example.expert.domain.user.entity.User;

@Getter
@AllArgsConstructor
public class ManagerResponse {

    private final Long id;
    private final UserResponse user;


    public static ManagerResponse of(Manager savedManagerUser, User user) {
        return new ManagerResponse(
                savedManagerUser.getId(),
                new UserResponse(user.getId(), user.getEmail())
        );
    }
}
