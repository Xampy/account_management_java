/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banxclient.utils;

/**
 *
 * @author Software
 */
public interface ObserverInterface {
    /**
     * Update propertions on
     * update notification sent
     * 
     * @param action to do 
     * @param value to be updated
     */
    void update(ObsActionEnum action, Object value);
}
