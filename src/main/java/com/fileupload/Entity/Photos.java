package com.fileupload.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Photos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer photoId;
	
	@Column(length = 500)
	private String photoUrl;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "userId",name = "userId")
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(referencedColumnName = "placeId", name = "placeId")
	private Place place;
}
