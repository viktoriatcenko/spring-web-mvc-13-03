package ru.maxima.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxima.spring.models.Item;
import ru.maxima.spring.models.Person;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByItemName(String name);
    Item findByOwner(Person owner);
}
