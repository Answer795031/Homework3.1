package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService{

    private final Map<Long,Student> studentMap = new HashMap<>();
    private long counter = 0;

    @Override
    public Student addStudent(Student student) {
        this.counter++;
        long id = counter;
        student.setId(id);
        studentMap.put(id, student);

        return student;
    }

    @Override
    public Student getStudent(Long id) {
        if (!studentMap.containsKey(id)) {
            throw new StudentNotFoundException("Ошибка! Студент не найден!");
        }

        return studentMap.get(id);
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student targetStudent = studentMap.get(id);

        targetStudent.setName(student.getName());
        targetStudent.setAge(student.getAge());

        return targetStudent;
    }

    @Override
    public Student removeStudent(Long id) {
        return studentMap.remove(id);
    }

    @Override
    public List<Student> studentsByAge(int age) {
        return studentMap.values()
                .stream()
                .filter(student -> student.getAge() == age)
                .collect(Collectors.toList());
    }
}
