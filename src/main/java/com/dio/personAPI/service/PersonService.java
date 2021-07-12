package com.dio.personAPI.service;

import com.dio.personAPI.Repository.PersonRepository;
import com.dio.personAPI.dto.request.PersonDTO;
import com.dio.personAPI.dto.response.MessageResponseDTO;
import com.dio.personAPI.entity.Person;
import com.dio.personAPI.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private PersonRepository personRepository;
    private PersonMapper personMapper= PersonMapper.INSTANCE;
    //criamos essa constante instance para retornar um getMapper para ele retornar uma instancia.


    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public MessageResponseDTO createPerson(PersonDTO personDTO){
        Person personToSave = personMapper.toModel(personDTO);

        Person savedPerson = personRepository.save(personToSave);


        return MessageResponseDTO
                .builder()
                .message("Created person with ID = " + savedPerson.getId())
                .build();
    }
    //@RequestBody- informa que vamos receber uma requisição do tipo pessoa com os atributos iguais aos da Classe

}