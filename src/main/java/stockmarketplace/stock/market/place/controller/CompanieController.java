package stockmarketplace.stock.market.place.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import stockmarketplace.stock.market.place.dao.CompanieRepository;
import stockmarketplace.stock.market.place.dto.CompanyCapital;

@Controller
public class CompanieController {

	@Autowired
	private CompanieRepository companieRepository;

	@GetMapping("/highestCapital")
	public String highestCapital(Model model) {
		
		
		List<CompanyCapital> list =  companieRepository.listHighestCapital();
		
		model.addAttribute("highestCapital", list);
		
		return "highestCapital";

	}
	
	@GetMapping("/lowestCapital")
	public String lowestCapital(Model model) {
		
		
		List<CompanyCapital> list =  companieRepository.listlowestCapital();
		
		model.addAttribute("lowestCapital", list);
		
		return "lowestCapital";

	}

}
