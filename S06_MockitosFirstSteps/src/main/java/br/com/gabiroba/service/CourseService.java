package br.com.gabiroba.service;

import java.util.List;

public interface CourseService {

    public List<String> retrieveCourses(String student);
    public List<String> doSomething(String student);
    public void deleteCourse(String course);
    
}
