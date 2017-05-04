package com.springboot.restAPI.courese.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.springboot.restAPI.courese.model.Course;
import com.springboot.restAPI.courese.model.Topic;

public interface CoureseRepository extends CrudRepository<Course, String> {

	public List<Course> findCourseByTopic(Topic topic);

}
