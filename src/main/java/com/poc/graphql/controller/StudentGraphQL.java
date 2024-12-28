package com.poc.graphql.controller;

import com.poc.graphql.entity.Student;
import com.poc.graphql.entity.Subject;
import com.poc.graphql.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class StudentGraphQL {
    @Autowired
    private StudentService studentService;

    // Get all students
    @QueryMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Get student by id
    @QueryMapping
    public Student getStudentById(@Argument int id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.orElse(null);
    }


    // Get subjects for a student
    @QueryMapping
    public List<Subject> getSubjectsByStudentId(@Argument int studentId) {
        return studentService.getSubjectsByStudentId(studentId);
    }

    // Update student by id
    @MutationMapping
    public Student updateStudent(@Argument int id, @Argument Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return updatedStudent;
    }

}
