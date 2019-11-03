package net.guides.springboot2.springboot2jpacrudexample.model;

import javax.persistence.Column;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exams")
public class Exam {

	private long id;
	private String examName;
	private String location;
	private int time;
	
	public Exam() {
		
	}
	
	public Exam(String examName, String location, int time) {
		this.examName = examName;
		this.location = location;
		this.time = time;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "examName", nullable = false)
	public String getExamName() {
		return examName;
	}
	public void setExamName(String examName) {
		this.examName = examName;
	}
	
	@Column(name = "location", nullable = false)
	@Length(max = 10, message = "The field must be less than 50 characters")
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	@Column(name = "time", nullable = false)
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Exam [id=" + id + ", examName=" + examName + ", location=" + location + ", time=" + time
				+ "]";
	}
	
}
