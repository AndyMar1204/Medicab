
package com.andy.medicab.services.interfaces;

import java.util.List;

/**
 *
 * @author Ir Andy
 * @param <T>
 */


public interface GenericService<T> {

    /**
     *
     * @param t
     * @return
     */
    public T save(T t);

    /**
     *
     * @param t
     * @return
     */
    public T update(T t);

    /**
     *
     * @param t
     */
    public void delete( T t);

    /**
     *
     * @param id
     * @return
     */
    public T getById(long id);

    /**
     *
     * @return
     */
    public List<T> getAll();

    /**
     *
     * @param id
     * @return
     */
    public boolean checkIfExist(long id);
}
