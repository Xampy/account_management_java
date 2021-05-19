/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.xorm.dao;

import banxclient.exception.entity.EntityNotFountException;
import java.sql.Connection;

/**
 *
 * @author Software
 * @param <T>
 */
public abstract class AbstractDAO<T> {

    protected Connection connect = null;

    public AbstractDAO(Connection conn) {
        this.connect = conn;
    }

    /**
     * M�thode de cr�ation
     *
     * @param obj
     * @return boolean
     */
    public abstract boolean create(T obj);

    /**
     * M�thode pour effacer
     *
     * @param obj
     * @return boolean
     */
    public abstract boolean delete(T obj);

    /**
     * M�thode de mise � jour
     *
     * @param obj
     * @return boolean
     */
    public abstract boolean update(T obj);

    /**
     * M�thode de recherche des informations
     *
     * @param id
     * @return T 
     */
    public abstract T find(int id);
    
     /**
     * M�thode de recherche des informations 
     * par objet
     *
     * @param obj
     * @return T 
     */
    public abstract T find(T obj) throws EntityNotFountException;

    /**
     * Select database rows from table without options
     *
     * @return
     */
    public abstract T[] select();

    /**
     * Select rows with additionnal options like offset, limit, ...
     *
     * @param limit
     * @param offset
     *
     * @return
     */
    public abstract T[] selectWithOptions(int limit, int offset);

    /**
     *
     * Get the table rows count
     *
     * @return the table row count
     */
    public abstract int getRowsCount();
}
