package org.automation;

import io.cucumber.plugin.EventListener;
import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.TestCaseStarted;
import io.cucumber.plugin.event.TestRunFinished;

public class IDsRetrieverCucumber6Jvm implements EventListener {

    private EventHandler<TestCaseStarted> caseStartedEventHandler;
    private EventHandler<TestRunFinished> caseFinishedEventHandler;
    private NiceAppendable out;

    public IDsRetrieverCucumber6Jvm(Appendable out) {
        caseStartedEventHandler = event -> extractID(event);
        caseFinishedEventHandler = event -> finishTestCaseEvent();
        this.out = new NiceAppendable(out);
    }

    private void extractID(TestCaseStarted event) {
        String title = event.getTestCase().getName();
        String regex = ".*\\((\\d+)\\).*";
        if(title.matches(regex)) {
            String id = title.replaceAll(regex, "$1").concat("\n");
            out.append(id);
        }
    }

    private void finishTestCaseEvent() {
        System.out.println("IDs extraction completed!");
    }

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestCaseStarted.class, caseStartedEventHandler);
        publisher.registerHandlerFor(TestRunFinished.class, caseFinishedEventHandler);
    }
}
