package com.spyatthehatch.advent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * WebController class to handle all endpoint requests and forward
 * appropriately.
 * @author Bill Everton
 * @version Advent 2022
 */
@Controller
public class WebController {
   /**
    * Logger.
    */
   private static final Logger LOGGER = 
      LoggerFactory.getLogger(WebController.class);

   /**
    * Controller for application /advent end point. 
    * 
    * @param name Foo 
    * @param model Bar
    * @return View
    */
   @GetMapping("/advent")
   public String advent(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
      model.addAttribute("name", name);
      model.addAttribute("title", Constants.HTTP_TITLE);
      LOGGER.info("Received incoming advent request.");
      return "advent";
   }

}