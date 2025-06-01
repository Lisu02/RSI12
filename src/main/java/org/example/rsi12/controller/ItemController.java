package org.example.rsi12.controller;

import org.example.rsi12.dao.ItemDao;
import org.example.rsi12.model.Item;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/item")
public class ItemController {

    ItemDao itemDao;

    public ItemController(ItemDao itemDao){
        this.itemDao = itemDao;
    }

    @GetMapping("/ping")
    public String ping(){
        return "Item ping";
    }

    @GetMapping()
    public List<Item> getItemList(){
        return itemDao.getAllItems();
    }

    @GetMapping("/{id}")
    public EntityModel<Item> getItemById(@PathVariable Long id){
        Item item = itemDao.getItemById(id);

        return EntityModel.of(item,
                linkTo(methodOn(ItemController.class).getItemById(id)).withSelfRel(),
                linkTo(methodOn(ItemController.class).getItemList()).withRel("Items"));
    }

    @PostMapping()
    public void putItem(@RequestBody Item item){
        itemDao.putItem(item);
    }

}
