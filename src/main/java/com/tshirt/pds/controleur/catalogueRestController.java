
package com.tshirt.pds.controleur;


import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tshirt.pds.entities.Produit;
import com.tshirt.pds.repository.ProduitRepository;
@CrossOrigin("*")
@RestController
public class catalogueRestController {
	private ProduitRepository produitRepository;

	public catalogueRestController(ProduitRepository produitRepository) {
		this.produitRepository = produitRepository;
	}
	 @GetMapping(path="/photoProduct/{id}", produces= MediaType.IMAGE_PNG_VALUE)
	public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
		 Produit p=produitRepository.findById(id).get();
		 return Files.readAllBytes(Paths.get(System.getProperty("user.home")+"/ecommerceapp/produit/"+p.getPhotoNom()));
		 
		 
		
	}
	 @PostMapping(path="/uploadphoto/{id}")
	 public void uploadphoto(MultipartFile file, @PathVariable Long id) throws Exception {
		 Produit p=produitRepository.findById(id).get();
		 p.setPhotoNom(id+".png");
		 Files.write(Paths.get(System.getProperty("user.home")+"/ecommerceapp/produit/"+p.getPhotoNom()), file.getBytes());
		 produitRepository.save(p);
	 }

}
