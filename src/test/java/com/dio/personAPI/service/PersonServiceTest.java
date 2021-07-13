package com.dio.personAPI.service;


import com.dio.personAPI.Repository.PersonRepository;
import com.dio.personAPI.dto.request.PersonDTO;
import com.dio.personAPI.dto.response.MessageResponseDTO;
import com.dio.personAPI.entity.Person;
import com.dio.personAPI.utils.PersonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
//Classe rodar com mockito - testa somente o código de personService. Geramos classes fakes para fazer um teste
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;


    @InjectMocks
    private PersonService personService;

    @Test
    void testGivenPersonDTOThenReturnSaveMessage() {
        PersonDTO personDTO = PersonUtils.createFakeDTO();
        Person expectedSavedPerson =  PersonUtils.createFakeEntity();
        when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);
        MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());
        MessageResponseDTO successMessage = personService.createPerson(personDTO);
        assertEquals(expectedSuccessMessage , successMessage);
    }

    private MessageResponseDTO createExpectedMessageResponse(Long id) {
        return MessageResponseDTO.builder()
                .message("Created person with ID = " + id)
                .build();
    }

}
