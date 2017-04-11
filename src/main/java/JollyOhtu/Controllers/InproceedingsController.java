/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JollyOhtu.Controllers;

import JollyOhtu.Objects.Inproceedings;
import JollyOhtu.Repository.InproceedingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 *
 * @author ritakosk
 */
@Controller
public class InproceedingsController {

    @Autowired
    private InproceedingsRepository inproRepo;

    @RequestMapping(value = "/add_inproceedings", method = GET)
    public String inproceedingsForm(Model model) {
        model.addAttribute("inproceedings", new Inproceedings());
        return "add_inproceedings";
    }

    @RequestMapping(value = "/add_inproceedings", method = POST)
    public String inproceedingsSubmit(@ModelAttribute Inproceedings inpro, Model model) {
        if(!inpro.mandatoryFieldsAreFilled()){
            model.addAttribute("error", new String("You must fill in the fields marked by *"));
        } else if (inproRepo.save(inpro) != null) {
            model.addAttribute("success", new String("Reference was saved succesfully!"));
            model.addAttribute("inproceedings", new Inproceedings());
        } else {
            model.addAttribute("error", new String("There was an error saving"
                    + " the reference. Reference not saved"));
        }
        return "add_inproceedings"; 
    }

    //HUOM. vain testausta varten
    @RequestMapping(value = "/delete_inproceedings", method = GET)
    public String inproceedingsDeleteAll() {
        inproRepo.deleteAll();
        return "index";
    }

}