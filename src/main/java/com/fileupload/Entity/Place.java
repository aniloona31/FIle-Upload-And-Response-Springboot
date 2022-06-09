package com.fileupload.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Place {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer placeId;
	
	@NotNull
	private String placeName;
	
	@Column
	private String discription;
	
	@Column(length = 1000)
	private String placeImage;
	
}
