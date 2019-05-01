package ac.uk.shef.cc19grp10.utils.payment;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;

@Service
@ConditionalOnProperty(prefix= "payment.", name="application_account_id")
public class BillUtility {

    private Logger logger = LoggerFactory.getLogger(BillUtility.class);

    @Value("${payment.server.base_url}")
    private String paymentServletBase;

    @Value("${payment.server.api_url}")
    private String paymentApiUrl;

    @Value("${payment.application_account_id}")
    private long applicationAccountId;

    private RestTemplate restTemplate;

    public BillUtility() {
        this.restTemplate = new RestTemplate();
    }

    public long createBill(int amount) throws CannotCreateBillException {
        return createBill(applicationAccountId, amount);
    }

    public long createBill(long destinationAccountId, int amount) throws CannotCreateBillException {
        URI createBillURI = URI.create(paymentApiUrl + "/bills");

        BillResponse createBillRes;

        {
            LinkedMultiValueMap<String, String> requestForm = new LinkedMultiValueMap<>();
            requestForm.add("toAccount", Long.toString(destinationAccountId));
            requestForm.add("amount", Integer.toString(amount, 10));

            RequestEntity<LinkedMultiValueMap<String,String>> createBillReq = RequestEntity.post(createBillURI)
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                    .body(requestForm);

            logger.info("POST for create bill");
            ResponseEntity<BillResponse> res = restTemplate.exchange(createBillReq, BillResponse.class);
            logger.info("POST for create bill [done]");

            if (!res.getStatusCode().is2xxSuccessful()) {
                throw new CannotCreateBillException();
            }

            createBillRes = res.getBody();

            if (createBillRes == null) {
                throw new CannotCreateBillException();
            }
        }

        return createBillRes.id;
    }

    public boolean verifyBillPaid(long billId) throws CannotVerifyBillException {
        URI verifyBillPaidURI = URI.create(paymentApiUrl + "/bills/" + billId);

        BillResponse responseBody;

        RequestEntity<Void> req = RequestEntity.get(verifyBillPaidURI).build();

        logger.info("GET for verify bill");
        ResponseEntity<BillResponse> res = restTemplate.exchange(req, BillResponse.class);
        logger.info("GET for verify bill [DONE]");

        if (!res.getStatusCode().is2xxSuccessful()) {
            throw new CannotVerifyBillException();
        }

        responseBody = res.getBody();

        if (responseBody == null) {
            throw new CannotVerifyBillException();
        }

        return responseBody.paid;
    }

    public ModelAndView redirectUserToBillPayment(long billId, String callbackUri) throws UnsupportedEncodingException {
        String redirectUri = paymentServletBase + "/bill/" + billId + "/pay";
        redirectUri += "?callbackUri=" + URLEncoder.encode(callbackUri,"UTF-8");
        return new ModelAndView("redirect:" + redirectUri);
    }

    private static class BillResponse {
        @JsonProperty("id")
        public long id;

        @JsonProperty("paid")
        public boolean paid;

        public BillResponse() {}
    }

    public class CannotCreateBillException extends Exception {}
    public class CannotVerifyBillException extends Exception {}
}
