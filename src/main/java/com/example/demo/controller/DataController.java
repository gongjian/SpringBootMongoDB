package com.example.demo.controller;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.PersonDao;
import com.example.demo.dao.PersonRepository;
import com.example.demo.domain.Location;
import com.example.demo.domain.PPerson;
import com.example.demo.domain.Person;

@RestController
public class DataController {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PersonDao personDao;

	// Mongo part
	@RequestMapping("/save")
	public Person save() {
		Person p = new Person("gongjian", 36);

		Collection<Location> locations = new LinkedHashSet<>();
		Location loc1 = new Location("上海", "2011");
		Location loc2 = new Location("北京", "2015");
		Location loc3 = new Location("广州", "2017");

		locations.add(loc1);
		locations.add(loc2);
		locations.add(loc3);

		p.setLocations(locations);

		return personRepository.save(p);
	}

	@RequestMapping("/q1")
	public Person q1(String name) {
		return personRepository.findByName(name);
	}

	@RequestMapping("/q2")
	public List<Person> q2(Integer age) {
		return personRepository.withQueryFindByAge(age);
	}

	// Redis part
	@RequestMapping("/set")
	public void set() {
		PPerson person = new PPerson("1", "gongjian", 36);
		personDao.save(person);
		personDao.stringRedisTemplateDemo();
	}

	@RequestMapping("/getStr")
	public String getStr() {
		return personDao.getString();
	}

	@RequestMapping("/getPerson")
	public PPerson getPerson() {
		return personDao.getPerson();
	}

}
