package com.my.projmanager.model.impl;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.my.projmanager.model.PersonInf;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "empl_id"))
@AttributeOverride(name = "created", column = @Column(name = "empl_create_date"))
@AttributeOverride(name = "updated", column = @Column(name = "empl_update_date"))
@AttributeOverride(name = "name", column = @Column(name = "empl_name"))
@AttributeOverride(name = "lastname", column = @Column(name = "empl_lastname"))
@AttributeOverride(name = "mail", column = @Column(name = "empl_mail"))
@AttributeOverride(name = "phone", column = @Column(name = "empl_phone"))
@AttributeOverride(name = "skype", column = @Column(name = "empl_skype"))
@AttributeOverride(name = "telegramm", column = @Column(name = "empl_telegramm"))
@EqualsAndHashCode(callSuper = true)
public class Employee extends PersonInf {

    @Column(name = "empl_password", nullable = false, unique = true)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empl_position", nullable = false)
    @JsonManagedReference
    private Position position;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "empl_role", nullable = false)
    @JsonManagedReference
    private Role role;

    @Column(name = "empl_fired")
    private Boolean fired = false;
}
