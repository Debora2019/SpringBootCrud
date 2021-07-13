package pt.com.springboot.springcourse;

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
import pt.com.springboot.springcourse.model.user;
import pt.com.springboot.springcourse.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
public class controller {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/mostranome/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String greetingText(@PathVariable String name){
        return "Hello " + name;
    }

    @RequestMapping(value = "/hi/{name}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String returnIamHere(@PathVariable String name){
        user userA = new user();
        userA.setName(name);

        userRepository.save(userA);

        return "This is other method " + name;

    }
    @GetMapping(value = "listAll")
    @ResponseBody
    public ResponseEntity<List<user>> listUser(){
        List<user> users = userRepository.findAll();

        return new ResponseEntity<List<user>>(users,HttpStatus.CREATED);
    }

    @PostMapping(value= "save")
    @ResponseBody
    public ResponseEntity<user> save(@RequestBody user userA){
        user useR = userRepository.save(userA);
        return new ResponseEntity<user>(userA, HttpStatus.CREATED);
    }

    @PutMapping(value= "update")
    @ResponseBody
    public ResponseEntity<?> update(@RequestBody user userA){
        if(userA.getId() == null){//validação para o caso do usuario nao colocar o id. Se nao tiver a validação e o 
           // usuario esquecer de colocar o id, será criado outro registro.
            return new ResponseEntity<String>("Id not informed", HttpStatus.OK);
        }
        user useR = userRepository.saveAndFlush(userA);
        return new ResponseEntity<user>(userA, HttpStatus.OK);
    }


    @DeleteMapping(value = "delete")
    @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long iduser){
        userRepository.deleteById(iduser);
        return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
}

    @GetMapping(value = "findUserByid")
    @ResponseBody
    public ResponseEntity<user> findUserByid(@RequestParam(name = "iduser") Long iduser){
        user userA = userRepository.findById(iduser).get();
        return new ResponseEntity<user>(userA, HttpStatus.OK);
}

@GetMapping(value = "searchByName")
    @ResponseBody
    public ResponseEntity<List<user>> searchByName(@RequestParam(name = "name") String name){
       List<user> userA = userRepository.searchByName(name);
        return new ResponseEntity<List<user>>(userA, HttpStatus.OK);
}

}