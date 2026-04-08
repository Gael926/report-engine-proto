package engine.service;
import engine.factory.ReportFactory;
import engine.model.*;
import engine.exception.DataSourceException ;

import java.util.List;


public interface DataSource {

    boolean isAvailable();

    List<DataRecord> fetchData(String query) throws DataSourceException;
}
