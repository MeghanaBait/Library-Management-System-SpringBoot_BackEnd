package com.example.Library.Management.system.Service;

import com.example.Library.Management.system.Entities.CardStatus;
import com.example.Library.Management.system.Entities.LibraryCard;
import com.example.Library.Management.system.Entities.Student;
import com.example.Library.Management.system.Exceptions.CardAlreadyUsed;
import com.example.Library.Management.system.Exceptions.CardNotFoundException;
import com.example.Library.Management.system.Exceptions.StudentNotFound;
import com.example.Library.Management.system.Repository.CardRepository;
import com.example.Library.Management.system.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CardService {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    StudentRepository studentRepository;
    public LibraryCard generateCard(){
        LibraryCard card = new LibraryCard();
        card.setCardStatus(CardStatus.NEW);
        card = cardRepository.save(card);
        return card;
    }

    public String associateWithStudent(Integer studentId, Integer cardNo) throws Exception {
        //findById return type is optional
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if(!optionalStudent.isPresent()){
            throw new StudentNotFound("Invalid student ID");
        }
        Student student = optionalStudent.get();


        Optional<LibraryCard> optionalLibraryCard = cardRepository.findById(cardNo);
        if (!optionalLibraryCard.isPresent()){
            throw new CardNotFoundException("Please create card then associate with student");
        }
        LibraryCard libraryCard = optionalLibraryCard.get();

        if(!libraryCard.getCardStatus().equals(CardStatus.NEW)){
            throw new CardAlreadyUsed("Card with ID "+cardNo+" is already assigned to student with ID " +libraryCard.getStudentVariable().getStudentId());
        }

        //setting the required attributes of the libraryCard Entity
        libraryCard.setCardStatus(CardStatus.ACTIVE);
        libraryCard.setNameOnCard(student.getStudentName());
        libraryCard.setStudentVariable(student);

        //setting the required attributes of the student entity
        student.setLibraryCard(libraryCard);

        studentRepository.save(student);
        return "Card with "+cardNo+" has been associated to student with "+studentId;
    }
}
