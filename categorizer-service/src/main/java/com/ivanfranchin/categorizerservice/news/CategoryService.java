package com.ivanfranchin.categorizerservice.news;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Random;

@Service
public class CategoryService {

    private final String[] categories = new String[]{"Sport", "World", "Science", "Entertainment", "Health"};
    private final Random random = new SecureRandom();

    public String categorize(String title, String text) {
        return categories[random.nextInt(categories.length)];
    }
}
