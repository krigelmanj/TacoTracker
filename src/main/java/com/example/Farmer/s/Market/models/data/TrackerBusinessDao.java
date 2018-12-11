package com.example.Farmer.s.Market.models.data;


import com.example.Farmer.s.Market.models.TrackerBusiness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Transactional
public interface TrackerBusinessDao extends JpaRepository<TrackerBusiness, Long> {
}
