package com.fileupload.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fileupload.Entity.Photos;
import com.fileupload.Entity.Place;

public interface PhotoRepository extends JpaRepository<Photos, Integer>{

	List<Photos> getByPlace(Place place);

}
