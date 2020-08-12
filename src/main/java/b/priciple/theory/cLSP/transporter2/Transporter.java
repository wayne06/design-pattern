package b.priciple.theory.cLSP.transporter2;

public class Transporter {

    private HttpClient httpClient;

    public Transporter(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public Response sendRequest(Request request) {
        Response response = httpClient.execute(request);
        return response;
    }
}
