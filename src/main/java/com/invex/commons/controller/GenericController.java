/**
 * 
 */
package com.invex.commons.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.invex.commons.service.IGenericService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * @author EduSam
 * 
 *         E -> entity 
 *         T -> DTO 
 *         S -> service
 *
 */
public class GenericController<T, S extends IGenericService<T>> {

	@Autowired
	protected S service;

	/**
	 * Devuelve una lista de todos los empleados
	 * @return
	 */
	@Operation(summary = "Returns the list of all registered employees")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful get list elements"),
			@ApiResponse(responseCode = "500", description = "Server error")
	})
	@GetMapping
	public ResponseEntity<?> getListElements() {
		return ResponseEntity.ok().body(service.findAll());
	}

	/**
	 * Busca a empleado por medio de su ID
	 * @param id
	 * @return
	 */
	@Operation(summary = "Retrieves an employee's details by their ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful by ID"),
			@ApiResponse(responseCode = "404", description = "Employee not found"),
			@ApiResponse(responseCode = "500", description = "Server error")
	})
	@GetMapping("/{id}")
	public ResponseEntity<?> getElementById(@PathVariable Long id) {
		return ResponseEntity.ok(service.findById(id));
	}

	/**
	 * @param listDto
	 * @return Guarda uno o mas empleados
	 */
	@Operation(summary = "Allows you to insert one or more employees in the same request")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful created"),
			@ApiResponse(responseCode = "500", description = "Server error")
	})
	@PostMapping
	public ResponseEntity<?> save(@RequestBody List<T> listDto) {
		List<T> newListDto = service.save(listDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(newListDto);
	}

	
	/**
	 * Elimina empleado por medio de su ID
	 * @param id
	 * @return
	 */
	@Operation(summary = "Delete an employee by their ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful delete by ID"),
			@ApiResponse(responseCode = "404", description = "Employee not found"),
			@ApiResponse(responseCode = "500", description = "Server error")
	})
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
