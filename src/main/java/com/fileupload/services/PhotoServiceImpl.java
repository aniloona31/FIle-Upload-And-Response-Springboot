package com.fileupload.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fileupload.Dao.PhotoRepository;
import com.fileupload.Dao.PlaceRepository;
import com.fileupload.Dao.UserRepository;
import com.fileupload.Dto.PhotoResponse;
import com.fileupload.Entity.Photos;
import com.fileupload.Entity.Place;
import com.fileupload.Entity.User;

@Service
public class PhotoServiceImpl implements PhotoService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PhotoRepository photoRepository;
	
	@Autowired
	private PlaceRepository placeRepository;
	
	@Value("${project.image}")
	private String path;
	
	@Override
	public String uploadPhoto(MultipartFile file, Integer userId, String placeName){
		User user = userRepository.getById(userId);
		if(user == null) {
			return "user not found";
		}
		
		Place place = placeRepository.getByPlaceName(placeName);
		if(place == null) {
			return "place not found";
		}
		
		String fileName = file.getOriginalFilename();
		String uniqueId = UUID.randomUUID().toString();
		String filePath = path + "\\" + fileName + uniqueId;
		
		File fileFolder = new File(path);
		
		if(!fileFolder.exists()) {
			fileFolder.mkdir();
		}
		
		try {
			Files.copy(file.getInputStream(), Paths.get(filePath));
			Photos photo = new Photos(userId, filePath, user, place);
			photoRepository.save(photo);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return "posted";
	}

	private PhotoResponse convertImageToBytes(Photos photo) {
		PhotoResponse photoResponse = new PhotoResponse();
		byte[] byteArray = null;
		try {
			byteArray = Files.readAllBytes(Paths.get(photo.getPhotoUrl()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(byteArray.toString());
		photoResponse.setPhoto(byteArray);
		return photoResponse;
	}
	
	@Override
	public List<PhotoResponse> getAllPhotos(String placeName) {
		Place place = placeRepository.getByPlaceName(placeName);
		if(place == null) {
			throw new RuntimeException("place doesn't exist"); 
		}
		
		return photoRepository.getByPlace(place)
				.stream()
				.map(this :: convertImageToBytes)
				.collect(Collectors.toList());
	}

}
