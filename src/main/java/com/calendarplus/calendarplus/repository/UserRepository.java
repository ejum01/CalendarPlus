package com.calendarplus.calendarplus.repository;

import com.calendarplus.calendarplus.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * UserRepository 클래스입니다.
 *
 * @author : ejum
 * @since : 9/5/24
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);


    User findByUsername(String username);

    boolean existsByUsername(String displayName);
}