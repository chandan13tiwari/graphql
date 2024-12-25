package com.poc.graphql.controller;

import com.poc.graphql.entity.Student;
import com.poc.graphql.entity.Subject;
import com.poc.graphql.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    // Get student by id
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Optional<Student> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add a new student
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    // Update student by id
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return updatedStudent != null ? ResponseEntity.ok(updatedStudent) : ResponseEntity.notFound().build();
    }

    // Delete student by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Add a subject to a student
    @PostMapping("/{studentId}/subjects")
    public Subject addSubjectToStudent(@PathVariable Long studentId, @RequestBody Subject subject) {
        return studentService.addSubjectToStudent(studentId, subject);
    }

    // Get subjects for a student
    @GetMapping("/{studentId}/subjects")
    public List<Subject> getSubjectsByStudentId(@PathVariable Long studentId) {
        return studentService.getSubjectsByStudentId(studentId);
    }

    // Delete subject by id
    @DeleteMapping("/subjects/{id}")
    public ResponseEntity<Void> deleteSubject(@PathVariable Long id) {
        return studentService.deleteSubject(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
