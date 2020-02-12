package htp.dao.DAOinterfaces;



public interface GenericDao <T, K> {
    K save (T item);
    T update (T item);
    void delete (K id);
    T findById (K id);
}
