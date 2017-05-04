package com.springboot.restAPI.courese.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.restAPI.courese.model.Course;
import com.springboot.restAPI.courese.model.Topic;
import com.springboot.restAPI.courese.service.CourseService;
import com.springboot.restAPI.courese.service.TopicService;

@RestController
public class CourseController {

	private CourseService courseService;
	private TopicService topicService;

	@Autowired
	public CourseController(CourseService courseService, TopicService topicService) {
		this.courseService = courseService;
		this.topicService = topicService;
	}

	// get all courses
	@RequestMapping(value = "/topic/courses", method = RequestMethod.GET)
	public List<Course> getAllCourse() {
		return courseService.getAllCourese();
	}

	// get courses by topic
	@RequestMapping(value = "/topic/{id}/courses", method = RequestMethod.GET)
	public ResponseEntity<?> getCoursesByTopics(@PathVariable String id) {
		Topic topic = topicService.getTopic(id);
		if (topic == null) {
			return new ResponseEntity<>("Topic not found.", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(courseService.getCourseByTopic(topic), HttpStatus.OK);
	}

	// get particular course
	@RequestMapping(value = "/topic/course/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getTopic(@PathVariable String id) {
		Course course = courseService.getCourse(id);
		if (course == null)
			return new ResponseEntity<>("Course not found.", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(course, HttpStatus.FOUND);
	}

	// save Course
	@RequestMapping(value = "/topic/{topicId}/course", method = RequestMethod.POST)
	public ResponseEntity<?> saveCourse(@Valid @RequestBody Course course, @PathVariable String topicId) {
		Topic topic = topicService.getTopic(topicId);
		if (topic == null)
			return new ResponseEntity<>("Topic not found.", HttpStatus.NOT_FOUND);
		course.setTopic(topic);
		courseService.saveOrUpdateCourse(course);
		return new ResponseEntity<>("data saved", HttpStatus.OK);
	}

	// update topic
	@RequestMapping(value = "/topic/{topicId}/course/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTopic(@Valid @RequestBody Course course, @PathVariable String topicId,
			@PathVariable String id) {
		Topic topic = topicService.getTopic(topicId);
		if (topic == null)
			return new ResponseEntity<>("Topic not found.", HttpStatus.NOT_FOUND);
		Course check = courseService.getCourse(id);
		if (check == null)
			return new ResponseEntity<>("Course not valid.", HttpStatus.NOT_FOUND);

		course.setId(check.getId());
		course.setTopic(topic);
		courseService.saveOrUpdateCourse(course);
		return new ResponseEntity<>("data updated", HttpStatus.OK);
	}

	// delete course
	@RequestMapping(value = "/topic/course/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTopic(@PathVariable String id) {
		if (courseService.getCourse(id) == null)
			return new ResponseEntity<>("Course not found.", HttpStatus.NOT_FOUND);
		courseService.deleteCourse(id);
		return new ResponseEntity<>("data deleted", HttpStatus.OK);
	}

}
