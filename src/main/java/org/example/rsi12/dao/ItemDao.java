package org.example.rsi12.dao;

import org.example.rsi12.model.Item;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemDao {

    private Long idCounter = 0L;
    private Map<Long, Item> database= new LinkedHashMap<>();

    public Item getItemById(Long id){
        return database.get(id);
    }

    public void putItem(Item item){
        item.setId(idCounter);
        database.put(idCounter,item);
        idCounter++;
    }

    public List<Item> getAllItems(){
        return database.values().stream().toList();
    }

}
