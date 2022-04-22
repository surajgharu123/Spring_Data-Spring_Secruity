package com.blog.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.dto.BlogApplicationDTO;
import com.blog.entity.BlogApplicationDocumnent;
import com.blog.exception.InvalidIdException;
import com.blog.exception.InvalidRequestBodyException;
import com.blog.repository.BlogApplicationRepo;

@Service
public class BlogApplicationServiceImpl implements BlogApplicationService {

	@Autowired
	BlogApplicationRepo blogApplicationRepo;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Override
	public BlogApplicationDTO createNewBlog(BlogApplicationDTO blogApplicationDTO) {
		// TODO Auto-generated method stub
		if(blogApplicationDTO.getTitle() == null || blogApplicationDTO.getId() == null)
		{
			throw new InvalidRequestBodyException();
		}
		BlogApplicationDocumnent blogApplicationDocumnent = convertDTOIntoDocument(blogApplicationDTO);
		blogApplicationDTO = convertDocumentIntoDTO(blogApplicationRepo.save(blogApplicationDocumnent));
		return blogApplicationDTO;
	}

	@Override
	public BlogApplicationDTO readBlogById(int id) {
		// TODO Auto-generated method stub
		
		Optional<BlogApplicationDocumnent> blogDocumentsOptional = blogApplicationRepo.findById(id);
		if(blogDocumentsOptional.isPresent())
		{
			BlogApplicationDTO blogApplicationDTO = convertDocumentIntoDTO(blogDocumentsOptional.get());
			return blogApplicationDTO;
		}
		else
			throw new InvalidIdException("Enter Please Valid Id Instead of Invalid Id" + id);
		
	}

	@Override
	public BlogApplicationDTO updateBlogById(BlogApplicationDTO blogApplicationDTO, int id) {
		// TODO Auto-generated method stub
		Optional<BlogApplicationDocumnent> blogDocumentsOptional = blogApplicationRepo.findById(id);
		if(blogDocumentsOptional.isPresent())
		{
			BlogApplicationDTO updatedBlogApplication = convertDocumentIntoDTO(blogDocumentsOptional.get()); 
			updatedBlogApplication.setTitle(blogApplicationDTO.getTitle());
			return convertDocumentIntoDTO(blogApplicationRepo.save(convertDTOIntoDocument(updatedBlogApplication)));
			
		}
		else
			throw new InvalidIdException("Enter Please Valid Id Instead of Invalid Id" + id);
	}

	@Override
	public Boolean deleteBlogById(int id) {
		// TODO Auto-generated method stub
		Optional<BlogApplicationDocumnent> blogDocumentsOptional = blogApplicationRepo.findById(id);
		if(blogDocumentsOptional.isPresent())
		{
			blogApplicationRepo.deleteById(id);
			return true;
		}
		else
			throw new InvalidIdException("Enter Please Valid Id Instead of Invalid Id" + id);
	}

	
	
	private BlogApplicationDTO convertDocumentIntoDTO(BlogApplicationDocumnent blogApplicationDocumnent) {
		
		BlogApplicationDTO blogApplicationDTO = modelMapper.map(blogApplicationDocumnent, BlogApplicationDTO.class);
		return blogApplicationDTO;
	}

	private BlogApplicationDocumnent convertDTOIntoDocument(BlogApplicationDTO blogApplicationDTO) {

		BlogApplicationDocumnent blogApplicationDocumnent = modelMapper.map(blogApplicationDTO, BlogApplicationDocumnent.class);

		return blogApplicationDocumnent;
	}
}
