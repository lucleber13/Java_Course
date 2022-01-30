package uk.co.springboot.course_jdev.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import uk.co.springboot.course_jdev.model.Users;
import uk.co.springboot.course_jdev.repository.UsersRepository;


/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	
	@Autowired /* IC/CD or CDI - Dependency injection*/
	private UsersRepository usersRepository;

    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    
   //testing commit again
    @GetMapping(value = "listAll") /*First method API*/
    @ResponseBody /*Return the data to response body*/
    public ResponseEntity<List<Users>> listUsers(){
    	
    	List<Users> users = usersRepository.findAll();
    	
    	return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
    	
    }
    
    
    @PostMapping(value = "saving")
    @ResponseBody
    public ResponseEntity<Users> saving(@RequestBody Users users){ 
    	
    	Users user = usersRepository.save(users);
    	
    	return new ResponseEntity<Users>(user, HttpStatus.CREATED);
    	
    }
    
    
    @PutMapping(value = "update")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody Users users){ 
    	
    	if (users.getId() == null) {
			return new ResponseEntity<String>("Id not informed  to update!.", HttpStatus.OK);
		}
    	
    	Users user = usersRepository.saveAndFlush(users);
    	
    	return new ResponseEntity<Users>(user, HttpStatus.OK);
    	
    }
    
    
    @DeleteMapping(value = "delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long iduser){ 
    	
    	usersRepository.deleteById(iduser);
    	
    	return new ResponseEntity<String>("User deleted with success! ", HttpStatus.OK);
    	
    }
    
    
    @GetMapping(value = "findUserById")
    @ResponseBody
    public ResponseEntity<Users> findUserById(@RequestParam(name = "iduser") Long iduser){ 
    	
    	Users user = usersRepository.findById(iduser).get();
    	
    	return new ResponseEntity<Users>(user, HttpStatus.OK);
    	
    }
    
    
    @GetMapping(value = "searchByName")
    @ResponseBody
    public ResponseEntity<List<Users>> searchByName(@RequestParam(name = "name") String name){ 
    	
    	List<Users> user = usersRepository.searchByName(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Users>>(user, HttpStatus.OK);
    	
    }
}
