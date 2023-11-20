package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.exception.StudentNotFoundException;
import ru.hogwarts.school.model.Faculty;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService{

    private final Map<Long,Faculty> facultyMap = new HashMap<>();
    private long counter = 0;

    @Override
    public Faculty addFaculty(Faculty faculty) {
        this.counter++;
        long id = counter;
        faculty.setId(id);
        facultyMap.put(id, faculty);

        return faculty;
    }

    @Override
    public Faculty getFaculty(Long id) {
        if (!facultyMap.containsKey(id)) {
            throw new StudentNotFoundException("Ошибка! Факультет не найден!");
        }

        return facultyMap.get(id);
    }

    @Override
    public Faculty updateFaculty(Long id, Faculty faculty) {
        Faculty targetFaculty = facultyMap.get(id);

        targetFaculty.setName(faculty.getName());
        targetFaculty.setColor(faculty.getColor());

        return targetFaculty;
    }

    @Override
    public Faculty removeFaculty(Long id) {
        return facultyMap.remove(id);
    }

    @Override
    public List<Faculty> facultyByColor(String color) {
        return facultyMap.values()
                .stream()
                .filter(faculty -> faculty.getColor().equals(color))
                .collect(Collectors.toList());
    }
}
