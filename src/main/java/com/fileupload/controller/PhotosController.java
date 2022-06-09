package com.fileupload.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.Dto.PhotoResponse;
import com.fileupload.services.PhotoService;

@RestController
@RequestMapping("/photos")
public class PhotosController {

	@Autowired
	private PhotoService photoService;
	
	@PostMapping("/user/{userId}/place/{placeName}")
	public ResponseEntity<String> uploadPhoto(@RequestParam("image") MultipartFile image,@PathVariable(name = "userId") Integer userId,@PathVariable(name = "placeName") String placeName) throws IOException{
		return new ResponseEntity<String>(photoService.uploadPhoto(image, userId, placeName),HttpStatus.OK);
	}
	
	@GetMapping("/place/{placeName}")
	public ResponseEntity<List<PhotoResponse>> getAllPhotosOfPlace(@PathVariable String placeName){
		return new ResponseEntity<List<PhotoResponse>>(photoService.getAllPhotos(placeName),HttpStatus.OK);
	}
}
