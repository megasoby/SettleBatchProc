package io.megasoby.settlebatchproc;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TB1 {

    public int num;
    public String name;
    public int age;
}
