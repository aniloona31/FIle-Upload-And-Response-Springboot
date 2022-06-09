package com.fileupload.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fileupload.Entity.Place;


public interface PlaceRepository extends JpaRepository<Place, Integer>{

	Place getByPlaceName(String placeName);

}
