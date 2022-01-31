/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.andy.medicab.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Ir Andy
 * @param <T>
 */
public interface GenericCrud<T> {

    /**
     *
     * @return
     */
    ResponseEntity<T> save(T t);

    /**
     *
     * @param t
     * @return
     */
    ResponseEntity<T> update(T t,Long id);

    /**
     *
     * @return
     */
    ResponseEntity<Void> delete(long id);

    /**
     *
     * @param id
     * @return
     */
    ResponseEntity<T> findById(long id);
    ResponseEntity<List<T>> getAll();

    public static final String MAPPING_SAVE = "save";
    public static final String MAPPING_UPDATE = "update";
    public static final String MAPPING_FIND_BY_ID = "findById/";
    public static final String MAPPING_DELETE_BY_ID = "deleteById/";
    public static final String MAPPING_FIND_ALL = "findAll";
    public static final String MAPPING_CHECK_EXIST = "checkExist/";

    ResponseEntity<Boolean> checkExistById(long id);
}
