package ra.business.service;

import java.util.List;

public interface IGeneric<T,E> {
    List<T> findAll();
    T findById(E id);
    void save(T t);
    void setStatusById(E id);
    E getNewId();
    boolean checkExist(String str);
    void displayAll();
    List<T> findByName(String name);
}
