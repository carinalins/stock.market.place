package stockmarketplace.stock.market.place.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import stockmarketplace.stock.market.place.component.TradeBuild;
import stockmarketplace.stock.market.place.dao.CompanieRepository;
import stockmarketplace.stock.market.place.dao.InvestorRepository;
import stockmarketplace.stock.market.place.dao.TradeRepository;
import stockmarketplace.stock.market.place.dto.AnyTradeDTO;
import stockmarketplace.stock.market.place.dto.CompanieDTO;
import stockmarketplace.stock.market.place.dto.InvestorDTO;
import stockmarketplace.stock.market.place.dto.ListTradeDTO;
import stockmarketplace.stock.market.place.dto.TradeDTO;
import stockmarketplace.stock.market.place.exception.CompanieShareNotEnough;
import stockmarketplace.stock.market.place.exception.InvestorNotFound;
import stockmarketplace.stock.market.place.model.Companie;
import stockmarketplace.stock.market.place.model.Trade;
import stockmarketplace.stock.market.place.service.ITradeService;

@Controller
public class TradeController {

	@Autowired
	private TradeRepository tradeRepository;

	@Autowired
	private CompanieRepository companieRepository;

	@Autowired
	private InvestorRepository investorRepository;

	@Autowired
	private ITradeService tradeService;
	
	@Autowired
	private TradeBuild build;

	
	
	@GetMapping("/anyTrade")
	public String anyTrade(Model model) {
		
		List<AnyTradeDTO> ListAnyTradeDto = tradeRepository.listCompanyAnyTradeYesterday();
		
		
		model.addAttribute("ListAnyTradeDto", ListAnyTradeDto);
		
		return "anyTradeCompanie";

	}
	
	
	@GetMapping("/anyTradeSubmit")
	public ModelAndView anyTrade() {
		
		List<AnyTradeDTO> ListAnyTradeDto = tradeRepository.listCompanyAnyTradeYesterday();
		
		
		for (AnyTradeDTO anyTradeDTO : ListAnyTradeDto) {
			
			Companie companie =  companieRepository.findById(anyTradeDTO.getId()).get();
			companie.setSharePrices(anyTradeDTO.getNv());
			companie.setDtUpdate(new Date());
			
			companieRepository.save(companie);
		}
		
		
		
		return new ModelAndView("redirect:/");

	}
	
	
	
	
	@GetMapping("/trade")
	public String newTrade(Model model) {
		List<InvestorDTO> listInvestor = investorRepository.allInvestorAsc();
		List<CompanieDTO> listCompanie = companieRepository.allCompanieAsc();

		model.addAttribute("listInvestor", listInvestor);
		model.addAttribute("listCompanie", listCompanie);

		return "newtrade";
	}

	@ModelAttribute("tradeDTO")
	public TradeDTO newTradeDTO() {

		return new TradeDTO();
	}

	@PostMapping("/trade")
	public ModelAndView addTrade(@ModelAttribute("tradeDTO") TradeDTO tradeDTO)
			throws InvestorNotFound, CompanieShareNotEnough {

		Trade trade = build.getCompanie(tradeDTO.getIdCompanie()).getInvestor(tradeDTO.getIdInvestor()).newTradeBuild();

		tradeService.tradeDay(trade);
		
		
		return new ModelAndView("redirect:/");

	}

	@GetMapping("/")
	public String home(Model model) {

		List<ListTradeDTO> listTrade = tradeRepository.listTrade();

		model.addAttribute("listTrade", listTrade);

		return "trade";

	}

}
