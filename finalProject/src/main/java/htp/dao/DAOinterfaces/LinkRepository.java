package htp.dao.DAOinterfaces;


import htp.entities.Link;

import java.util.List;

public interface LinkRepository extends GenericDao<Link,Long>{
    List<Link> findAll ();
}
