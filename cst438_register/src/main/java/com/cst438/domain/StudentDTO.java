package com.cst438.domain;

import java.util.Objects;

public class StudentDTO {

	public int Student_id;
	public String name;
	public String email;
	public String Status;
	public int Status_code;
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentDTO other = (StudentDTO) obj;
		return Objects.equals(Status, other.Status) && Status_code == other.Status_code
				&& Student_id == other.Student_id && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name);
	}

	public StudentDTO( String name, String email) {
		Student_id = 0;
		this.name = name;
		this.email = email;
		Status = null;
		Status_code = 0;
	}

	// student DTO class
	public StudentDTO() {
		
		Student_id = 0;
		this.name = null;
		this.email = null;
		Status = null;
		Status_code = 0;
	}
	
	
	@Override
	public String toString() {
		return "StudentDTO [Student_id=" + Student_id + ", name=" + name + ", email=" + email + ", Status=" + Status
				+ ", Status_code=" + Status_code + "]";
	}
	
	
}
