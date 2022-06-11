package com.andy.Medicab.services.interfaces;

import java.util.List;

/**
 *
 * @author Ir Andy
 * @param <E> entity
 * @param <I> id
 */
public interface GenericService <E,I>{
    /**
     *
     * @param e
     * @return
     */
    I save(E e);
    /**
     *
     * @param e
     * @return E
     */
    E update(E e);
    /**
     *
     * @param id
     * @return
     */
    E findById(I id);
    /**
     *
     * @param id
     *
     */
    void deleteById(I id);
    /**
     *
     *
     * @return List<E>
     */
    List<E> findAll();
    /**
     *
     *
     * @return boolean
     */
    boolean existById(I id);
}
