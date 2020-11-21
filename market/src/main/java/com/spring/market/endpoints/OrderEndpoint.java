package com.spring.market.endpoints;

import com.spring.market.services.OrderService;
import com.spring.market.soap.GetUserOrdersRequest;
import com.spring.market.soap.GetUserOrdersResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class OrderEndpoint {
    private static final String NAMESPACE_URI = "http://www.pavel.com/ws/market";
    private OrderService orderService;

    @Autowired
    public OrderEndpoint(OrderService orderService) {
        this.orderService = orderService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserOrdersRequest")
    @ResponsePayload
    public GetUserOrdersResponse getOrdersByUsername(@RequestPayload GetUserOrdersRequest request) {

        GetUserOrdersResponse response = new GetUserOrdersResponse();
        response.getOrder().addAll(orderService.findAllUserOrderXMLByUsername(request.getUsername()));
        return response;
    }
}