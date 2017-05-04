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

import com.springboot.restAPI.courese.model.Topic;
import com.springboot.restAPI.courese.service.TopicService;

@RestController
public class TopicController {

	private TopicService topicService;

	@Autowired
	public TopicController(TopicService topicService) {
		this.topicService = topicService;
	}

	@RequestMapping(value = "/topics", method = RequestMethod.GET)
	public List<Topic> getAllTopics() {
		return topicService.getAllTopics();
	}

	@RequestMapping(value = "/topic/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getTopic(@PathVariable String id) {
		Topic topic = topicService.getTopic(id);
		if (topic == null)
			return new ResponseEntity<>("Item not found.", HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(topic, HttpStatus.FOUND);
	}

	// save topic
	@RequestMapping(value = "/topic", method = RequestMethod.POST)
	public ResponseEntity<?> saveTopic(@Valid @RequestBody Topic topic) {
		topicService.saveTopic(topic);
		return new ResponseEntity<>("data saved", HttpStatus.OK);
	}

	// update topic
	@RequestMapping(value = "/topic/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTopic(@Valid @RequestBody Topic topic, @PathVariable String id) {
		Topic check = topicService.getTopic(id);
		if (check == null)
			return new ResponseEntity<>("Item not found.", HttpStatus.NOT_FOUND);
		topic.setId(check.getId());
		topicService.updateTopic(id, topic);
		return new ResponseEntity<>("data updated", HttpStatus.OK);
	}

	// delete topic
	@RequestMapping(value = "/topic/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTopic(@PathVariable String id) {
		if (topicService.getTopic(id) == null)
			return new ResponseEntity<>("Item not found.", HttpStatus.NOT_FOUND);
		topicService.deleteTopic(id);
		return new ResponseEntity<>("data deleted", HttpStatus.OK);
	}

}
