package com.example.schoolmanager.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.schoolmanager.service.StudentService;
import com.example.schoolmanager.model.Student;

@RestController
@RequestMapping("/api/students")
@CrossOrigin 
public class StudentController {

    @Autowired
    private StudentService service;

    // Yêu cầu 1 (1đ): API THÊM sinh viên (@PostMapping)
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return service.addStudent(student);
    }

    // Yêu cầu 2 (1đ): API Xóa sinh viên (@PostMapping("/delete/{id}"))
    // LƯU Ý: Đề bắt dùng POST và đường dẫn /delete/{id}
    @PostMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);
        return "Deleted student " + id;
    }

    // Yêu cầu 3 (1đ): API tìm kiếm (@GetMapping("/search"))
    @GetMapping("/search")
    public List<Student> searchByName(@RequestParam String name) {
        return service.findByName(name);
    }

    // Yêu cầu 4 (1đ): API lấy theo ID (@GetMapping("/{id}"))
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return service.getStudentById(id);
    }

    // Yêu cầu 5 (1đ): API lấy danh sách (@GetMapping)
    @GetMapping
    public List<Student> getAllStudents() {
        return service.getAll();
    }
    
    // Yêu cầu 6 (1đ): API cập nhật (@PostMapping("/update/{id}"))
    // LƯU Ý: Đề bắt dùng POST và đường dẫn /update/{id}
    @PostMapping("/update/{id}")
    public Student updateStudent(@PathVariable int id, @RequestBody Student studentDetails) {
        Student existingStudent = service.getStudentById(id);
        if (existingStudent != null) {
            existingStudent.setName(studentDetails.getName());
            existingStudent.setEmail(studentDetails.getEmail());
            return service.addStudent(existingStudent); // Hàm save trong service tự update nếu có ID
        }
        return null;
    }
}