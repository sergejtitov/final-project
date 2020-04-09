package htp.controller.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class SearchCriteria {

    private String key;
    private String operation;
    private Object value;

}
