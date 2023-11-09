package com.example.Library.Management.system.Controller;

import com.example.Library.Management.system.Service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/issue-book/{bookId}/{cardId}")
    public ResponseEntity issueBook(@PathVariable ("bookId") Integer bookId, @PathVariable("cardId") Integer cardId){
        try{
            String result = transactionService.issueBook(bookId,cardId);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/return-book/{bookId}/{cardId}")
    public ResponseEntity returnBook(@PathVariable ("bookId") Integer bookId, @PathVariable("cardId") Integer cardId){
        try{
            String result = transactionService.returnBook(bookId,cardId);
            return new ResponseEntity(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/total-Revenue")
    public ResponseEntity getTotalRevenueCollectedByLibrary(){
        Long revenue = transactionService.getTotalRevenueCollectedByLibrary();
        return new ResponseEntity(revenue,HttpStatus.OK);
    }
}
