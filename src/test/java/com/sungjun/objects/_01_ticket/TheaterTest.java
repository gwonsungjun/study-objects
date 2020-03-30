package com.sungjun.objects._01_ticket;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

@DisplayName("소극장 클래스 테스트")
class TheaterTest {
    final Long ticketFee = 200L;

    private Ticket ticket1 = new Ticket(ticketFee);
    private Ticket ticket2 = new Ticket(ticketFee);

    private TicketOffice ticketOffice = new TicketOffice(1000L, ticket1, ticket2);

    private TicketSeller ticketSeller = new TicketSeller(ticketOffice);

    private Theater theater = new Theater(ticketSeller);

    @Test
    @DisplayName("관람객의 가방이 초대장 없이 현금만 보관하는 경우")
    public void enterWithInvitation() throws Exception {
        //given
        final Bag bag = new Bag(1000L);
        final Audience audience = new Audience(bag);

        final Long beforeAmount = bag.getAmount();

        //when
        theater.enter(audience);

        //then
        Assertions.assertTrue(bag.hasTicket(), "관람객의 가방에는 티켓이 들어있다.");
        Assertions.assertEquals(Optional.of(bag.getAmount()), Optional.of(beforeAmount - ticketFee), "가방에 현금이 차감되었다.");
    }

    @Test
    @DisplayName("관람객의 가방이 초대장과 현금을 함께 보관하는 경우")
    public void enterWithInvitationAndCash() throws Exception {
        //given
        final Bag bag = new Bag(new Invitation(), 1000L);
        final Audience audience = new Audience(bag);

        final Long beforeAmount = bag.getAmount();

        //when
        theater.enter(audience);

        //then
        Assertions.assertTrue(bag.hasTicket(), "관람객의 가방에는 티켓이 들어있다.");
        Assertions.assertEquals(Optional.of(bag.getAmount()), Optional.of(beforeAmount), "가방에 든 현금이 그대로이다.");
    }
}
