package pl.insert.handler;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.binding.validation.ValidationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import pl.insert.dto.OrderDto;
import pl.insert.model.Order;

import javax.validation.Valid;

@Component
public class OrderDtoValidator {

    public void validateCreateNewOrderTwo(@Valid @ModelAttribute OrderDto orderDto, ValidationContext context){
        MessageContext messages = context.getMessageContext();
        System.out.println(messages);

        if(orderDto.getSecondName().equals("")){
            messages.addMessage(new MessageBuilder().error().source("secondName").
                    defaultText("Check in date must be a future date").build());
        }


    }


}
