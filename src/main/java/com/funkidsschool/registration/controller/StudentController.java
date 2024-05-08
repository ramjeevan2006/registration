package com.funkidsschool.registration.controller;

import com.funkidsschool.registration.model.Student;
import com.funkidsschool.registration.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.addStudent(student));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Student>> getStudentsByNameOrClass(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String classRoom) {
        if (name != null) return ResponseEntity.ok(studentService.getStudentsByName(name));
        if (classRoom != null) return ResponseEntity.ok(studentService.getStudentsByClass(classRoom));
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.updateStudent(student));
    }
}

