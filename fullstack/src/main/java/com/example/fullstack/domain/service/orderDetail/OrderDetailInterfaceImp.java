package com.example.fullstack.domain.service.orderDetail;

import com.example.fullstack.domain.exeptions.orderDetail.OrderDetailIdNotFoundException;
import com.example.fullstack.domain.exeptions.orderDetail.OrderDetailNotFoundException;
import com.example.fullstack.domain.repository.OrderDetailRepository;
import com.example.fullstack.persistence.entity.OrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class OrderDetailInterfaceImp implements OrderDetailInterface<OrderDetail> {
    private final OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailInterfaceImp(OrderDetailRepository orderDetailRepository){
        this.orderDetailRepository = orderDetailRepository;
    };

    @Override
    public List<OrderDetail> getAll() {
        return orderDetailRepository.findAll();
    }

    @Override
    public ResponseEntity<OrderDetail> getById(Object id) {
        try{
            Long  newId = Long.parseLong(id.toString());
            OrderDetail optionalOrderDetail = orderDetailRepository.findById(newId)
                    .orElseThrow(() -> new OrderDetailNotFoundException("Order Detail not found"));
            return ResponseEntity.ok(optionalOrderDetail);

        }catch (NumberFormatException e){
            throw new OrderDetailIdNotFoundException("You have entered a letter or another character other than a Long type Number");
        }
    }

    @Override
    public ResponseEntity<OrderDetail> save(OrderDetail orderDetailSave) {
        orderDetailRepository.save(orderDetailSave);
        return ResponseEntity.ok(orderDetailSave);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        if (orderDetailRepository.existsById(id)) {
            orderDetailRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<OrderDetail> update(Object id, OrderDetail orderDetailUpdate) {
        try{
            Long newId = Long.parseLong(id.toString());
            Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(newId);
            if(optionalOrderDetail.isPresent()){

                OrderDetail orderDetailSend = optionalOrderDetail.get();
                orderDetailSend.setOrders(orderDetailUpdate.getOrders());
                orderDetailSend.setProducts(orderDetailUpdate.getProducts());
                orderDetailSend.setQuantity(orderDetailUpdate.getQuantity());
                orderDetailSend.setPrice(orderDetailUpdate.getPrice());
                orderDetailRepository.save(orderDetailSend);

                return ResponseEntity.ok(orderDetailSend);
            } else {
                throw new OrderDetailNotFoundException("Order Detail not found by said ID to update");
            }
        }catch (NumberFormatException e){
            throw new OrderDetailIdNotFoundException("You have entered a letter or/or another character other than a Long type Number");
        }
    }
}
