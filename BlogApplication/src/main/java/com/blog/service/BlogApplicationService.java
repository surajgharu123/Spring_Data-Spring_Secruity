package com.blog.service;

import com.blog.dto.BlogApplicationDTO;

public interface BlogApplicationService {
	
	public BlogApplicationDTO createNewBlog(BlogApplicationDTO blogApplicationDTO);
	public BlogApplicationDTO readBlogById(int id);
	public BlogApplicationDTO updateBlogById(BlogApplicationDTO blogApplicationDTO, int id);
	public Boolean deleteBlogById(int id);
}
