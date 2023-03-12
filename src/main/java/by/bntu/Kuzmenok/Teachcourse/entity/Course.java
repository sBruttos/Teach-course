package by.bntu.Kuzmenok.Teachcourse.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "course")

public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(min = 3, max = 75, message = "Name should be contains more than 3 characters")
    @NotBlank(message = "Name should be not empty")
    private String name;

    @Size(min = 10, max = 250, message = "Description should be between 10 and 250 characters")
    @NotBlank(message = "Description should be not empty")
    private String description;

    @Column(name = "fileName")
    private String fileName;

    @Transient
    @ManyToMany(mappedBy = "courses")
    private Set<User> users;

    private boolean status;

    @PrePersist
    private void prePersist(){
        this.status = true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (status != course.status) return false;
        if (!id.equals(course.id)) return false;
        if (!name.equals(course.name)) return false;
        if (!description.equals(course.description)) return false;
        return fileName.equals(course.fileName);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + fileName.hashCode();
        result = 31 * result + (status ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", fileName='" + fileName + '\'' +
                ", status=" + status +
                '}';
    }
}
