package com.example.foodrecipemobileapp.Listeners;

import com.example.foodrecipemobileapp.Models.Responses.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {
    void didFetch(List<InstructionsResponse> response, String message);
    void didError(String message);
}
