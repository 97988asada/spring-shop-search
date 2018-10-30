package handson.example.springshopsearch.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import handson.example.springshopsearch.model.item.Item;
import handson.example.springshopsearch.model.item.ItemRepository;

@Controller
@RequestMapping("/")
public class HomeController {

//    @GetMapping
//    public String getIndex() {
//        return "index";
//    }

@GetMapping("about")
    public String getAbout() {
        return "about";
    }
	@Autowired
    ItemRepository itemRepository;

    @GetMapping
    public String index(
            Model model,
            @RequestParam(name = "keyword", required = false) Optional<String> keyword,
            @RequestParam(name = "pulldown", required = false) Optional<String> pulldown) {
    	List<Item> list = itemRepository.findAll(); //itemRepository.findAll();をあらかじめ定義しておくことで@Requestの処理から値を持ってこれる
    	if(keyword.isPresent() && pulldown.isPresent()){
    	switch(pulldown.get()) {
    	case "name":
    		list = itemRepository.findByNameContainsOrderByIdAsc(keyword.get());
    		break;
    	case "description":
    		list = itemRepository.findByDescriptionContainsOrderByIdAsc(keyword.get());
    		break;
    	case "NameOrDescription":
    		list = itemRepository.findByNameOrDescriptionContainsOrderByIdAsc(keyword.get(),keyword.get());;
    		break;
    	}
    	}
    	model.addAttribute("items", list);
    	return "index";
    }
}