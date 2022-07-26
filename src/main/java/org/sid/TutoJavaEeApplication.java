package org.sid;

import org.sid.dao.ProduitRepository;
import org.sid.entities.Produit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TutoJavaEeApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(TutoJavaEeApplication.class, args);
		ProduitRepository produitRepository = ctx.getBean(ProduitRepository.class);
		produitRepository.save(new Produit("LX 4352", 670, 90));
		produitRepository.save(new Produit("Ord HP 432", 670, 90));
		produitRepository.save(new Produit("Imp Epson", 450, 12));
		produitRepository.save(new Produit("Imp HP 5400", 452, 10));
		
//		produitRepository.findAll().forEach(p -> System.out.println(p.getDesignation()));
	}

}
