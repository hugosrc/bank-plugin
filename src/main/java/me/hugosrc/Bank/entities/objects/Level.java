package me.hugosrc.Bank.entities.objects;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Level {
    private String name;
    private double price;
    private double maxMoney;
    private double maxIncome;
    private double percentageIncome;
}
