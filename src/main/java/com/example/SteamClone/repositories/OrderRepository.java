package com.example.SteamClone.repositories;

import com.example.SteamClone.models.Order;
import com.example.SteamClone.models.User;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
