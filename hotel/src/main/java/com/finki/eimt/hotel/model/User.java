package com.finki.eimt.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String idCardNumber;

    private String email;

    private String phoneNumber;

    public boolean isValid() {
        return StringUtils.isNoneEmpty(firstName,lastName,idCardNumber,email,phoneNumber);
    }
}
