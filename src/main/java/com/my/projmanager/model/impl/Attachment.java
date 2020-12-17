package com.my.projmanager.model.impl;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.net.URL;
import java.sql.Timestamp;

@Entity
@Table(name = "attachments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "url", doNotUseGetters = true)
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_id")
    private Long id;

    @Column(name = "file_url", nullable = false, unique = true)
    private URL url;

    @Column(name = "file_create_date", updatable = false)
    @CreationTimestamp
    private Timestamp created;

    @Column(name = "file_update_date")
    @UpdateTimestamp
    private Timestamp updated;
}
