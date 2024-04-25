package com.example.fullstack.domain.service.orders;

import com.example.fullstack.domain.exeptions.orders.OrdersIdNotFoundException;
import com.example.fullstack.domain.exeptions.orders.OrdersNotFoundException;
import com.example.fullstack.domain.repository.OrdersRepository;
import com.example.fullstack.persistence.entity.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersInterfaceImp implements OrdersInterface<Orders> {
    private final OrdersRepository ordersRepository;

    @Autowired
    public OrdersInterfaceImp(OrdersRepository ordersRepository){
        this.ordersRepository = ordersRepository;
    };

    @Override
    public List<Orders> getAll() {
        return ordersRepository.findAll();
    }

    @Override
    public ResponseEntity<Orders> getById(Object id) {
        try{
            Long  newId = Long.parseLong(id.toString());
            Orders optionalOrders = ordersRepository.findById(newId)
                    .orElseThrow(() -> new OrdersNotFoundException("Order not found"));
            return ResponseEntity.ok(optionalOrders);

        }catch (NumberFormatException e){
            throw new OrdersIdNotFoundException("You have entered a letter or another character other than a Long type Number");
        }
    }

    @Override
    public ResponseEntity<Orders> save(Orders ordersSave) {
        ordersRepository.save(ordersSave);
        return ResponseEntity.ok(ordersSave);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        if (ordersRepository.existsById(id)) {
            ordersRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Orders> update(Object id, Orders ordersUpdate) {
        try{
            Long newId = Long.parseLong(id.toString());
            Optional<Orders> optionalOrders = ordersRepository.findById(newId);
            if(optionalOrders.isPresent()){

                Orders orderSend = optionalOrders.get();
                orderSend.setUser(ordersUpdate.getUser());
                orderSend.setOrder_date(ordersUpdate.getOrder_date());
                orderSend.setTotal_amount(ordersUpdate.getTotal_amount());
                orderSend.setStatus(ordersUpdate.getStatus());
                ordersRepository.save(orderSend);

                return ResponseEntity.ok(orderSend);
            } else {
                throw new OrdersNotFoundException("Orders not found by said ID to update");
            }
        }catch (NumberFormatException e){
            throw new OrdersIdNotFoundException("You have entered a letter or/or another character other than a Long type Number");
        }
    }
}



