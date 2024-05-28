package com.example.SteamClone.services;

import com.example.SteamClone.models.Game;
import com.example.SteamClone.models.Order;
import com.example.SteamClone.models.User;
import com.example.SteamClone.repositories.OrderRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderService
{
    private final OrderRepository orderRepository;
    private final BucketService bucketService;

    public List<Order> listOrders(User user)
    {
        return orderRepository.findByUser(user);
    }
    public void createOrder(HttpSession session, User user)
    {
        List<Game> lst = (List<Game>) session.getAttribute("lstBucket");
        if(user.getBalance() < bucketService.sumBucket(lst))
        {
            return;
        }
        Order order = new Order();
        order.setUser(user);
        order.setGames(lst);
        orderRepository.save(order);
        log.info("Saving new Order. User: {}", user.getEmail());
    }
}
