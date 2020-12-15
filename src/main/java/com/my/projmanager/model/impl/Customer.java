package com.my.projmanager.model.impl;

import com.my.projmanager.model.PersonInf;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@Getter
@Setter
@AttributeOverride(name = "id", column = @Column(name = "custom_id"))
@AttributeOverride(name = "name", column = @Column(name = "custom_name"))
@AttributeOverride(name = "lastname", column = @Column(name = "customer_lastname"))
@AttributeOverride(name = "mail", column = @Column(name = "custom_mail"))
@AttributeOverride(name = "phone", column = @Column(name = "custom_phone"))
@AttributeOverride(name = "skype", column = @Column(name = "custom_skype"))
@AttributeOverride(name = "telegramm", column = @Column(name = "custom_telegramm"))
@AttributeOverride(name = "created", column = @Column(name = "custom_create_date"))
@AttributeOverride(name = "updated", column = @Column(name = "custom_update_date"))
@EqualsAndHashCode(callSuper = true)
public class Customer extends PersonInf {

    @Column(name = "custom_address", nullable = false)
    private String address;
}
