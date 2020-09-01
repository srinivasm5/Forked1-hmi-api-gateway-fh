package uk.gov.hmcts.futurehearings.hmi.acceptance.sessions;

import static uk.gov.hmcts.futurehearings.hmi.acceptance.common.helper.CommonHeaderHelper.createStandardPayloadHeader;
import static uk.gov.hmcts.futurehearings.hmi.acceptance.sessions.helper.SessionsParamsHelper.buildValidRetrieveScheduleParams;

import uk.gov.hmcts.futurehearings.hmi.Application;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;


@Slf4j
@SpringBootTest(classes = {Application.class})
@ActiveProfiles("acceptance")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RetrieveScheduleValidationTest extends SessionsValidationTest {

    @Value("${targetInstance}")
    private String targetInstance;

    @Value("${targetSubscriptionKey}")
    private String targetSubscriptionKey;

    @Value("${sessionApiRootContext}")
    private String sessionAPIRootContext;

    @BeforeAll
    public void initialiseValues() {
        super.initialiseValues();
        this.setRelativeURL(sessionAPIRootContext);
        this.setHttpMethod(HttpMethod.GET);
        this.setHttpSucessStatus(HttpStatus.OK);
        this.setRelativeURLForNotFound(this.getRelativeURL().replace("sessions","session"));
        //this.setUrlParams(buildValidRetrieveScheduleParams());
    }

    @BeforeEach
    public void beforeEach(TestInfo info) {
        log.debug("Before execute : " + info.getTestMethod().get().getName());
        log.debug("The value of the targetInstance "+targetInstance);
    }

    @AfterEach
    public void afterEach(TestInfo info) {
        log.debug("After execute : "+info.getTestMethod().get().getName());
    }

    @Test

    @Disabled("Query Paramaters tests WIP")
    public void test_hearing_date_queryparam_with_value() throws IOException {
        this.setUrlParams(buildValidRetrieveScheduleParams());
        commonDelegate.test_expected_response_for_supplied_header(getApiSubscriptionKey(),
                getRelativeURL(), getInputPayloadFileName(),
                createStandardPayloadHeader(getApiSubscriptionKey()),
                getUrlParams(),
                getHttpMethod(),
                HttpStatus.BAD_REQUEST, getApiName(),null);
    }

}