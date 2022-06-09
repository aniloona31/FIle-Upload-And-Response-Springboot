package com.fileupload.services;


import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fileupload.Dto.PhotoResponse;


public interface PhotoService {
	public String uploadPhoto(MultipartFile file,Integer userId,String placeName) throws IOException;

	public List<PhotoResponse> getAllPhotos(String placeName);
}
