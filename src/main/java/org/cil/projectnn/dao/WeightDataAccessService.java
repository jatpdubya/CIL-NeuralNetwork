//package org.cil.projectnn.dao;
//
//import org.cil.projectnn.model.Weight;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import javax.swing.text.html.Option;
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@Repository("postgres")
//public class WeightDataAccessService implements WeightDAO {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public WeightDataAccessService(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public int insertWeight(UUID id, Weight weight) {
//        final String sql = "INSERT INTO weight (id, val) VALUES " +
//                "('" + id + "', '" + weight.getVal() + "')" ;
//        jdbcTemplate.execute(sql);
//        return 1;
//    }
//
//    @Override
//    public List<Weight> selectAll() {
//        final String sql = "SELECT id, val FROM weight";
//        return jdbcTemplate.query(sql, (resultSet, i) -> {
//            UUID id = UUID.fromString(resultSet.getString("id"));
//            String val = resultSet.getString("val");
//            return new Weight(id, val);
//        });
//    }
//
//    @Override
//    public Optional<Weight> selectWeight(UUID id) {
//        final String sql = "SELECT id, val FROM weight WHERE id = ?";
//
//        Weight weight = jdbcTemplate.queryForObject(sql, new Object[]{id},
//                (resultSet, i) -> {
//                UUID weightId = UUID.fromString(resultSet.getString("id"));
//                String val = resultSet.getString("val");
//                return new Weight(weightId, val);
//        });
//        return Optional.ofNullable(weight);
//    }
//
//    @Override
//    public int deleteWeight(UUID id) {
//        final String sql = "DELETE FROM weight WHERE id = '" + id + "'";
//        jdbcTemplate.execute(sql);
//        return 1;
//    }
//
//    @Override
//    public int updateWeight(UUID id, Weight weight) {
//        return 0;
//    }
//}
