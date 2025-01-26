package com.example.Online.Food.Ordering.repository;

import com.example.Online.Food.Ordering.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRespository  extends JpaRepository<Address,Long> {
}
