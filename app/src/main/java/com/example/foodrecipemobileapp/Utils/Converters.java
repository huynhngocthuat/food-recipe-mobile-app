package com.example.foodrecipemobileapp.Utils;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.TypeConverter;

import java.util.Arrays;
import java.util.List;

public final class Converters {
    private static final String SEPARATOR = ",";
    private static final String REGEX_SEP = "\\s*,\\s*";

    @RequiresApi(api = Build.VERSION_CODES.N)
    @TypeConverter
    public static String fromRecipeTypesToString(List<String> types){
        if(types.size() == 1){
            return types.get(0);
        }
        return String.join(SEPARATOR, types);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @TypeConverter
    public static List<String> fromRecipeTypesToString(String typeAsString){
        return Arrays.asList(typeAsString.split(REGEX_SEP));
    }
}
