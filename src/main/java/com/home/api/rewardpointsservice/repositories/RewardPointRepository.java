package com.home.api.rewardpointsservice.repositories;

import com.home.api.rewardpointsservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardPointRepository extends JpaRepository<Transaction, Integer>
{
}
