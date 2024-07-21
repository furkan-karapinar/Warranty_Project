package com.furkankarapinar.garanti_kontrol;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

        /**
         * Handles requests to the root URL ("/").
         *
         * @param name The name parameter from the request, defaulting to "World" if not provided.
         * @param model The Model object to add attributes to be used in the view.
         * @return The name of the view template to be rendered ("index").
         */
        @RequestMapping("/")
        public String index(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
                model.addAttribute("name", name);
                return "index";
        }

        /**
         * Handles requests to the "/add" URL.
         *
         * @return The name of the view template to be rendered ("add").
         */
        @RequestMapping("/add")
        public String add() {
                return "add";
        }

        /**
         * Handles requests to the "/devicelist" URL.
         *
         * @return The name of the view template to be rendered ("devicelist").
         */
        @RequestMapping("/devicelist")
        public String devicelist() {
                return "devicelist";
        }
}
