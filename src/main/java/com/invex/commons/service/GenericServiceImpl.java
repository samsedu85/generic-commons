/**
 * 
 */
package com.invex.commons.service;

import static com.invex.commons.utils.GenericConstans.ERROR_CODE_NOT_FOUND;
import static com.invex.commons.utils.GenericConstans.ERROR_MESSAGE_NOT_FOUND;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.invex.commons.exception.NotFoundInvexException;

/**
 * @author EduSam
 * 
 *         E -> entity 
 *         T -> DTO 
 *         R -> repository
 * 
 */
public class GenericServiceImpl<E, T, R extends JpaRepository<E, Long>> implements IGenericService<T> {

	@Autowired
	protected R repository;

	/**
	 * Devuelve una lista de objetos
	 */
	@Override
	@Transactional(readOnly = true)
	public List<T> findAll() {
		List<E> listEntity = repository.findAll();
		List<T> listDTO = new ArrayList<>();
		for (E entity : listEntity) {
			@SuppressWarnings("unchecked")
			T objectDTO = (T) this.getClass().getSuperclass();
			BeanUtils.copyProperties(entity, objectDTO);
			listDTO.add(objectDTO);
		}
		return listDTO;
	}

	/**
	 * Devuelve un objeto de acuerdo al ID
	 */
	@Override
	@Transactional(readOnly = true)
	public T findById(Long id) {
		Optional<E> entity = repository.findById(id);
		entity.orElseThrow(() -> new NotFoundInvexException(ERROR_MESSAGE_NOT_FOUND, ERROR_CODE_NOT_FOUND));
		@SuppressWarnings("unchecked")
		T objectDTO = (T) getClass();
		BeanUtils.copyProperties(entity.get(), objectDTO);
		return objectDTO;
	}

	/**
	 * Guarda uno o mas elementos
	 *
	 */
	@Override
	public List<T> save(List<T> dto) {
		List<T> createListEmployee = new ArrayList<T>();
		dto.stream().forEach(objectDto -> {
			@SuppressWarnings("unchecked")
			E entity = (E) getClass();
			BeanUtils.copyProperties(objectDto, entity);
			E newEntity = repository.save(entity);
			@SuppressWarnings("unchecked")
			T newDto = (T) getClass();
			BeanUtils.copyProperties(newEntity, newDto);
			createListEmployee.add(newDto);

		});
		return createListEmployee;
	}

	/**
	 * Elimina un elemento por ID
	 */
	@Override
	public void deleteById(Long id) {
		try {
			repository.deleteById(id);
		} catch (Exception error) {
			throw new NotFoundInvexException(ERROR_MESSAGE_NOT_FOUND, ERROR_CODE_NOT_FOUND);
		}
	}

}
