package com.ab.templateApi.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "Rooms")
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RoomId", nullable = false, unique = true, updatable = false)
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "MovieId")
    private Movie movie;

    @OneToMany(mappedBy = "room", orphanRemoval = true, fetch = FetchType.LAZY)
    private List<WorkHour> workHour;

    public Room(Long roomId) {
        this.roomId = roomId;
    }
}
