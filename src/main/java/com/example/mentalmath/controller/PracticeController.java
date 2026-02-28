package com.example.mentalmath.controller;

import com.example.mentalmath.dto.AnswerRequest;
import com.example.mentalmath.dto.CheckResponse;
import com.example.mentalmath.dto.ProblemResponse;
import java.security.SecureRandom;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PracticeController {

    private static final int MIN_TWO_DIGIT = 10;
    private static final int MAX_TWO_DIGIT = 99;

    private final SecureRandom random = new SecureRandom();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/history")
    public String history() {
        return "history";
    }

    @GetMapping("/api/problem")
    @ResponseBody
    public ProblemResponse nextProblem() {
        return new ProblemResponse(randomTwoDigit(), randomTwoDigit());
    }

    @PostMapping("/api/check")
    @ResponseBody
    public CheckResponse check(@RequestBody AnswerRequest request) {
        int expected = request.left() * request.right();
        boolean correct = request.answer() == expected;

        String message = correct
                ? "정답입니다! 훌륭해요 🎉"
                : "아쉽네요. 다시 도전해보세요!";

        return new CheckResponse(correct, expected, message);
    }

    private int randomTwoDigit() {
        return random.nextInt(MIN_TWO_DIGIT, MAX_TWO_DIGIT + 1);
    }
}
