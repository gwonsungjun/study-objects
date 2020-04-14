package com.sungjun.objects._02_movie;

public interface DiscountPolicy {
    Money calculateDiscountAmount(Screening screening);
}
