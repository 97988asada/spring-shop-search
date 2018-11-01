package handson.example.springshopsearch.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import handson.example.springshopsearch.model.item.Item;
import handson.example.springshopsearch.model.item.ItemRepository;

@Controller
@RequestMapping("/items")
public class ItemController {

    @GetMapping
    public String listItem(Model model) {
        List<Item> list = itemRepository.findAll();
        model.addAttribute("items", list); //登録したものを追加
        return "item_list";
    }
        @GetMapping("add") //URLの一番後ろについてくる奴、登録画面の追加のページ
        public String getForm() { //変数
            return "item_form"; //item_formの追加の部分からとってくる
    }

        @GetMapping("{id}") //もともとなかった詳細ボタンから飛ぶURL
        public String getKousin(Model model,@PathVariable("id")long id) { //変数
        	model.addAttribute("item", itemRepository.getOne(id));
            return "item_kousin"; //item_formの追加の部分からとってくる
    }
        @Autowired
       	ItemRepository itemRepository;

        @PostMapping("/add") //URLの一番後ろについてくる奴、登録画面の追加の部分
        public String registerItem(Item item) { //変数
            itemRepository.save(item);
            return "redirect:/items"; //itemsの登録画面に飛んでく
        }
        @PostMapping("{id}") //URLの一番後ろについてくる奴、登録画面の更新の部分
        public String kousinItem(Item item) { //変数
            itemRepository.save(item);
            return "redirect:/items"; //itemsの登録する画面に飛んでく
        }
}
//getMappingやPostMappingでエラーが起こるのは、同じURLに飛んで行ってしまい、衝突してしまうため