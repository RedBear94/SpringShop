package com.spring.mvc.controllers;

import com.spring.mvc.model.Product;
import com.spring.mvc.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HelloController {
    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    // context-path: http://localhost:8190/app

    // При запросе на http://localhost:8190/app/hello - вернется сообщение
    /*@RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody // вернет строку
    public String sayHello(){
        return "Hello";
    }*/

    // Вернет имя html страницы из templates
    @GetMapping("/product_page")
    public String showProductPage(Model model){
        List<Product> list = productService.getAllProducts();
        // model - точка обмена между бэкендом и фронтендом
        model.addAttribute("products", list); // model - подобен hashMap - в атрибут с именем products кладем list
        return "hello";
    }

    // http://localhost:8190/app/hello_user?name=Bob&surname=Johnson
    @GetMapping("/hello_user")
    @ResponseBody
    public String sayHello(@RequestParam(name = "name", defaultValue = "Unknown") String username, @RequestParam(defaultValue = "User") String surname){
        return String.format("Hello, %s %s !!!", username, surname);
    }

    // http://localhost:8190/app/docs/100/info
    @GetMapping("/docs/{doc_id}/info")
    @ResponseBody
    public String showDocInfo(@PathVariable(name = "doc_id") Long id) {
        return "Document #" + id;
    }

    @PostMapping("/add_product")
    public String addNewProduct(@RequestParam String title, @RequestParam int cost) {
        Long id = productService.getFreeId();
        Product product = new Product(id, title, cost);
        productService.save(product);
        return "redirect:/product_page";
    }

    @GetMapping("/delete_product/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/product_page";
    }

    @GetMapping("/show_me_product")
    @ResponseBody
    public Product addNewProduct(@ModelAttribute Product product){ // Создает пустой product и заполняет через setter переданные параметры
        // Product product = new Product(id, title, cost)
        return product; // вернет json за счет настроек в beans.xml и pom.xml
    }

    // Обратный вариант, принимаем json как post запрос
    /*@PostMapping("/show_me_product")
    @ResponseBody
    public Product addNewProduct(@RequestBody Product product){ // RequestBody - говорм, что в тело будет зашит объект product
        // Product product = new Product(id, title, cost)
        product.setTitle("Red");
        return product; // вернет json за счет настроек в beans.xml и pom.xml
    }*/
}
