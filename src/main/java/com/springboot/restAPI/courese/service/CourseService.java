package com.springboot.restAPI.courese.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restAPI.courese.model.Course;
import com.springboot.restAPI.courese.model.Topic;
import com.springboot.restAPI.courese.repository.CoureseRepository;

@Service
public class CourseService {

	private CoureseRepository coureseRepository;

	@Autowired
	public CourseService(CoureseRepository coureseRepository) {
		this.coureseRepository = coureseRepository;
	}

	public List<Course> getAllCourese() {
		List<Course> courses = new ArrayList<>();
		coureseRepository.findAll().forEach(courses::add);
		return courses;
	}

	public List<Course> getCourseByTopic(Topic topic) {
		return coureseRepository.findCourseByTopic(topic);
	}

	public Course getCourse(String id) {
		return coureseRepository.findOne(id);
	}

	public void saveOrUpdateCourse(Course course) {
		coureseRepository.save(course);

	}

	public void deleteCourse(String id) {
		coureseRepository.delete(id);
	}

}
