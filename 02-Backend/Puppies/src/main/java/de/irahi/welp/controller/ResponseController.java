package de.irahi.welp.controller;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.irahi.welp.dto.PuppyDTO;
import de.irahi.welp.service.PuppyService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/upload")
public class ResponseController {
	
	@Autowired 
	private PuppyService puppyService;
	
	@PostMapping(value="/addPuppy")
	public ResponseEntity<Map<String, String>> addPuppy(@RequestBody PuppyDTO puppyDTO) {
			Map<String, String> responseVariable = new HashMap<>();
			try {
			puppyService.save(puppyDTO);
			System.out.println(puppyDTO.getImage());
			responseVariable.put("message", "Objekt erfolgreich gespeichert");
			
			return ResponseEntity.ok(responseVariable);
			}catch(Exception e) {
				responseVariable.put("error", "konnte nicht gespeichert werden");
				return ResponseEntity.ok(responseVariable);
			}
				
	}
	
	@PostMapping("/image")
	public ResponseEntity<Map<String, String>> saveImage(@RequestParam("image") MultipartFile file){
		
		String uploadDirectory = "../../03-Frontend/welps/src/assets/images/welps";
		File directory = new File(uploadDirectory);
		Map<String, String> responseVariable = new HashMap<>();
		responseVariable.put("message", "Die Anfrage war erfolgreich");
		if(directory.exists()) { System.out.println("Verzeichnis vorhanden!----------------------------------------------------------------------");}
		else {System.out.println("Verzeichnis ist nicht vorhanden!---------------------------------------------------------------------------");}
		String filePath = Paths.get(uploadDirectory,file.getOriginalFilename() ).toString();
		try {
			Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
			responseVariable.put("message", "Die Anfrage war erfolgreich");
			return ResponseEntity.ok(responseVariable);
		}catch(Exception e) {
			responseVariable.put("error", "Das Bild konnte nicht gespeichert werden");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseVariable);
		}		
	}
	

}
