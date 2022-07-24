package org.example;

import org.example.entities.Item;
import org.example.entities.Member;
import org.example.entities.Order;
import org.example.entities.OrderItem;

import javax.persistence.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction t = em.getTransaction();

        try{
            t.begin();

            Member member = Member.builder().name("member1").build();
            em.persist(member);

            Order order = Order.builder().member(member).orderDateTime(LocalDateTime.now()).build();
            em.persist(order);

            Item item = Item.builder().name("item1").build();
            em.persist(item);

            OrderItem orderItem = OrderItem.builder().order(order).item(item).build();
            em.persist(orderItem);

            t.commit();
        }catch (Exception e){
            t.rollback();
        }

        em.close();

        emf.close();
    }
}