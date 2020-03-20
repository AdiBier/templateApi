package com.ab.templateApi.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "WorkHours")
@NoArgsConstructor
@AllArgsConstructor
public class WorkHour {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WorkHourId", nullable = false, unique = true, updatable = false)
    private Long workHourId;

    @ManyToOne
    @JoinColumn(name = "roomId")
    private Room room;

    @Column(name = "Day")
    private Integer day;

    @Column(name = "OpeningTime")
    private String openingTime;

    @Column(name = "ClosingTime")
    private String closingTime;

    public WorkHour(Room room, int day, String openingTime, String closingTime ) {
        this.room = room;
        this.day = day;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public WorkHour(int day, String openingTime, String closingTime ) {
        this.day = day;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }
}
