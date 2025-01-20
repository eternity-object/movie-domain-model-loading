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
    @Id
    private Long id;

    @MapsId
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name="MOVIE_ID")
    private Movie movie;

    private int sequence;
    private LocalDateTime screeningTime;

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
