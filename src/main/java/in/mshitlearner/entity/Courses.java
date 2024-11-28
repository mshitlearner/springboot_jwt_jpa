package in.mshitlearner.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name ="MSH_COURSES")
public class Courses {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="COURSE_SEQ_ID")
		private Integer courseSeqId;
		@Column(name ="COURSE_NAME")
		private String courseName;
		@Column(name="PRICE")
		private Integer price;
}
