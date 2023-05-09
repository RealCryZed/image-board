package com.example.imageboard.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "report")
@NoArgsConstructor
public class Report {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator="REPORT_SEQ")
    @SequenceGenerator(name="REPORT_SEQ", sequenceName="REPORT_SEQ")
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "comment_id", referencedColumnName = "id")
    private Comment comment;

    @Column(name = "reported_at", updatable = false)
    @CreationTimestamp
    private Date reportedAt;

    @Column(name = "is_active")
    private boolean isActive;
}
