package in.mshitlearner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {
	private Integer courseSeqId;
	private String courseName;
	private Integer price;
}
