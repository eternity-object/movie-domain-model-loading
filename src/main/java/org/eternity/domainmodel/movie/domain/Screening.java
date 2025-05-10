package org.eternity.domainmodel.movie.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.eternity.domainmodel.generic.Money;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Screening {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int sequence;
    private LocalDateTime screeningTime;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name= "MOVIE_ID")
    private Movie movie;

    public Screening(Movie movie, int sequence, LocalDateTime screeningTime) {
        this.movie = movie;
        this.movie.setScreening(this);
        this.sequence = sequence;
        this.screeningTime = screeningTime;
    }

    public Money getFixedFee() {
        return movie.getFee();
    }
}
