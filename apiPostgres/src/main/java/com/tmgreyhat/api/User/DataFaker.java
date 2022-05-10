package com.tapiwanashembizvo.security.jpa.faker;

import com.tapiwanashembizvo.security.jpa.models.Topic.Topic;
import com.tapiwanashembizvo.security.jpa.models.Topic.TopicService;
import com.tapiwanashembizvo.security.jpa.models.User.User;
import com.tapiwanashembizvo.security.jpa.models.User.UserService;
import com.tapiwanashembizvo.security.jpa.models.course.Course;
import com.tapiwanashembizvo.security.jpa.models.course.CourseService;
import com.tapiwanashembizvo.security.jpa.models.courseNote.CourseNote;
import com.tapiwanashembizvo.security.jpa.models.courseNote.CourseNoteRepository;
import com.tapiwanashembizvo.security.jpa.models.courseNote.CourseNoteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataFaker {


    @Bean
    CommandLineRunner commandLineRunner(
            UserService userService,
            CourseService courseService,
            CourseNoteService courseNoteService,
            TopicService topicService
    ){

        User root_user = new User(
                "tapiwa",
                "tapiwa",
                "21080",
                true,
                "ROLE_USER,ROLE_ADMIN"

        );
        Course course = new Course(
                "BP CHECK",
                LocalDate.now(),
                root_user
        );

        Topic topic = new Topic(
                "BP CHECK",
                course,
                "This is a topic"
        );


        CourseNote courseNote = new CourseNote(
                "Testing BP Patient",
                "Some pretty long formatted",
                "imagenameNice, imagename",
                topic
        );

        return args -> {
            userService.registerNewUser(root_user);
            courseService.addCourse(course);
            topicService.createTopic(topic);
            courseNoteService.createCourseNote(courseNote);
        };
    }

}
