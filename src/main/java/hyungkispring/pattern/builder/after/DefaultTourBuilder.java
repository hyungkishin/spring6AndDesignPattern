package hyungkispring.pattern.builder.after;

import hyungkispring.pattern.builder.before.DetailPlan;
import hyungkispring.pattern.builder.before.TourPlan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DefaultTourBuilder implements TourPlanBuilder {

    private TourPlan tourPlan;

    public TourPlanBuilder newInstance() {
        this.tourPlan = new TourPlan();
        return this;
    }

    @Override
    public TourPlanBuilder nightAndDays(int nights, int days) {
        this.tourPlan.setNights(nights);
        this.tourPlan.setDays(days);
        return this;
    }

    @Override
    public TourPlanBuilder title(String title) {
        this.tourPlan.setTitle(title);
        return this;
    }

    @Override
    public TourPlanBuilder startDate(LocalDate startDate) {
        this.tourPlan.setStartDate(startDate);
        return this;
    }

    @Override
    public TourPlanBuilder whereToStay(String whereToStay) {
        this.tourPlan.setWhereToStay(whereToStay);
        return this;
    }

    @Override
    public TourPlanBuilder addPlan(int day, String plan) {
        this.tourPlan.addPlan(day, plan);
        return this;
    }

    @Override
    public TourPlan getPlan() {
        return this.tourPlan;
    }

}
