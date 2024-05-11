package project4.travel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import project4.travel.Entity.Tourism;

import java.util.List;

public interface TourismRepository extends JpaRepository<Tourism, String> {
    List<Tourism> findByGRPNMContaining(String groupName); // 인원
    List<Tourism> findBySEASONNMContaining(String seasonName); // 계절

    List<Tourism> findByIEMNMContaining(String iemName); // 테마
}


