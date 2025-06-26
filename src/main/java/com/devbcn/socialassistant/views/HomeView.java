package com.devbcn.socialassistant.views;

import com.devbcn.socialassistant.service.ChatService;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.messages.MessageInput;
import com.vaadin.flow.component.messages.MessageList;
import com.vaadin.flow.component.messages.MessageListItem;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serial;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@PageTitle("DevBcn Assistant")
@Route("")
public class HomeView extends VerticalLayout {
    //region variables
    @Serial
    private static final long serialVersionUID = 2405172041950251807L;
    private static final Logger logger = LoggerFactory.getLogger(HomeView.class);
    private static final String SOCIAL_ASSISTANT = "Social Assistant";
    private final transient ChatService chatService;
    private final MessageList messageList = new MessageList();
    //endregion

    public HomeView(ChatService chatService) {
        this.chatService = chatService;
        initializeUI();
    }

    private void onSubmit(@NotNull MessageInput.SubmitEvent submitEvent) {
        String userMessage = submitEvent.getValue();

        logger.info("Prompt received: {}", userMessage);
        messageList.addItem(new MessageListItem(userMessage, Instant.now(), "Me"));
        messageList.addItem(new MessageListItem(chatService.getResponse(userMessage), Instant.now(), SOCIAL_ASSISTANT));
        try {
            getUI().ifPresent(ui -> ui.access(() -> Notification.show("Message received: " + userMessage, 3000, Notification.Position.MIDDLE)));
        } catch (IllegalStateException e) {
            logger.error("Error while processing user message: {}", userMessage, e);
            Notification.show("An error occurred while processing your message. Please try again.",
                    3000, Notification.Position.MIDDLE);
        }
    }

    private void initializeUI() {
        add(new H1("DevBcn Social Assistant"));
        add(new Paragraph("This is the home view"));

        List<MessageListItem> items = new ArrayList<>();
        items.add(new MessageListItem("This is **DevBcn** assistant [website](https://www.devbcn.com/), please enter your prompt", Instant.now(), SOCIAL_ASSISTANT));
        messageList.setItems(items);
        messageList.getStyle().setWidth("100%");

        MessageInput input = new MessageInput();
        input.addSubmitListener(this::onSubmit);
        input.getStyle().setWidth("100%");
        VerticalLayout chatLayout = new VerticalLayout(messageList, input);
        chatLayout.setHeightFull();
        chatLayout.expand(messageList);
        chatLayout.getStyle().setHeight(null).setFlexGrow("1");
        chatLayout.addClassNames(LumoUtility.Padding.MEDIUM);
        add(chatLayout);
    }
}
