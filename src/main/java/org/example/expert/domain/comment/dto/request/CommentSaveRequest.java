package org.example.expert.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentSaveRequest {

    @NotBlank(message = "내용은 필수 입력입니다.")
    @Size(min = 1, message = "내용은 1자 이상이여야합니다.")
    private String contents;
}
