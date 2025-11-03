package br.com.TrinketStore.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.TrinketStore.Model.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {
}
