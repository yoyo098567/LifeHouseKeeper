package com.example.lifeHouseKeeper.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;



@Entity(name = "calMonth")
@Setter @Getter
@NoArgsConstructor
public class CalMonthModel {

    public CalMonthModel(long monthId) {
        MonthId = monthId;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long MonthId;

    @Column(nullable = false)
    private String howMonth;

    @Column
    private String totalCost;

    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private AccountModel accountModel;
}
