package by.bntu.Kuzmenok.Teachcourse.controller.manager;

import by.bntu.Kuzmenok.Teachcourse.entity.Course;
import by.bntu.Kuzmenok.Teachcourse.service.api.CourseService;
import by.bntu.Kuzmenok.Teachcourse.service.api.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/course")
public class ManagerCourseController {

    private static final String PRODUCT_ADDED_MSG = "Product added successfully";

    private CourseService courseService;
    private FileService fileService;

    public ManagerCourseController(CourseService courseService, FileService fileService) {
        this.courseService = courseService;
        this.fileService = fileService;
    }

    @PostMapping("/create")
    public String createCourse(@ModelAttribute @Valid Course course,
                               BindingResult bindingResult,
                               @RequestParam MultipartFile file,
                               RedirectAttributes redirectAttributes) throws IOException{
        if (file.isEmpty()){
            bindingResult.addError(new FieldError("course", "fileName", "Please add photo"));
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("newCourse", course);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newCourse", bindingResult);
            return "redirect:/main";
        }

        course.setFileName(fileService.uploadFile(file));
        courseService.save(course);

        if (courseService.save(course) != null) {
            redirectAttributes.addFlashAttribute("msg", PRODUCT_ADDED_MSG);
        }

        return "redirect:/main";
    }
}
