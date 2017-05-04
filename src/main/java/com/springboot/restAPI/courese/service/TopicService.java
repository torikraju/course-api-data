package com.springboot.restAPI.courese.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.restAPI.courese.model.Topic;
import com.springboot.restAPI.courese.repository.TopicRepository;

@Service
public class TopicService {

	private TopicRepository topicRepository;

	@Autowired
	public TopicService(TopicRepository topicRepository) {
		this.topicRepository = topicRepository;
	}

	public List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<>();
		topicRepository.findAll().forEach(topics::add);
		return topics;
	}

	public Topic getTopic(String id) {
		return topicRepository.findOne(id);
	}

	public void saveTopic(Topic topic) {
		topicRepository.save(topic);
	}

	public void updateTopic(String id, Topic topic) {
		topicRepository.save(topic);
	}

	public void deleteTopic(String id) {
		topicRepository.delete(id);
	}

}
