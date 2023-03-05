package com.techytown.payload.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
 
@Data
public class SignupRequest {
    
	@NotEmpty
    @Size(min = 3, max = 20,message = "Please Enter Username Between 3-20 Characters.")
    private String username;
 
	@NotEmpty
    @Size(max = 50)
    @Email(message = "Please Enter Vaild Email !")
    private String email;
    
    private Set<String> role;
    
    @NotEmpty
    @Size(min = 8, max = 12,message = "Password Must Be Greater than 8 Characters.")
    private String password;
  
}
