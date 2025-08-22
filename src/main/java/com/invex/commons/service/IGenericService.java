/**
 * 
 */
package com.invex.commons.service;

import java.util.List;

/**
 * @author EduSam
 *
 *         T -> DTO
 * 
 */
public interface IGenericService<T> {

	public List<T> findAll();

	public T findById(Long id);

	public List<T> save(List<T> dto);

	public void deleteById(Long id);

}
