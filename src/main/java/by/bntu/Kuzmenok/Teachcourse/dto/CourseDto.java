package by.bntu.Kuzmenok.Teachcourse.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {

    @Size(min = 3, max = 75, message = "Name should be contains more than 3 characters")
    @NotBlank(message = "Name should be not empty")
    private String name;

    @Size(min = 10, max = 250, message = "Description should be between 10 and 250 characters")
    @NotBlank(message = "Description should be not empty")
    private String description;

    private String fileName;
}
