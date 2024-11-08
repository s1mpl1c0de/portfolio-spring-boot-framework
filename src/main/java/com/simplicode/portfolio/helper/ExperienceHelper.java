package com.simplicode.portfolio.helper;

import com.simplicode.portfolio.model.Experience;
import com.simplicode.portfolio.util.CalendarUtil;
import org.springframework.stereotype.Component;

@Component
public class ExperienceHelper {

    public String getPeriod(Experience experience) {
        return String.format("%s - %s", getStarted(experience), getEnded(experience));
    }

    private String getStarted(Experience experience) {
        return String.format(
           "%s %d",
           CalendarUtil.getMonthAbbr(experience.getStartedMonth()),
           experience.getStartedYear()
        );
    }

    private String getEnded(Experience experience) {
        String ended = String.format(
           "%s %d",
           CalendarUtil.getMonthAbbr(experience.getEndedMonth()),
           experience.getEndedYear()
        );

        return !experience.getIsStillInRole() ? ended : "Present";
    }

}
