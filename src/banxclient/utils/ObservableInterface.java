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
public interface ObservableInterface {
    /**
     * Add a new observer
    * @param obs
    */

    void addObserver(ObserverInterface obs);
    void updateObervers(ObsActionEnum action, Object value);
    void deleteObserver();
}
