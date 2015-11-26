package cn.itcast.controller;

import cn.itcast.domain.Items;
import cn.itcast.service.ItemsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/items")
public class ItemsController {

    @Resource
    private ItemsService itemsService;

    @RequestMapping("/selectItems")
    public String selectItems(Model model) {
        List<Items> list = itemsService.selectAllItems();
        model.addAttribute("itemList", list);
        return "itemsList";
    }

    //先跳转到修改页面
    @RequestMapping("editItems")
    public String editItems(Model model, Integer id) {

        Items item = itemsService.selectItemsByID(id);
        model.addAttribute("item", item);
        return "editItem";
    }

    //根据Id进行修改
    @RequestMapping("updateItems")
    public String updateItems(Items item) {

        itemsService.updateItems(item);

        return "redirect:selectItems.do";

    }


}
