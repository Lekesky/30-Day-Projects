package com.leke.personal_finance_tracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.leke.personal_finance_tracker.model.Users;
public interface UserRepository extends JpaRepository<Users, String>{

    
}
