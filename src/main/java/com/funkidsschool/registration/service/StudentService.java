package com.funkidsschool.registration.service;

import com.funkidsschool.registration.exception.StudentNotFoundException;
import com.funkidsschool.registration.model.Student;
import com.funkidsschool.registration.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public List<Student> getStudentsByName(String name) {
        List<Student> students = studentRepository.findByName(name);
        if (students.isEmpty()) {
            throw new StudentNotFoundException("No students found with name: " + name);
        }
        return students;
    }

    public List<Student> getStudentsByClass(String classRoom) {
        List<Student> students = studentRepository.findByClassRoom(classRoom);
        if (students.isEmpty()) {
            throw new StudentNotFoundException("No students found with name: " + classRoom);
        }
        return students;
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }
}
