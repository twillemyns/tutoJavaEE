package org.sid.web;

import java.util.List;

import org.sid.dao.ProduitRepository;
import org.sid.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProduitController {
	@Autowired
	private ProduitRepository produitRepository;
	 
	@RequestMapping(value = "/index")
	public String index(Model model, 
			@RequestParam(name = "page", defaultValue = "0")int p,
			@RequestParam(name = "size", defaultValue = "5")int s,
			@RequestParam(name = "motCle", defaultValue = "")String mc) {
        Pageable pageable = PageRequest.of(p, s);
		Page<Produit> pageProduits = produitRepository.chercher("%"+mc+"%", pageable);
		model.addAttribute("listProduits", pageProduits.getContent());
		int[] pages = new int[pageProduits.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);
		model.addAttribute("pageCourante", p);
		model.addAttribute("motCle", mc);
		return "produits";
	}
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String delete(Long id, String motCle, int page, int size) {
		produitRepository.deleteById(id);
		return "redirect:/index?page="+page+"&size="+size+"&motCle="+motCle;
	}
	@RequestMapping(value="form", method=RequestMethod.GET)
	public String formProduit(Model model) {
		model.addAttribute("produit", new Produit());
		return "FormProduit";
	}
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String save(Model model, Produit produit) {
		produitRepository.save(produit);
		return "Confirmation";
	}
}
