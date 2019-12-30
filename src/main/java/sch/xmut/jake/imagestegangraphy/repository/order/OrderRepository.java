package sch.xmut.jake.imagestegangraphy.repository.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sch.xmut.jake.imagestegangraphy.domain.order.OrderEntity;
import java.util.List;

/**
 * Created by jake.lin on 2019/12/25
 */
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    OrderEntity findFirstByOrderByOrderTimeDesc();
    @Query(value = "select * from user_order where user_id = :userId and payment_status = :paymentStatus " +
            "and order_status = :orderStatus and download_status = :downloadStatus", nativeQuery = true)
    List<OrderEntity> noDownloadOrder(@Param("userId") Integer userId, @Param("paymentStatus") Integer paymentStatus,
                                          @Param("orderStatus") Integer orderStatus, @Param("downloadStatus") Integer downloadStatus);
    Page<OrderEntity> findAllByUserIdAndOrderStatus(Integer userId, Integer orderStatus, Pageable pageable);
    List<OrderEntity> findAllByUserIdAndOrderStatus(Integer userId, Integer orderStatus);
    Page<OrderEntity> findAllByUserId(Integer userId, Pageable pageable);
    OrderEntity findByOrderNumber(String orderNumber);
}
