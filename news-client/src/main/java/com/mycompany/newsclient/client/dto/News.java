package com.mycompany.newsclient.client.dto;

import java.util.Date;

public record News(String id, String title, String text, Date datetime, String category) {
}
