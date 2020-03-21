package htp.entities.front_entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressFront {

    @Size(max = 200)
    private String address;

    @Size(max = 100)
    private String addressType;
}
