package com.blog.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.blog.entity.BlogApplicationDocumnent;



public interface BlogApplicationRepo extends  MongoRepository<BlogApplicationDocumnent, Integer>{

	
}
