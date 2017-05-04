package com.springboot.restAPI.courese.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.restAPI.courese.model.Topic;

public interface TopicRepository extends CrudRepository<Topic, String> {

}
