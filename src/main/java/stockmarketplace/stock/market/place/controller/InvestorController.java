package stockmarketplace.stock.market.place.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import stockmarketplace.stock.market.place.dao.InvestorRepository;
import stockmarketplace.stock.market.place.dto.InvestorHighestSharesDTO;
import stockmarketplace.stock.market.place.dto.InvestorInvestMostCompaniesDTO;

@Controller
public class InvestorController {

	@Autowired
	private InvestorRepository investorRepository;

	@GetMapping("/highestShares")
	public String investorHighestShares(Model model) {
		
		List<InvestorHighestSharesDTO> list =  investorRepository.investorOrderByHighestShares();
		
		model.addAttribute("investorHighestShares", list);
			
		return "highestShares";
	}

	
	@GetMapping("/lowerShares")
	public String investorLowetShares(Model model) {
		
		List<InvestorHighestSharesDTO> list =  investorRepository.investorOrderByLowertShares();
		
		model.addAttribute("investorLowerShares", list);
			
		return "lowerShares";
	}
	
	@GetMapping("/invetedMostCompanies")
	public String invetedMostCompanies(Model model) {
		
		List<InvestorInvestMostCompaniesDTO> list =  investorRepository.investorInvetedMostCompanies();
		
		model.addAttribute("invetedMostCompanies", list);
			
		return "invetedMostCompanies";
	}

	
	@GetMapping("/invetedLeastMostCompanies")
	public String invetedLeastMostCompanies(Model model) {
		
		List<InvestorInvestMostCompaniesDTO> list =  investorRepository.investorLeastInvetedMostCompanies();
		
		model.addAttribute("invetedLeastMostCompanies", list);
			
		return "invetedLeastMostCompanies";
	}
	

}
