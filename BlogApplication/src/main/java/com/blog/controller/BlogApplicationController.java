package com.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.dto.BlogApplicationDTO;
import com.blog.service.BlogApplicationService;

//http://localhost:9097/blogs
@RestController
@RequestMapping("/blogs")
@CrossOrigin(origins = "*")
public class BlogApplicationController {
	
	@Autowired
	BlogApplicationService blogApplicationService;
	
	@PostMapping(value="/", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			produces ={MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<BlogApplicationDTO> createNewBlog(@RequestBody BlogApplicationDTO blogApplicationDTO) {
		return new ResponseEntity<BlogApplicationDTO>(blogApplicationService.createNewBlog(blogApplicationDTO), HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/blog/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<BlogApplicationDTO> readBlogById(@PathVariable("id") int id) {
		return new ResponseEntity<BlogApplicationDTO>(blogApplicationService.readBlogById(id),HttpStatus.OK);
		
	}
	
	@PutMapping(value = "/blog/updates/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, 
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<BlogApplicationDTO> updateBlogById(@RequestBody BlogApplicationDTO blogApplicationDTO, @PathVariable("id") int id){
		return new ResponseEntity<BlogApplicationDTO>(blogApplicationService.updateBlogById(blogApplicationDTO, id), HttpStatus.OK);
	}
	@DeleteMapping(value = "/blog/delete/{id}")
	public ResponseEntity<Boolean> deleteBlogById(@PathVariable("id") int id) {
		return new ResponseEntity<Boolean> (blogApplicationService.deleteBlogById(id) ,HttpStatus.OK);
	}
	

}
