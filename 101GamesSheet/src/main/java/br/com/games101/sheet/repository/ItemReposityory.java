package br.com.games101.sheet.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.games101.sheet.entity.Item;

public interface ItemReposityory  extends JpaRepository<Item,Long> {

}
