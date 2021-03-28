package uni.evaluacion1.backend.dao;

import java.io.IOException;
import java.util.Collection;
import uni.evaluacion1.backend.pojo.Vehicle;

public interface VehicleDao extends Dao<Vehicle>{
    Vehicle findById(int id) throws IOException;
    Collection<Vehicle> findByStatus(String status) throws IOException;
}
