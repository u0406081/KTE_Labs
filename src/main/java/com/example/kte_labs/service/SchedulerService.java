package com.example.kte_labs.service;

import static java.util.Objects.nonNull;
import com.example.kte_labs.component.Receipt;
import com.example.kte_labs.dto.Statistic;
import com.example.kte_labs.entity.Discount;
import com.example.kte_labs.entity.Product;
import com.example.kte_labs.exception.DiscountNotFound;
import com.example.kte_labs.repository.DiscountRepository;
import com.example.kte_labs.repository.PositionRepository;
import com.example.kte_labs.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Random;

@Service
public class SchedulerService {
    public static final Byte MIN_DISCOUNT = 5;
    public static final Byte MAX_DISCOUNT = 10;

    @Autowired
    DiscountRepository discountRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    Receipt receipt;
    @Autowired
    PositionRepository positionRepository;

    @Scheduled(fixedDelay = 3_600_000) // 1 hour
    //@Scheduled(fixedRate = 3000) //3 seconds
    @Async
    public void computePrice() throws DiscountNotFound {
        Long prevDiscountId = discountRepository.getMaxDiscountId();
        if (nonNull(prevDiscountId)) {
            Discount prevDiscount = discountRepository.findById(prevDiscountId)
                    .orElseThrow(() -> new DiscountNotFound(prevDiscountId));
            prevDiscount.setCurrent(false);
            discountRepository.save(prevDiscount);
        }
        Long minId = productRepository.getMinProductId();
        Long maxId = productRepository.getMaxProductId();

        Random random = new Random();
        int randomDiscount = MIN_DISCOUNT + random.nextInt(MAX_DISCOUNT - MIN_DISCOUNT + 1);
        Long randomProductId = minId + random.nextInt(maxId.intValue() - minId.intValue() + 1);
        Discount discount = new Discount();
        discount.setDiscount(randomDiscount);
        discount.setDateTime(LocalDateTime.now());
        discount.setProduct(new Product(randomProductId));
        discount.setCurrent(true);
        discountRepository.save(discount);
    }

    @Scheduled(cron = "59 59 23 * * *")
    public void resetReceipt() {
        receipt.setReceiptNumber(100);
        Statistic columns = positionRepository.getStatistic(1L, 1L);
    }
}
