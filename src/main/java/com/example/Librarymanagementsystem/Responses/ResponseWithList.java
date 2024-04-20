package com.example.Librarymanagementsystem.Responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ResponseWithList<T> extends GenaricResponse{

    private List<T> data;

    public ResponseWithList(List<T> data,int status, String message) {
        super(status,message);
        this.data = data;
    }

}
