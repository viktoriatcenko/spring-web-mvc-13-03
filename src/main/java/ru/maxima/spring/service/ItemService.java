package ru.maxima.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.spring.models.Item;
import ru.maxima.spring.models.Person;
import ru.maxima.spring.repositories.ItemRepository;
import ru.maxima.spring.repositories.PeopleRepository;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Transactional
    public void update(Long id, Item item) {
        item.setId(id);
        itemRepository.save(item);
    }

    @Transactional
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }

    public Item findByItemName(String name) {
        return itemRepository.findByItemName(name);
    }

    public Item findByOwner(Person owner) {
        return itemRepository.findByOwner(owner);
    }

}
