package org.example.expert.domain.manager.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.expert.domain.user.dto.response.UserResponse;

@Getter
@AllArgsConstructor
public class ManagerResponse {

    private final Long id;
    private final UserResponse user;

//    public ManagerResponse(Long id, UserResponse user) {
//        this.id = id;
//        this.user = user;
//    }

    public static ManagerResponse of(Long id, UserResponse user) {
        return new ManagerResponse(id, user);
    }
}
