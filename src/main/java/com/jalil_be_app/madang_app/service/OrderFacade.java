package com.jalil_be_app.madang_app.service;

import com.jalil_be_app.madang_app.dto.orderFacadeDto.request.ConfirmOrderRequestDto;
import com.jalil_be_app.madang_app.dto.orderFacadeDto.response.ConfirmOrderResponseDto;

public interface OrderFacade {
    ConfirmOrderResponseDto confirm(String token, ConfirmOrderRequestDto confirmOrderRequestDto);
}
