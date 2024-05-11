package project4.travel.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
public class CreatePostDTO {
    @NotNull
    String title;

    @NotNull
    String content;


}