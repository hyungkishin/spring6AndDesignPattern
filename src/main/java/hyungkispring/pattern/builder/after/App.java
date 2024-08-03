package hyungkispring.pattern.builder.after;

import hyungkispring.pattern.builder.before.TourPlan;

import java.time.LocalDate;

public class App {

    public static void main(String[] args) {
//        TourPlanBuilder builder = new DefaultTourBuilder();
//        TourPlan plan = builder.title("칸쿤 여행")
//                .nightAndDays(2, 3)
//                .whereToStay("리조트")
//                .addPlan(0, "체크인 하고 짐풀기")
//                .addPlan(0, "저녁식사")
//                .getPlan();
//
//        TourPlan longBeachTrip = builder
//                .title("롱비치")
//                .startDate(LocalDate.of(2021, 7, 15))
//                .getPlan();

        TourDirector tourDirector = new TourDirector(new DefaultTourBuilder());
        System.out.println("tourDirector.cancunTrip() = " + tourDirector.cancunTrip());
        System.out.println("tourDirector.longBeach() = " + tourDirector.longBeach());

    }

}
