package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.domain.File;

public interface FileRepository extends MongoRepository<File, String> {
}
