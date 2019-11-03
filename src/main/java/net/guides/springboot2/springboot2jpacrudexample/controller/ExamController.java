package net.guides.springboot2.springboot2jpacrudexample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.guides.springboot2.springboot2jpacrudexample.exception.ResourceNotFoundException;
import net.guides.springboot2.springboot2jpacrudexample.model.Exam;
import net.guides.springboot2.springboot2jpacrudexample.repository.ExamRepository;

@RestController
@RequestMapping("/api/v2")
public class ExamController {
	@Autowired
	private ExamRepository examRepository;

	@GetMapping("/exams")
	public List<Exam> getAllExams() {
		return examRepository.findAll();
	}

	@GetMapping("/exams/{id}")
	public ResponseEntity<Exam> getExamById(@PathVariable(value = "id") Long examId)
			throws ResourceNotFoundException {
		Exam exam = examRepository.findById(examId)
				.orElseThrow(() -> new ResourceNotFoundException("Exam not found for this id :: " + examId));
		return ResponseEntity.ok().body(exam);
	}

	@PostMapping("/exams")
	public Exam createEmployee(@Valid @RequestBody Exam exam) {
		return examRepository.save(exam);
	}

	@PutMapping("/exams/{id}")
	public ResponseEntity<Exam> updateExam(@PathVariable(value = "id") Long examId,
			@Valid @RequestBody Exam examDetails) throws ResourceNotFoundException {
		Exam exam = examRepository.findById(examId)
				.orElseThrow(() -> new ResourceNotFoundException("Exam not found for this id :: " + examId));

		exam.setTime(examDetails.getTime());
		exam.setLocation(examDetails.getLocation());
		exam.setExamName(examDetails.getExamName());
		final Exam updatedExam = examRepository.save(exam);
		return ResponseEntity.ok(updatedExam);
	}

	@DeleteMapping("/exams/{id}")
	public Map<String, Boolean> deleteExam(@PathVariable(value = "id") Long examId)
			throws ResourceNotFoundException {
		Exam exam = examRepository.findById(examId)
				.orElseThrow(() -> new ResourceNotFoundException("Exam not found for this id :: " + examId));

		examRepository.delete(exam);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
