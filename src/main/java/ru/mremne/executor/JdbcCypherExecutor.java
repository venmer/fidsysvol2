package ru.mremne.executor;

import org.apache.log4j.Logger;

import java.sql.*;
import java.util.*;

/**
 * @author Michael Hunger @since 22.10.13
 */
public class JdbcCypherExecutor implements CypherExecutor {
    private static final Logger LOG =Logger.getLogger(JdbcCypherExecutor.class);
    private Connection conn;

    public JdbcCypherExecutor(String neo4jHost,Integer neo4jPort) {
        LOG.info("http://"+neo4jHost+":"+neo4jPort.toString());
        try {
            conn = DriverManager.getConnection(String.format("jdbc:neo4j://%s:%s",neo4jHost,neo4jPort));
        } catch (SQLException e) {
            LOG.error("can't connect to neo4j");
        }
    }

    @Override
    public Iterator<Map<String, Object>> query(String query, Map<String, Object> params) {
        try {
            final PreparedStatement statement = conn.prepareStatement(query);
            setParameters(statement, params);
            final ResultSet result = statement.executeQuery();
            return new Iterator<Map<String, Object>>() {

                boolean hasNext = result.next();
                public List<String> columns;

                @Override
                public boolean hasNext() {
                    return hasNext;
                }

                private List<String> getColumns() throws SQLException {
                    if (columns != null) return columns;
                    ResultSetMetaData metaData = result.getMetaData();
                    int count = metaData.getColumnCount();
                    List<String> cols = new ArrayList<>(count);
                    for (int i = 1; i <= count; i++) cols.add(metaData.getColumnName(i));
                    return columns = cols;
                }

                @Override
                public Map<String, Object> next() {
                    try {
                        if (hasNext) {
                            Map<String, Object> map = new LinkedHashMap<>();
                            for (String col : getColumns()) map.put(col, result.getObject(col));
                            hasNext = result.next();
                            if (!hasNext) {
                                result.close();
                                statement.close();
                            }
                            return map;
                        } else throw new NoSuchElementException();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
                @Override
                public void remove() {
                }
            };
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setParameters(PreparedStatement statement, Map<String, Object> params) throws SQLException {
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            int index = Integer.parseInt(entry.getKey());
            statement.setObject(index, entry.getValue());
        }
    }
}
