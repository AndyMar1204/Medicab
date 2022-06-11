package com.andy.Medicab.controller;


import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 *
 * @author Ir Andy
 * @param <T> Entité
 * @param <I> Identifiant
 */
public interface Crud<T,I>{
    /**
     *
     * @return <I>
     */
    ResponseEntity<I> save(T t);

    /**
     *
     * @param t
     * @return <T>
     */
    ResponseEntity<T> update(T t,I id);

    /**
     *@param id
     * @return <Void>
     */
    ResponseEntity<Void> delete(I id);

    /**
     *
     * @param id
     * @return <T>
     */
    ResponseEntity<T> findById(I id);
    /**
     *
     *
     * @return ResponseEntity<List<T>>
     */
    ResponseEntity<List<T>> getAll();
    /**
     *
     *
     * @return <Boolean>
     */
    ResponseEntity<Boolean> checkExist(I id);
}
