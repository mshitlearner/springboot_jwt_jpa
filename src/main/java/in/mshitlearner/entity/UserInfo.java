package in.mshitlearner.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "MSH_USER_INFO")
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_SEQ_ID")
	private Integer id;

	@Column(name = "US_NAME")
	private String firstName;

	@Column(name = "US_ID")
	private String userId;

	@Column(name = "ROLES")
	private String roles;
	
	@Column(name = "PASSWORD")
	private String password;

}