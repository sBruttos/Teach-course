package by.bntu.Kuzmenok.Teachcourse.controller.manager;

import by.bntu.Kuzmenok.Teachcourse.entity.Course;
import by.bntu.Kuzmenok.Teachcourse.repository.CourseRepository;
import by.bntu.Kuzmenok.Teachcourse.service.api.CourseService;
import by.bntu.Kuzmenok.Teachcourse.service.api.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class ManagerCourseController {

    private static final String PRODUCT_ADDED_MSG = "Product added successfully";

    private final CourseService courseService;

    private final CourseRepository courseRepository;
    private final FileService fileService;

    public ManagerCourseController(CourseService courseService, CourseRepository courseRepository, FileService fileService) {
        this.courseService = courseService;
        this.courseRepository = courseRepository;
        this.fileService = fileService;
    }

    @GetMapping("/createCourse")
    public String newCourse(Model model) {
        model.addAttribute("courseForm", new Course());
        return "manager/createCourse";
    }

    @PostMapping("/createCourse")
    public String createCourse(@ModelAttribute("courseForm") @Valid Course course,
                               BindingResult bindingResult,
                               @RequestParam MultipartFile file,
                               RedirectAttributes redirectAttributes) throws IOException {

        if (file.isEmpty()) {
            bindingResult.addError(new FieldError("product", "fileName", "Please add photo"));
        }
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("courseForm", course);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.newProduct", bindingResult);
            return "redirect:manager/createCourse";
        }
        course.setFileName(fileService.uploadFile(file));
        courseRepository.save(course);

        if (courseService.save(course) != null) {
            redirectAttributes.addFlashAttribute("msg", PRODUCT_ADDED_MSG);
        }

        return "/main";
    }

    @GetMapping("/main")
    public String CourseList(Model model){
        model.addAttribute("allCourse", courseService.getAll());
        return "/main";
    }
}
