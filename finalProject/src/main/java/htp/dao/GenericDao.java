package htp.dao;



public interface GenericDao <T, K> {
    T save (T item);
    T update (T item);
    void delete (K id);
    T findById (K id);
}
