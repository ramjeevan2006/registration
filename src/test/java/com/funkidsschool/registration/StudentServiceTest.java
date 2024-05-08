package com.funkidsschool.registration;


import com.funkidsschool.registration.model.Student;
import com.funkidsschool.registration.repository.StudentRepository;
import com.funkidsschool.registration.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        student = new Student();
        student.setId(1L);
        student.setName("Ramjeevan Patha");
        student.setDateOfBirth(LocalDate.of(2010, 4, 5));
        student.setJoiningDate(LocalDate.of(2022, 9, 1));
        student.setClassRoom("5A");
    }
    @Test
    void testAddStudent() {
        when(studentRepository.save(any(Student.class))).thenReturn(student);
        Student savedStudent = studentService.addStudent(student);
        assertNotNull(savedStudent);
        assertEquals("Ramjeevan Patha", savedStudent.getName());
    }
    @Test
    void testGetStudentById() {
        when(studentRepository.findById(1L)).thenReturn(Optional.of(student));
        Student foundStudent = studentService.getStudentById(1L);
        assertNotNull(foundStudent);
        assertEquals(1L, foundStudent.getId());
    }
    @Test
    void testGetStudentsByName() {
        when(studentRepository.findByName("Ramjeevan Patha")).thenReturn(Arrays.asList(student));
        List<Student> students = studentService.getStudentsByName("Ramjeevan Patha");
        assertFalse(students.isEmpty());
        assertEquals(1, students.size());
        assertEquals("Ramjeevan Patha", students.get(0).getName());
    }
    @Test
    void testGetStudentsByClass() {
        when(studentRepository.findByClassRoom("5A")).thenReturn(Arrays.asList(student));
        List<Student> students = studentService.getStudentsByClass("5A");
        assertFalse(students.isEmpty());
        assertEquals(1, students.size());
        assertEquals("5A", students.get(0).getClassRoom());
    }
    @Test
    void testGetStudentById_NotFound() {
        Long nonExistentId = 999L;
        when(studentRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            studentService.getStudentById(nonExistentId);
        });

        assertEquals("Student not found", exception.getMessage());
    }

    @Test
    void testGetStudentById_DatabaseError() {
        when(studentRepository.findById(anyLong())).thenThrow(new RuntimeException("Database error occurred"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            studentService.getStudentById(1L);
        });

        assertEquals("Database error occurred", exception.getMessage());
    }


}


