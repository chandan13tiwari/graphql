package com.poc.graphql.service;

import com.poc.graphql.entity.Student;
import com.poc.graphql.entity.Subject;
import com.poc.graphql.exception.ResourceNotFoundException;
import com.poc.graphql.repository.StudentRepository;
import com.poc.graphql.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    private final SubjectRepository subjectRepository;

    public StudentService(StudentRepository studentRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.subjectRepository = subjectRepository;
    }

    // Create a new student
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Get student by id
    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    // Update student details
    public Student updateStudent(Long id, Student student) {
        if (studentRepository.existsById(id)) {
            student.setId(id);
            return studentRepository.save(student);
        }
        return null;
    }

    // Delete student by id
    public boolean deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Add a subject to a student
    public Subject addSubjectToStudent(Long studentId, Subject subject) {
        Student student = getStudentById(studentId).orElse(null);
        if(student == null) {
            throw new ResourceNotFoundException("Student not found for id: " + studentId);
        }
        subject.setStudentId(studentId);
        student.getSubjects().add(subject);
        return subjectRepository.save(subject);
    }

    // Get subjects by studentId
    public List<Subject> getSubjectsByStudentId(Long studentId) {
        return subjectRepository.findByStudentId(studentId);
    }

    // Delete subject by id
    public boolean deleteSubject(Long id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
