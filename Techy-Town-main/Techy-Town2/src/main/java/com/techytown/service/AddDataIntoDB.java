//package com.techytown.service;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.techytown.models.Category;
//import com.techytown.models.Product;
//import com.techytown.repository.CategoryRepository;
//import com.techytown.repository.ProductRepository;
//
//@Component
//public class AddDataIntoDB {
//	
//	@Autowired
//	private CategoryRepository categoryRepository;
//	
//	@Autowired
//	private ProductRepository productRepository;
//	
//	 @PostConstruct
//	 private void postConstructForCat() {
//		 Category cat1 = new Category("Household","Household Products To Clean PC and Other.");
//		 Category cat2 = new Category("Mobiles_Tablets","Top Branded Mobiles and Tablets.");
//		 Category cat3 = new Category("Beverages","What is Coders Most needed Drink ? It is Here.");
//		 Category cat4 = new Category("Electronics","Wireless Earphones, RAM, SSD, etc");
//		 Category cat5 = new Category("TVs & Appliances","Newest TV's with Discount");
//		  
//		  
//		 categoryRepository.save(cat1);
//		 categoryRepository.save(cat2);
//		 categoryRepository.save(cat3);
//		 categoryRepository.save(cat4);
//		 categoryRepository.save(cat5);
//		 
//		 	System.out.println("============================Added Cats=====================================");
//		
//		Product p1= new Product("Containers", "Pack of 7 | white", 1499.0, 125.0, "url");
//		p1.setCategory(cat1);
//		
//		Product p2= new Product("JNT Water Bottle", "1000ml | Copper", 999.0, 499.0, "url");
//		p2.setCategory(cat1);
//		
//		Product p3= new Product("ASE Trigger Spray", "Pack of 3", 130.0, 97.0, "url");
//		p3.setCategory(cat1);
//		
//		Product p4= new Product("Infinix Note 12 5G", "Dimensity 810 | FHD+", 19_999.0,11_999.0, "url");
//		p4.setCategory(cat2);
//		
//		Product p5= new Product("realme C33", "Boundless Sea Design", 11_999.0, 7_999.0, "url");
//		p5.setCategory(cat2);
//		
//		Product p6= new Product("moto e40", "48MP Triple Camera", 10_999.0, 7_249.0, "url");
//		p6.setCategory(cat2);
//		
//		Product p7= new Product("Redmi 10", "Massive 6000mAh Battery", 14_999.0, 8_499.0, "url");
//		p7.setCategory(cat2);
//		
//		Product p8= new Product("moto g72", "Billion 10-bit in Display", 21_999.0, 13_999.0, "url");
//		p8.setCategory(cat2);
//		
//		Product p9= new Product("Sprite", "750ml | Pack of 1", 40.0, 31.0, "url");
//		p9.setCategory(cat3);
//		
//		Product p10= new Product( "Thumbs Up", "2.25ml | Pack of 1", 99.0, 73.0, "url");
//		p10.setCategory(cat3);
//		
//		Product p11= new Product("Coca-Cola", "750ml | Pack of 1", 40.0, 34.0, "url");
//		p11.setCategory(cat3);
//		
//		Product p12= new Product("Budweiser", "3x330ml", 333.0, 330.0, "url");
//		p12.setCategory(cat3);
//		
//		Product p13= new Product("SanDisk Ultra 64 GB", "MicroSDXC Class 10 ",  1000.0,529.0, "url");
//		p13.setCategory(cat4);
//		
//		Product p14= new Product("SAMSUNG Evo Plus 128 GB ", "MicroSDXC Class 10 130 MB/s",  3999.0,999.0, "url");
//		p14.setCategory(cat4);
//		
//		Product p15= new Product(" Seagate Basic", "Portable", 3799.0, 5899.0, "url");
//		p15.setCategory(cat4);
//		
//		Product p16= new Product("TOSHIBO 1TB", "Canvio Partner", 3999.0,6000.0, "url");
//		p16.setCategory(cat4);
//		
//		Product p17= new Product("OnePlus U1S", "164 cm (65 inch) Ultra HD (4K) LED Smart Android TV", 69_999.0,61_999.0, "url");
//		p17.setCategory(cat5);
//		
//		Product p18= new Product("SONY Bravia", "163.9 cm (65 inch) Ultra HD (4K) LED Smart Google TV", 139900.0, 81_550.0, "url");
//		p18.setCategory(cat5);
//		
//		Product p19= new Product("Thomson", "Alpha 80 cm", 10999.0,7999.0, "url");
//		p19.setCategory(cat5);
//		
//		
//		productRepository.save(p1);
//		productRepository.save(p2);
//		productRepository.save(p3);
//		productRepository.save(p4);
//		productRepository.save(p5);
//		productRepository.save(p6);
//		productRepository.save(p7);
//		productRepository.save(p8);
//		productRepository.save(p9);
//		productRepository.save(p10);
//		productRepository.save(p11);
//		productRepository.save(p12);
//		productRepository.save(p13);
//		productRepository.save(p14);
//		productRepository.save(p15);
//		productRepository.save(p16);
//		productRepository.save(p17);
//		productRepository.save(p18);
//		productRepository.save(p19);
//
//		System.out.println("=========================Added Products==============================");
//		
//	 }
//	 
//	 
//
//
//}
