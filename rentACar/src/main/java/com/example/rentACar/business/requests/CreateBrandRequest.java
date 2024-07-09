package com.example.rentACar.business.requests;


// request API'ye gelen istektir. Örneğin markaları listele
// response kullanıcıya verilen yanıttır.
// Burda sisteme bi şey verilir.

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateBrandRequest {
    // id almıyoruz. Son kullanıcı id de atamamalı. Onu sistem halletmeli.
    @NotNull
    @NotBlank
    @Size(min = 3, max = 20)
    private String name;

}
