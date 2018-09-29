package com.example.Farmer.s.Market.models.data;




import com.example.Farmer.s.Market.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by LaunchCode
 */
@Repository
@Transactional
public interface UserDao extends JpaRepository<User , Long> {
     static boolean existsByName(String name) {
        return true;
    }

    User findTopByName(String name);

}
