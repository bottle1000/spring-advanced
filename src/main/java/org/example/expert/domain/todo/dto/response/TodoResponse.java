package org.example.expert.domain.todo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.expert.domain.todo.entity.Todo;
import org.example.expert.domain.user.dto.response.UserResponse;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TodoResponse {

    private final Long id;
    private final String title;
    private final String contents;
    private final String weather;
    private final UserResponse user;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    public static TodoResponse of(Todo todo) {
        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getContents(),
                todo.getWeather(),
                new UserResponse(todo.getUser().getId(), todo.getUser().getEmail()),
                todo.getCreatedAt(),
                todo.getModifiedAt()
        );
    }
}
