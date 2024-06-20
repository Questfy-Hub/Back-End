package com.questifyHub.app.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.questifyHub.app.Entities.Gifts;

public interface GiftsRepository extends JpaRepository<Gifts, Long>{
    Gifts getGiftByGiftName(String giftname);
}
