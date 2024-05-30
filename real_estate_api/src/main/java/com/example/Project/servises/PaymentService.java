package com.example.Project.servises;

import com.example.Project.repositories.PaymentRepository;
import com.example.Project.tables.Payments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;


    public static String generateNumber(String pattern) {
        List<Character> characters = new ArrayList<>();
        for (char c : pattern.toCharArray()) {
            if (Character.isDigit(c)) {
                characters.add(c);
            }
        }

        long seed = System.nanoTime();
        Collections.shuffle(characters, new Random(seed));

        StringBuilder generatedNumber = new StringBuilder(pattern);
        int index = 0;
        for (char c : generatedNumber.toString().toCharArray()) {
            if (Character.isDigit(c)) {
                generatedNumber.setCharAt(index, characters.get(0));
                characters.remove(0);
            }
            index++;
        }
        return generatedNumber.toString();
    }

    public Payments savePayment(Payments payment) {
        String pattern = "#9874563210";
        String generatedNumber = generateNumber(pattern);
        payment.setControllNumber(generatedNumber);
        return paymentRepository.save(payment);
    }

    public List<Payments> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payments> getPaymentById(String id) {
        return paymentRepository.findById(id);
    }


    public Payments updatePayment(String id, Payments payment) {
        if (paymentRepository.existsById(id)) {
            payment.setPaymentId(id);
            return paymentRepository.save(payment);
        }
        return null;
    }

    public void deletePayment(String id) {
        paymentRepository.deleteById(id);
    }
}
