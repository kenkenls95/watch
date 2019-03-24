package application.data.service;

import java.util.List;

public interface IRepository {

    List search(Integer id);

    Object save(Object object);

    Boolean delete(Integer id);
}
